package servercomms;

import android.util.Log;

import java.net.MalformedURLException;
import java.net.URL;

import commandData.Command;
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
    private String ipaddress = "10.24.69.231";
    private String myUrl = "http://" + ipaddress +":8080/user/";

    private ServerProxy() {
    }


    public void login( LoginRequest request){
        Log.d(TAG,"Logging on");
        HttpTask httpTask = new HttpTask();
        String url = myUrl +"login";
        try {
            httpTask.start(new URL(url),request);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    public void register(RegisterRequest request){
        Log.d(TAG,"Registering");
        HttpTask httpTask = new HttpTask();
        String url = myUrl +"register";
        try {
            httpTask.start(new URL(url),request);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    /**Sends commands to the server. All commandData objects are of Type Command
     * @param command The command to be sent*/
    public void sendCommand(Command command){
        Log.d(TAG, "Sending Commands: " + command.getType());
        HttpTask httpTask = new HttpTask();
        String url = myUrl +"command";
        try {
            httpTask.start(new URL(url),command);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}