package ServerModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import commandData.ChatCommandData;
import commandData.ClaimDestinationCardCommandData;
import commandData.Command;
import modeling.CommandList;
import modeling.DestinationCard;
import modeling.DestinationCardList;
import modeling.Game;
import modeling.GameList;
import modeling.Player;
import modeling.User;
import modeling.UserInfoList;

/**
 * Created by jontt on 9/27/2017.
 */

public class ServerModel {

    /*
    This holds all the information and calculations needed for the server.
     */

    private static ServerModel instance = new ServerModel();
    private Map<String, User> users = new HashMap<>(); //Key=UserName
    private Map<String, Game> games = new HashMap<>(); //Key=gameID
    private Map<String, CommandList> commandListMap = new HashMap<>();
    private DestinationCardList destinationCardList = new DestinationCardList();
    private GameList gameList = new GameList();
    private UserInfoList userInfoList = new UserInfoList();
    private List<String> chatHistory = new ArrayList<>();

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

    boolean startGame(Game game){
        game.setHasStarted(true);
        commandListMap.put(game.getGameID(),new CommandList());
        return gameList.startGame(game);
    }

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
    public Map<String, Game> getGamesAsMap(){
        return games;
    }
    public void addChatHistory(String s,String gameId){

        gameList.findGame(gameId).getChatHistory().add(s);

        ChatCommandData chatCommandData = new ChatCommandData(gameList.findGame(gameId).getChatHistory(),gameId);
        ServerModel.getInstance().getReturnCommand().add(chatCommandData);
        ServerFacade.getInstance().addCommandToList(gameId, chatCommandData);
    }

    //Takes in the current list of games and returns a list of games that haven't started yet
    GameList getGames() {
        //List<Game> gameList = new ArrayList<>();

        /*
        for (Map.Entry<String, Game> game : getGamesAsMap().entrySet()){
            if (!game.getValue().isHasStarted()){
                gameList.add(game.getValue());
            }
        }



        return new GameList(gameList);*/

        return this.gameList;
    }

    public Map<String, CommandList> getCommandListMap() {
        return commandListMap;
    }

    public List<DestinationCard> getDestinationCards() {
        return destinationCardList.get3Cards();
    }

    public List<DestinationCard> distributeUsedDestinationCards(ClaimDestinationCardCommandData commandData) {
        List<DestinationCard> claimedCards = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            boolean isClaimed = commandData.getClaimDestinationCards().get(i).isClaimed();
            if (isClaimed) {
                 claimedCards.add(commandData.getClaimDestinationCards().get(i));
            }
            else if(!isClaimed){
                destinationCardList.getDestinationCardList().add(commandData.getClaimDestinationCards().get(i));
            }
        }
        String playerID = commandData.getPlayerID();
        Game currGame = gameList.findGame(commandData.getGameID());
        Player currPlayer = currGame.getPlayer(playerID);
        currPlayer.addDestinationCard(claimedCards);
        return claimedCards;
    }
}
