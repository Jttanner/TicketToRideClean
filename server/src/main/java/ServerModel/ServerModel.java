package ServerModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modeling.Game;
import modeling.Player;
import modeling.User;
import modeling.GameList;
import modeling.UserInfo;
import modeling.UserInfoList;
import request.LoginRequest;
import result.LoginResult;

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
    private User user;
    private Map<String, Player> playerMap; //Key = playerID
    private GameList gameList;
    private UserInfoList userInfoList;


    public ServerModel() {
    }

    public static ServerModel getInstance()
    {
        if(instance == null){
            instance = new ServerModel();
        }
        return instance;
    }

    public User register(String userName, String password){ //If register succeeds, it'll give us back a new user object
        return userInfoList.register(userName, password);
    }

    public boolean login(String userName, String password){
        UserInfo matches = userInfoList.login(userName, password);
        if(matches != null){//If the account exists and matches with one in the database...
            return true;
        }
        else{
            return false;
        }
    }

    public boolean createGame(Game newGame){
        return gameList.addGame(newGame);
    }

    public boolean joinGame(User user, String gameID){
        return gameList.joinGame(user, gameID);
    }

    public boolean startGame(Game game){
        return gameList.startGame(game);
    }


    //(userName, User)
    public Map<String, User> getUsers() {
        return users;
    }

    //(gameID, Game)
    public Map<String, Game> getGamesAsMap(){
        return games;
    }

    //Takes in the current list of games and returns a list of games that haven't started yet
    public GameList getGames() {
        List<Game> gameList = new ArrayList<>();

        for (Map.Entry<String, Game> game : getGamesAsMap().entrySet()){
            if (!game.getValue().isHasStarted()){
                gameList.add(game.getValue());
            }
        }
        return new GameList(gameList);
    }


    public User getUser() {
        return user;
    }
}
