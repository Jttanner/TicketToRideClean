package presenters;

import android.os.AsyncTask;
import android.util.Log;

import java.net.URL;

import commandData.Command;
import request.LoginRequest;
import request.RegisterRequest;
import servercomms.ServerProxy;

/**
 * Created by LabUser on 10/5/2017.
 */

class HttpTask extends AsyncTask<URL, Integer, Object> {//URL im sending off
    private Object request;

    void start(URL url, Object req) {
        if (req instanceof RegisterRequest) {
            request = req;
            Log.d("start", "Do a regRequest");
            //Goes into doInBackGround
            execute(url);
        } else if(req instanceof LoginRequest) {
            Log.d("start", "Do a loginRequest");
            request = req;
            //Goes into doInBackGround
            execute(url);
        }
        else if (req instanceof Command){
            request = req;
        }
    }

    @Override
    protected Object doInBackground(URL... urls) {
        Log.d("DoInBackGround", "Entering DoInBackGround");

        if (request instanceof LoginRequest) {
            //Calls the serverProxy
            return ServerProxy.getInstance().login(urls[0], (LoginRequest) request);
        } else {
            return ServerProxy.getInstance().register(urls[0], (RegisterRequest) request);
        }
    }

    @Override
    protected void onPostExecute(Object result) {//gets us back on the main thread
        Log.d("onPostExecute", "Entering onPostExecute");
        super.onPostExecute(result);
        LoginPresenter.checkLogSuccess(result);
    }
}