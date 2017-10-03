package presenters;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.net.URL;

import MVP_coms_classes.MVP_Main;
import request.LoginRequest;
import request.RegisterRequest;
import result.LoginResult;
import result.RegisterResult;
import result.ResultObject;
import servercomms.ServerProxy;

/**
 * Created by tyler on 9/26/2017.
 * The presenter for the login/register View, its function is self explanatory. It handles the logic for logging in and registering
 */

public class LoginPresenter implements MVP_Main.RequiredPresenterOps, MVP_Main.ProvidedLoginPresentOps {
    private WeakReference<MVP_Main.RequiredLoginViewOps> myView;
    /**
     * Booleans that tell us whether the user has entered a  suitable userName or password yet
     */
    private boolean hasUserName, hasPassword;
    /*A tag for our logs**/
    private final String TAG = "LoginPresenter";

    /**
     * Presenter Constructor
     *
     * @param view MainActivity
     */
    public LoginPresenter(MVP_Main.RequiredLoginViewOps view) {
        myView = new WeakReference<>(view);
    }

    /**
     * Called by View during the reconstruction events
     *
     * @Override public void setView(MVP_Main.RequiredLoginViewOps view) {
     * myView = new WeakReference<>(view);
     * }
     */

    @Override
    public void login(LoginRequest request) {
        try {
            //TODO dynamic host and port number getting
            URL url = new URL("http://localhost:8080/user/login");
            //call the async task
            HttpTask httpTask = new HttpTask();
            httpTask.start(url, request);
        } catch (Exception e) {
            Log.d(TAG, "login method messed up: " + e.toString());
            e.printStackTrace();
            checkSuccess(new RegisterResult(false, e.toString()));
        }

    }

    @Override
    public void register(RegisterRequest request) {
        try {
            //TODO dynamic host and port number getting
            URL url = new URL("http://10.24.71.148:8080/user/register");
            //call the async task
            HttpTask httpTask = new HttpTask();
            httpTask.start(url,request);
        } catch (Exception e) {
            Log.d(TAG, "register method messed up: " + e.toString());
            checkSuccess(new RegisterResult(false, e.toString()));
            e.printStackTrace();
        }
    }


    /**
     * Checks the success of the login or registration tells the GUI to take the appropitae action
     * Also as a note I'm passing in an object because i get a java.lang.ClassCastException if I that above
     */
    private void checkSuccess(Object r) {
        ResultObject result = null;
        Toast toast = new Toast(myView.get().getAppContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        if (r instanceof RegisterResult) {
            result = (RegisterResult) r;

        } else if (r instanceof LoginResult) {
            result = (LoginResult) r;
        }
        if(result != null){
            if (result.isSuccess()) {
                //Intent intent = new Intent(this, CreateGame.class);
                //myView.get().loginSucceeded(intent);
                //So obviously the below code will not be in the final product...just a placeholder until we get the next class
                myView.get().loginFailed(result.getMessage());
            }
            else {//if the login failed or we got some weird output object/no object\

                myView.get().loginFailed(result.getMessage());
            }
        }

    }

    /**
     * Return the View reference.
     * Throw an exception if the View is unavailable.
     */
    private MVP_Main.RequiredLoginViewOps getView() throws NullPointerException {
        if (myView != null)
            return myView.get();
        else
            throw new NullPointerException("View is unavailable");
    }

    @Override
    public Context getAppContext() {
        try {
            return getView().getAppContext();
        } catch (NullPointerException e) {
            //TODO send toast out
            return null;
        }
    }

    /**
     * @return Activity context
     */
    @Override
    public Context getActivityContext() {
        try {
            return getView().getActivityContext();
        } catch (NullPointerException e) {
            //TODO send toast out
            return null;
        }
    }

    @Override
    public void hasPassword(boolean b) {
        hasPassword = b;
        canEnableButtons();
    }

    @Override
    public void hasUserName(boolean b) {
        hasUserName = b;
        canEnableButtons();
    }

    private void canEnableButtons() {
        myView.get().toggleButtons(hasPassword && hasUserName);
    }

    /**
     * Task which does a register or login request. Calls the proxy server which calls the server
     */
    private class HttpTask extends AsyncTask<URL, Integer, Object> {//URL im sending off
        private Object request;

        void start(URL url, Object req) {
            if (req instanceof RegisterRequest) {
                request = req;
                Log.d("start", "Do a regRequest");
                //Goes into doInBackGround
                execute(url);
            } else if(req instanceof  LoginRequest) {
                Log.d("start", "Do a loginRequest");
                request = req;
                //Goes into doInBackGround
                execute(url);
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
            checkSuccess(result);
        }
    }

}