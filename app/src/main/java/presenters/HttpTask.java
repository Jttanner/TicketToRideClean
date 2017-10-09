package presenters;

import android.os.AsyncTask;
import android.util.Log;

import java.net.URL;

import MVP_coms_classes.CommandSuccessChecker;
import MVP_coms_classes.LoginSuccessChecker;
import commandData.CreateGameCommandData;
import request.LoginRequest;
import request.RegisterRequest;
import result.CommandResult;
import result.ResultObject;
import servercomms.ServerProxy;
/**
 * Task which does a register or login request. Calls the proxy server which calls the server.
 * Is given a Url and a request object to use
 */

class HttpTask extends AsyncTask<URL, Integer, Object> {//URL im sending off
    private Object request;
    private LoginSuccessChecker loginChecker;
    private CommandSuccessChecker commandChecker;
    private String address = "192.168.0.7";

    HttpTask(Object presenter) {
        if(presenter instanceof LoginPresenter ){
            loginChecker = (LoginPresenter) presenter;
        }
        else if( presenter instanceof  GameListPresenter){
            commandChecker = (GameListPresenter) presenter;
        }
    }

    void start(String postfix, Object req) {
        address += postfix;
        try {
            URL url = new URL("http://" + address);
            if (req instanceof RegisterRequest) {
                request = req;
                Log.d("start", "Do a regRequest");
                //Goes into doInBackGround
                execute(url);
            } else if (req instanceof LoginRequest) {
                Log.d("start", "Do a loginRequest");
                request = req;
                //Goes into doInBackGround
                execute(url);
            }
            else if (req instanceof CreateGameCommandData){
                request = req;
                execute(url);
            }

        }
        catch (Exception e){
            Log.d("here", "login method messed up: " + e.toString());
            e.printStackTrace();
        }

    }

    @Override
    protected Object doInBackground(URL... urls) {
        Log.d("DoInBackGround", "Entering DoInBackGround");

        if (request instanceof LoginRequest) {
            //Calls the serverProxy
            return ServerProxy.getInstance().login(urls[0], (LoginRequest) request);
        } else if (request instanceof RegisterRequest) {
            return ServerProxy.getInstance().register(urls[0], (RegisterRequest) request);
        } else if (request instanceof CreateGameCommandData) {
            return ServerProxy.getInstance().CreateGame(urls[0],(CreateGameCommandData) request);
        }
        return new ResultObject(false, "Given incorrect object of type: " + request.getClass());
    }

    @Override
    protected void onPostExecute(Object result) {//gets us back on the main thread
        Log.d("onPostExecute", "Entering onPostExecute");
        super.onPostExecute(result);
        if (result instanceof ResultObject) {
            loginChecker.checkLogSuccess(result);
        } else if (result instanceof CommandResult) {
            commandChecker.checkCommandSuccess((CommandResult)result);
            //TODO do what you want with the command object
        }


    }

}