package servercomms;

import request.*;
import result.*;

import android.util.Log;

import com.encoder.Encoder;

import java.io.InputStream;
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

    public LoginResult login(URL url, LoginRequest request){
        Log.d(TAG,"Logging on");
        String typeOfRequest = "POST";
        InputStream inputStream = ClientCommunicator.getInstance().send(url,request,typeOfRequest);
        //decode and send back to the presenter
        return new Encoder().decodeLoginResult(inputStream);
    }
    public RegisterResult register(URL url, RegisterRequest request){
        Log.d(TAG,"Registering");
        String typeOfRequest = "POST";
        InputStream inputStream = ClientCommunicator.getInstance().send(url,request,typeOfRequest);
        //decode and send back to the presenter
        return new Encoder().decodeRegisterResult(inputStream);
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