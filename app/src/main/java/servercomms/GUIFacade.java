package servercomms;

import modeling.User;

import result.*;

/**
 * Created by tyler on 9/26/2017.
 * This class is called by the Presenters in order to pass data/requests from the user to the server. It does so by
 * calling the appropriate function on the server proxy class
 */
class GUIFacade {
    private static GUIFacade ourInstance = new GUIFacade();

    public static GUIFacade getInstance() {
        return ourInstance;
    }

    private GUIFacade() {
    }

    public LoginResult Login(User u){
        return null;
    }
    public RegisterResult Register(User u){
        return null;
    }
}