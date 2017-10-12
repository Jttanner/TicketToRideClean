package servercomms;

import android.util.Log;

import java.net.MalformedURLException;
import java.net.URL;

import commandData.Command;
import commandData.CreateGameCommandData;
import request.LoginRequest;
import request.RegisterRequest;

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
    private String ipaddress = "10.24.71.220";
    private String myUrl = "http://" + ipaddress +"8080/user/";

    private ServerProxy() {
    }


    public void login( LoginRequest request){
        Log.d(TAG,"Logging on");
        HttpTask httpTask = new HttpTask();
        myUrl += "login";
        try {
            httpTask.start(new URL(myUrl),request);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    public void register(RegisterRequest request){
        Log.d(TAG,"Registering");
        HttpTask httpTask = new HttpTask();
        myUrl += "register";
        try {
            httpTask.start(new URL(myUrl),request);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

//    public List<Game> getGameList(Command c) {
//
//    }
    public void CreateGame(CreateGameCommandData command) {
        Log.d(TAG, "CreateGame");
        HttpTask httpTask = new HttpTask();
        myUrl += "command";
        try {
            httpTask.start(new URL(myUrl),command);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    public void getGameList(Command command){
        Log.d(TAG, "Getting game list");
        HttpTask httpTask = new HttpTask();
        myUrl += "command";
        try {
            httpTask.start(new URL(myUrl),command);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    //The request object for createGame will need the User Object
    public void joinGame(){

    }
    public void deleteGame(){

    }
}