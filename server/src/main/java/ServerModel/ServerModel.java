package ServerModel;

import java.util.HashMap;
import java.util.Map;

import modeling.Game;
import modeling.User;

/**
 * Created by jontt on 9/27/2017.
 */

public class ServerModel {
    private static ServerModel instance = new ServerModel();
    private Map<String, User> users = new HashMap<>();//Key=UserName
    private Map<String, Game> games = new HashMap<>();//Key=gameID
    private User user;

    ServerModel(User user){
        this.user = user;
    }

    private ServerModel() {
    }


    static ServerModel getInstance()
    {
        if(instance == null){
            instance = new ServerModel();
        }
        return instance;
    }

    Map<String, User> getUsers() {
        return users;
    }

    Map<String, Game> getGames() {
        return games;
    }


    public User getUser() {
        return user;
    }
}
