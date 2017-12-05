package ServerModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import commandData.ChatCommandData;
import commandData.Command;
import modeling.Game;
import modeling.GameList;
import modeling.User;
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

    private static ServerModel instance = new ServerModel();
    private Map<String, User> users = new HashMap<>(); //Key=UserName
    //private Map<String, Game> games = new HashMap<>(); //Key=gameID
    private Map<String, List<Command>> commandListMap = new HashMap<>();
    //private DestinationCardList destinationCardList = new DestinationCardList();
    private GameList gameList = new GameList();
    private UserInfoList userInfoList = new UserInfoList();
    private List<String> chatHistory = new ArrayList<>();

    /**The plugin registry object*/
    private PluginRegistry pluginRegistry = PluginRegistry.getInstance();

    /**Our Persistence manager object*/
    private IPersistenceManager pManager;

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

    public static ServerModel getInstance()
    {
        if(instance == null){
            instance = new ServerModel();
        }
        return instance;
    }

    User register(String userName, String password){ //If register succeeds, it'll give us back a new user object
        return userInfoList.register(userName, password);
    }

    User login(String userName, String password){
        //If the account exists and matches with one in the database...
        return new User(userInfoList.login(userName, password));
    }

    boolean createGame(Game newGame){
        //if the game has not already been made
        return gameList.addGame(newGame);
    }

    Game joinGame(User user, String gameID){
        return gameList.joinGame(user, gameID);
    }

    /*boolean startGame(String gameID){;
        commandListMap.put(gameID,new ArrayList<Command>());
        return gameList.startGame(gameID);
    }*/

    boolean deleteGame(Game game){
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

    void addChatHistory(String s,String gameId){/*public Map<String, Game> getGamesAsMap(){
        return games;
    }*/

        gameList.findGame(gameId).getChatHistory().add(s);

        ChatCommandData chatCommandData = new ChatCommandData(gameList.findGame(gameId).getChatHistory(),gameId);
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

    /**
     * @param pluginName The PluginName
     * @param n "n" save integer*/
    public void saveArgs(String pluginName, String n) {
        pManager = pluginRegistry.create(pluginName);
        //TODO save n, the "n"
    }

}