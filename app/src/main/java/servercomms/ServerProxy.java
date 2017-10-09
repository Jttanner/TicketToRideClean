package servercomms;

import android.util.Log;

import com.encoder.Encoder;

import java.io.InputStream;
import java.net.URL;

import clientModel.CModel;
import commandData.Command;
import commandData.CreateGameCommandData;
import request.LoginRequest;
import request.RegisterRequest;
import result.CommandResult;
import result.LoginResult;
import result.RegisterResult;

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


    public LoginResult login(URL url, LoginRequest request){
        Log.d(TAG,"Logging on");
        String typeOfRequest = "POST";
        InputStream inputStream = ClientCommunicator.getInstance().send(url,request,typeOfRequest);
        //decode ,set the user object, and send back to the presenter
        LoginResult result = new Encoder().decodeLoginResult(inputStream);
        CModel.getInstance().setMyUser(result.getUser());
        return result;
    }
    public RegisterResult register(URL url, RegisterRequest request){
        Log.d(TAG,"Registering");
        String typeOfRequest = "POST";
        InputStream inputStream = ClientCommunicator.getInstance().send(url,request,typeOfRequest);
        //decode ,set the user object, and send back to the presenter
        RegisterResult result = new Encoder().decodeRegisterResult(inputStream);
        CModel.getInstance().setMyUser(result.getUser());
        return result;
    }

//    public List<Game> getGameList(Command c) {
//
//    }
    public CommandResult CreateGame(URL url, CreateGameCommandData command) {
        String typeOfRequest = "POST";
        InputStream inputStream = ClientCommunicator.getInstance().send(url, command, typeOfRequest);
        //decode ,set the user object, and send back to the presenter
        CommandResult result = new Encoder().decodeCommand(inputStream);
        return result;
    }
    public Object getGameList(URL url, Command command){
        Log.d(TAG, "Getting game list");
        String typeOfRequest = "GET";
        InputStream inputStream = ClientCommunicator.getInstance().send(url, command, typeOfRequest);
        //GetGameListCommandData getGameListCommandData = new Encoder().decodeGetGameListCommandData(inputStream);
        CommandResult result = new Encoder().decodeCommand(inputStream);
        return result.getData();

    }

    //The request object for createGame will need the User Object
    public void joinGame(){

    }
    public void deleteGame(){

    }
}