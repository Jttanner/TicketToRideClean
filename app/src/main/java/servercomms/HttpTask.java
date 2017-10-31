package servercomms;

import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;
import java.net.URL;

import commandData.Command;
import commandData.CreateGameCommandData;
import commandData.GetCmndDataFromServer;
import commandData.GetGameListCommandData;
import commandData.JoinGameCommandData;
import encoder.Encoder;
import request.LoginRequest;
import request.RegisterRequest;
import result.CommandResult;
import result.LoginResult;
import result.RegisterResult;
import result.ResultObject;

/**
 * Task which does a register or login request. Calls the proxy server which calls the server.
 * Is given a Url and a request object to use
 */

class HttpTask extends AsyncTask<URL, Integer, Object> {//URL im sending off
    private Object request;
    private final String TAG = "HttpTask";

    HttpTask() {
    }

    void start(URL url, Object req) {
        try {

            if (req instanceof RegisterRequest) {
                request = req;
                Log.d("start", "Do a regRequest");
                //Goes into doInBackGround
            } else if (req instanceof LoginRequest) {
                Log.d("start", "Do a loginRequest");
                request = req;
                //Goes into doInBackGround
            }
            else if (req instanceof Command){
                request = req;
            }
            execute(url);
        }
        catch (Exception e){
            Log.d("here", "login method messed up: " + e.toString());
            e.printStackTrace();
        }

    }

    @Override
    protected Object doInBackground(URL... urls) {
        Log.d("DoInBackGround", "Entering DoInBackGround");
        Encoder encoder = new Encoder();
        String typeOfRequest = "POST";
        //connection with the server is here
        InputStream stream = ClientCommunicator.getInstance().send(urls[0],request,typeOfRequest);
       // String string = stream.toString();

        if (request instanceof LoginRequest) {
            return encoder.decodeLoginResult(stream);
        } else if (request instanceof RegisterRequest) {
            return encoder.decodeRegisterResult(stream);
        } else if(request instanceof GetGameListCommandData){
            return encoder.decodeGetGameResult(stream);
        }
        else if(request instanceof CreateGameCommandData){
            return encoder.decodeCreateResult(stream);
        }
        else if(request instanceof JoinGameCommandData){
            return encoder.decodeJoinCommandResult(stream);
        }
        else if(request instanceof GetCmndDataFromServer){
            return encoder.decodeGetCommandListToClient(stream);
        }
        else if(request instanceof Command){
            return encoder.decodeCommandResult(stream);
        }
        Log.d(TAG,"Yo things went wack, you gave us the wrong object type in the HttpTask");
        return new ResultObject(false, "Given incorrect object of type: " + request.getClass());
    }

    @Override
    protected void onPostExecute(Object result) {//gets us back on the main thread
        Log.d("onPostExecute", "Entering onPostExecute");
        ClientFacade facade = ClientFacade.getInstance();
        super.onPostExecute(result);
        if (result instanceof LoginResult) {
            facade.updateUser(((LoginResult) result).getUser());
        } else if (result instanceof RegisterResult) {
            facade.updateUser(((RegisterResult) result).getUser());
        }
        //this is used for any other command
        else if(result instanceof CommandResult){
            facade.checkTypeOfCommand((CommandResult) result);
        }
        else if(result instanceof Command){
            facade.checkTypeOfCommand((Command) result);
        }


    }

}