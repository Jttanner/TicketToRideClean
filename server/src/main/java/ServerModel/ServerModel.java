package ServerModel;

import java.util.List;

import modeling.*;

/**
 * Created by jontt on 9/27/2017.
 */

public class ServerModel {

    private User user;

    ServerModel(User user){
        this.user = user;
    }

    public ServerModel() {
    }

    private static ServerModel instance = null;

    public static ServerModel getInstance()
    {
        if(instance == null){
            instance = new ServerModel();
        }
        return instance;
    }


    public User getUser() {
        return user;
    }
}
