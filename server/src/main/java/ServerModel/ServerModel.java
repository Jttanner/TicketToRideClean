package ServerModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modeling.Game;
import modeling.User;
import result.GameList;

/**
 * Created by jontt on 9/27/2017.
 */

public class ServerModel {

    /*
    This holds all the information needed for the server.
     */

    private static ServerModel instance = new ServerModel();
    private Map<String, User> users = new HashMap<>(); //Key=UserName
    private Map<String, Game> games = new HashMap<>(); //Key=gameID
    private User user;

    private ServerModel() {
    }

    static ServerModel getInstance()
    {
        if(instance == null){
            instance = new ServerModel();
        }
        return instance;
    }

    //(userName, User)
    Map<String, User> getUsers() {
        return users;
    }

    //(gameID, Game)
    Map<String, Game> getGamesAsMap(){
        return games;
    }

    //Takes in the current list of games and returns a list of games that haven't started yet
    GameList getGames() {
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
