/*
package ui.views;

import android.os.AsyncTask;
import android.util.Log;

import java.net.URL;

import request.LoginRequest;
import request.RegisterRequest;
import servercomms.ServerProxy;

*/
/**
 * Task which does a register or login request. Calls the proxy server which calls the server
 * *//*

public class HttpTask extends AsyncTask<URL, Integer, Object> {//URL im sending off
    private Object request;
    private Object resultObj;

    public Object start(URL url, Object req) {
        if (req instanceof RegisterRequest) {
            request = req;
            Log.d("start", "Do a regRequest");
            //Goes into doInBackGround
            resultObj = execute(url);
            return resultObj;
        } else {
            Log.d("start", "Do a loginRequest");
            request = req;
            //Goes into doInBackGround
            resultObj = execute(url);
            return resultObj;
        }
    }

    protected Object doInBackground(URL... urls) {//... says treat it like an array even tho it isnt
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
        //checkResult(result);
    }
}*/
