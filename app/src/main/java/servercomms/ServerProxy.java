package servercomms;

import request.*;
import result.*;

import android.util.Log;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by tyler on 9/26/2017.
 * This class handles all of the requests from the client to the server and vice versa. On the client side it is called by the
 * GUIFacade with input coming from the user in order to perform operations on the server. It takes results from the server and
 * passes them to the ClientFacade,which updates the model.
 */
public class ServerProxy {
    private static ServerProxy ourInstance = new ServerProxy();

    public static ServerProxy getInstance() {
        return ourInstance;
    }

    private String TAG = "ServerProxy";

    private ServerProxy() {

    }

    public LoginResult Login(LoginRequest request){
        URL url = null;
        try {
            //TODO dynamic host and port number getting
            url = new URL("http://localhost:8080/user/login");
        } catch (MalformedURLException e) {
            Log.d(TAG,"URL is messed up yo in login method");
            e.printStackTrace();
        }
        //Make sure we are error checking this return statement
        return (LoginResult) ClientCommunicator.getInstance().send(url,request);
    }
    public RegisterResult Register(RegisterRequest request){
        URL url = null;
        try {
            //TODO dynamic host and port number getting
            url = new URL("http://localhost:8080/user/register");
        } catch (MalformedURLException e) {
            Log.d(TAG,"URL is messed up yo in register method");
            e.printStackTrace();
        }
        //Make sure we are error checking this return statement
        return (RegisterResult) ClientCommunicator.getInstance().send(url,request);
    }
    /*public List<Game> getGameList(Command c){
    }*/
    /*public void Encode(Object o, ResponseBody responseBody){
    }*/
    public void joinGame(){

    }
    public void deleteGame(){

    }
}