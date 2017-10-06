package presenters;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;

import MVP_coms_classes.MVP_Login;
import clientModel.CModel;
import commandData.Command;
import request.LoginRequest;
import request.RegisterRequest;
import result.LoginResult;
import result.RegisterResult;
import result.ResultObject;
import servercomms.ServerProxy;
import ui.views.GameListActivity;

/**
 * Created by tyler on 9/26/2017.
 * The presenter for the login/register View, its function is self explanatory. It handles the logic for logging in and registering
 */

public class LoginPresenter implements MVP_Login.RequiredPresenterOps, MVP_Login.ProvidedLoginPresentOps, Observer {
    private WeakReference<MVP_Login.RequiredLoginViewOps> myView;
    /**
     * Booleans that tell us whether the user has entered a  suitable userName or password yet
     */
    private boolean hasUserName, hasPassword;
    /*A tag for our logs**/
    private final String TAG = "LoginPresenter";

    /**
     * Presenter Constructor
     * @param view MainActivity
     */
    public LoginPresenter(MVP_Login.RequiredLoginViewOps view) {
        myView = new WeakReference<>(view);
        CModel.getInstance().addObserver(this);
    }

    /*TODO need to decide if I need a statemainter. What do I do with this observable Presenter when it is destroyed.
     TODO Probably need to deteach from the Model Objects*
     /
    /**
     * Called by View during the reconstruction events
     *
     * public void setView(MVP_Login.RequiredLoginViewOps view) {
     * myView = new WeakReference<>(view);
     * }
*/
    @Override
    public void login(LoginRequest request) {
        try {
            //TODO dynamic host and port number getting
<<<<<<< HEAD

            String myIp = "128.187.116.11";
            URL url = new URL("http://" + myIp +" :8080/user/login");

=======
            URL url = new URL("http://128.187.116.11:8080/user/login");
>>>>>>> 31f52d54b61c40884744043a10ebff8004a50c5f
            //call the async task
            HttpTask httpTask = new HttpTask();
            httpTask.start(url, request);
        } catch (Exception e) {
            Log.d(TAG, "login method messed up: " + e.toString());
            e.printStackTrace();
            checkLogSuccess(new RegisterResult(false, e.toString(), null));
        }

    }

    @Override
    public void register(RegisterRequest request) {
        try {
            //TODO dynamic host and port number getting
            String myIp = "128.187.116.11";
<<<<<<< HEAD


=======
>>>>>>> 31f52d54b61c40884744043a10ebff8004a50c5f
            URL url = new URL("http://" + myIp +":8080/user/register");
            //call the async task
            HttpTask httpTask = new HttpTask();
            httpTask.start(url,request);
        } catch (Exception e) {
            Log.d(TAG, "register method messed up: " + e.toString());
            checkLogSuccess(new RegisterResult(false, e.toString(), null));
            e.printStackTrace();
        }
    }


    /**
     * Checks the success of the login or registration tells the GUI to take the appropitae action
     * Also as a note I'm passing in an object because i get a java.lang.ClassCastException if I that above
     */
    private void checkLogSuccess(Object r) {
        ResultObject result = null;
        if (r instanceof RegisterResult) {
            result = (RegisterResult) r;

        } else if (r instanceof LoginResult) {
            result = (LoginResult) r;
        }
        if(result != null){
            if (result.isSuccess()) {
                Intent intent = new Intent(getActivityContext(), GameListActivity.class);
                myView.get().loginSucceeded(intent);
                //So obviously the below code will not be in the final product...just a placeholder until we get the next class
            }
            else {//if the login failed or we got some weird output object/no object\

                myView.get().loginFailed(result.getMessage());
            }
        }
        else{
            //If we get here something when wrong with the server and we didnt receive a result object back
            myView.get().loginFailed("Server Failure");
        }

    }

    /**
     * Return the View reference.
     * @exception NullPointerException  Thrown if the View is unavailable.
     * @return MVP_Login.RequiredLoginViewOps The interface returned
     */
    private MVP_Login.RequiredLoginViewOps getView() throws NullPointerException {
        if (myView != null)
            return myView.get();
        else
            throw new NullPointerException("View is unavailable");
    }

    /**
     * @return Application context
     */
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
    /**Checks if the user has entered a password
     * @param b Whether the user has a password or not*/
    @Override
    public void hasPassword(boolean b) {
        hasPassword = b;
        canEnableButtons();
    }
    /**Checks if the user has entered a username
     * @param b Whether the user has a username or not*/
    @Override
    public void hasUserName(boolean b) {
        hasUserName = b;
        canEnableButtons();
    }
    /**Handles the logic of whether the login and register buttons are enabled or not*/
    private void canEnableButtons() {
        myView.get().toggleButtons(hasPassword && hasUserName);
    }

    @Override
    public void update(Observable o, Object arg) {
        //TODO will need to create wrapper classes for the collections of objects passed in here from the model
        Log.d(TAG, "Num of observers: " + o.countObservers());
    }

    /**
     * Task which does a register or login request. Calls the proxy server which calls the server.
     * Is given a Url and a request object to use
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
        }

        @Override
        protected Object doInBackground(URL... urls) {
            Log.d("DoInBackGround", "Entering DoInBackGround");

            if (request instanceof LoginRequest) {
                //Calls the serverProxy
                return ServerProxy.getInstance().login(urls[0], (LoginRequest) request);
            } else if(request instanceof  RegisterRequest) {
                return ServerProxy.getInstance().register(urls[0], (RegisterRequest) request);
            }
            else if(request instanceof  Command){
                //TODO return ServerProxy.getInstance().doStuffWithCommand();
            }
            return new ResultObject(false,"Given incorrect object of type: " + request.getClass());
        }

        @Override
        protected void onPostExecute(Object result) {//gets us back on the main thread
            Log.d("onPostExecute", "Entering onPostExecute");
            super.onPostExecute(result);
            if(result instanceof ResultObject){
                checkLogSuccess(result);
            }
            else if(result instanceof Command){
                //TODO do what you want with the command object
            }

        }
    }

}