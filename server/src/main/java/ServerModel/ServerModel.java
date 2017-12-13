package ServerModel;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import command.AddChatCommand;
import command.ClaimDestinationCardCommand;
import command.ClaimInitialDestinationCardCommand;
import command.ClaimRouteCommand;
import command.DrawDestinationCardCommand;
import command.DrawTrainCardCommand;
import command.EndTurnCommandServer;
import command.GetCmndListServer;
import command.ICommand;
import command.IncrementCommandIndexCommand;
import command.ResetCommandIndex;
import command.StartGameCommand;
import commandData.*;
import commandData.GetCmndListDataToClient;
import modeling.Game;
import modeling.GameList;
import modeling.Player;
import modeling.User;
import modeling.UserInfo;
import modeling.UserInfoList;

/**
 * Serves as the root of the server where information and algorithms are stored. Accesses important data such as the list of Destination Cards,
 * list of commands, list of games, list of user info, and chat history.
 * Created by jontt on 9/27/2017.
 */

public class ServerModel {

    /*
    This holds all the information and calculations needed for the server.
     */
    private int delta_n = 0;
    private static ServerModel instance = new ServerModel();
    private Map<String, User> users = new HashMap<>(); //Key=UserName
    //private Map<String, Game> games = new HashMap<>(); //Key=gameID
    private Map<String, List<Command>> commandListMap = new HashMap<>();
    //private DestinationCardList destinationCardList = new DestinationCardList();
    private GameList gameList = new GameList();
    private UserInfoList userInfoList = new UserInfoList();
    private List<String> chatHistory = new ArrayList<>();

    /**Our Persistence manager object
     private IPersistenceManager pManager;*/
    /**
     * Our current plugin object
     */
    private IPlugin currPlugin;

    public void zeroOut(String playerID) {
        for (Game game : gameList.getGames()) {
            for (Player player : game.getPlayers()) {
                if (player.getPlayerName().equals(playerID)) {
                    player.setCommandIndex(0);
                }
            }
        }
    }

    public String checkUserInGame(String userId) {
        if (gameList != null) {
            List<Game> games = gameList.getGames();
            for (Game game : games) {
                for (Player player : game.getPlayers()) {
                    if (userId.equals(player.getPlayerName())) {
                        return game.getGameID();
                    }
                }
            }
        }
        return "no";
    }

    public List<String> getChatHistory() {
        return chatHistory;
    }

    public List<Command> returnCommand = new ArrayList<>();

    public List<Command> getReturnCommand() {
        return returnCommand;
    }

    public void setReturnCommand(List<Command> returnCommand) {
        this.returnCommand = returnCommand;
    }

    public void setChatHistory(List<String> chatHistory) {
        this.chatHistory = chatHistory;
    }

    public ServerModel() {
    }

    public static ServerModel getInstance() {
        if (instance == null) {
            instance = new ServerModel();
        }
        return instance;
    }

    User register(String userName, String password) { //If register succeeds, it'll give us back a new user object
        User user = userInfoList.register(userName, password);
        currPlugin.saveUser(user);
        return user;
    }

    User login(String userName, String password) {
        //If the account exists and matches with one in the database...
        //User user = new User(userInfoList.login(userName, password));
        User myUser = currPlugin.verifyUser(userName, password);
        if (myUser.getUserName().equals(userName) && myUser.getInfo().getPassword().equals(password)) {
            userInfoList.login(userName, password);
        }
        return myUser;
    }

    boolean createGame(Game newGame) {
        //if the game has not already been made
        return gameList.addGame(newGame);
    }

    Game joinGame(User user, String gameID) {
        return gameList.joinGame(user, gameID);
    }

    /*boolean startGame(String gameID){;
        commandListMap.put(gameID,new ArrayList<Command>());
        return gameList.startGame(gameID);
    }*/

    boolean deleteGame(Game game) {
        return gameList.deleteGame(game);
    }

    /*
    public boolean leaveGame(Game game, Player player){
        return gameList.leaveGame(game);
    }
    */

    //(userName, User)
    public Map<String, User> getUsers() {
        return users;
    }

    //(gameID, Game)

    void addChatHistory(String s, String gameId) {/*public Map<String, Game> getGamesAsMap(){
        return games;
    }*/

        gameList.findGame(gameId).getChatHistory().add(s);

        ChatCommandData chatCommandData = new ChatCommandData(gameList.findGame(gameId).getChatHistory(), gameId);
        ServerModel.getInstance().getReturnCommand().add(chatCommandData);
        ServerFacade.getInstance().addCommandToList(gameId, chatCommandData);
    }

    //Takes in the current list of games and returns a list of games that haven't started yet
    GameList getGames() {
        return this.gameList;
    }

    public Map<String, List<Command>> getCommandListMap() {
        return commandListMap;
    }

    public IPlugin getPlugin() {
        return currPlugin;
    }

    /**
     * @param fileName The PluginName
     * @param n        "n" save integer
     */
    public void saveArgs(String fileName, String n) {
        Loader loader = new Loader();
        ArrayList<String> fileArgs = loader.readFile(fileName);

        //save the persistence manager and the plugin
        if (fileArgs != null) {
            try {
                currPlugin = (IPlugin) loader.loadClass(fileName, fileArgs.get(0));
            } catch (Exception e) {
                e.printStackTrace();
            }

            IPersistenceManager persistenceManager = PluginRegistry.getInstance().create(fileName, fileArgs.get(1));
            currPlugin.setPManager(persistenceManager);
        } else {
            System.out.println("Something went horribly wrong");
        }
        delta_n = Integer.parseInt(n);
    }


    public int getDelta_n() {
        return delta_n;
    }

    /**
     * Called when the server is started to see if there is anything in the db. If there is it will repopulate the db
     */
    public void redoServerModel() throws Exception {
        List<User> allUsers = currPlugin.getAllUsers();
        List<Game> allGames = currPlugin.getAllGames();
        if ((allUsers != null && allUsers.size() > 0) || (allGames != null && allGames.size() > 0)) {
            //add all the users back in
            for (User user : allUsers) {
                users.put(user.getUserName(), user);
                userInfoList.addUser(user.getInfo());
                userInfoList.getUsernameToUserInfo().put(user.getUserName(), user.getInfo());
                userInfoList.getUserToUserInfo().put(user, user.getInfo());
            }
            gameList.setGames(allGames);
           /* for (Game game:gameList.getGames()) {
                commandListMap.put(game.getGameID(),currPlugin.getGameCommands(game.getGameID()));
            }*/

            //have to go through the command list of each game to ensure the game is completely updated
            for (Game game : gameList.getGames()) {
                for (Command com : commandListMap.get(game.getGameID())) {
                    if (com != null) {
                        switch (com.getType()) {
                            case "startGame":
                                StartGameCommandData startGameCommandData = (StartGameCommandData) com;
                                StartGameCommand startGameCommand = new StartGameCommand(startGameCommandData);
                                startGameCommand.execute();
                                break;
                            case "addChat":
                                ChatCommandData chatCommandData = (ChatCommandData) com;
                                AddChatCommand addChatCommand = new AddChatCommand(chatCommandData);
                                addChatCommand.execute();
                                break;
                            case "drawTrainCard":
                                DrawTrainCardCommandData drawTrainCardCommandData = (DrawTrainCardCommandData) com;
                                DrawTrainCardCommand drawTrainCardCommand = new DrawTrainCardCommand(drawTrainCardCommandData);
                                drawTrainCardCommand.execute();
                                break;
                            case "drawDestinationCards":
                                DrawDestinationCardCommandData drawDestinationCardCommandData = (DrawDestinationCardCommandData) com;
                                DrawDestinationCardCommand drawDestinationCardCommand = new DrawDestinationCardCommand(drawDestinationCardCommandData);
                                drawDestinationCardCommand.execute();
                                break;
                            case "claimDestinationCards":
                                ClaimDestinationCardCommandData claimDestinationCardCommandData = (ClaimDestinationCardCommandData) com;
                                ClaimDestinationCardCommand claimDestinationCardCommand = new ClaimDestinationCardCommand(claimDestinationCardCommandData);
                                claimDestinationCardCommand.execute();
                                break;
                            case "claimInitialDestinationCards":
                                ClaimInitialDestinationCardCommandData claimInitialDestinationCardCommandData = (ClaimInitialDestinationCardCommandData) com;
                                ClaimInitialDestinationCardCommand claimInitialDestinationCardCommand = new ClaimInitialDestinationCardCommand(claimInitialDestinationCardCommandData);
                                claimInitialDestinationCardCommand.execute();
                                break;
                            case "claimRoute":
                                ClaimRouteCommandData claimRouteCommandData = (ClaimRouteCommandData) com;
                                ClaimRouteCommand claimRouteCommand = new ClaimRouteCommand(claimRouteCommandData);
                                claimRouteCommand.execute();
                                break;
                            case "endTurn":
                                EndTurnCommandData data = (EndTurnCommandData) com;
                                EndTurnCommandServer endTurnCommandServer = new EndTurnCommandServer(data);
                                endTurnCommandServer.execute();
                                break;
                            case "incrementCommandIndex":
                                IncrementCommandIndexCommandData incrementCommandIndexCommandData = (IncrementCommandIndexCommandData) com;
                                IncrementCommandIndexCommand incrementCommandIndexCommand = new IncrementCommandIndexCommand(incrementCommandIndexCommandData.getGameID(), incrementCommandIndexCommandData.getPlayerName());
                                incrementCommandIndexCommand.execute();
                                break;
                        }
                    }
                }
                clearCommandsAndSave(game.getGameID());
            }
        } else {
            System.out.println("ServerModel:redoServerModel: db is empty or returning null");
        }
    }

    void clearCommandsAndSave(String gameID) {
        System.out.println("ServerFacade:saveCommands: saving game: " + gameID);
        Command cmd = new ResetCommandIndexData();
        //rmake new cmd list
        ArrayList<Command> list = new ArrayList<>();
        list.add(cmd);
        //add this command to the map
        commandListMap.put(gameID, list);
        //reset servermodel indices
        ICommand command = new ResetCommandIndex();
        //also execute the command server side
        command.execute();
        //now reset db
        getPlugin().saveGame(getGames().findGame(gameID));
        getPlugin().clearCommandList(gameID);
        //now db has 1 command in the list
        getPlugin().saveGameCommands(gameID, cmd);
    }
}