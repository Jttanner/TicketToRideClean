package presenters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.Observable;
import java.util.Observer;

import MVP_coms_classes.MVP_Login;
import clientModel.CModel;
import modeling.User;
import request.LoginRequest;
import request.RegisterRequest;
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
            ServerProxy.getInstance().login(request);
        } catch (Exception e) {
            Log.d(TAG, "login method messed up: " + e.toString());
            e.printStackTrace();
            checkLogSuccess(new ResultObject(false, e.toString()));
        }

    }

    @Override
    public void register(RegisterRequest request) {
        try {
            ServerProxy.getInstance().register(request);
        } catch (Exception e) {
            Log.d(TAG, "register method messed up: " + e.toString());
            checkLogSuccess(new ResultObject(false, e.toString()));
            e.printStackTrace();
        }
    }

    /**
     * Checks the success of the login or registration tells the GUI to take the appropitae action
     * Also as a note I'm passing in an object because i get a java.lang.ClassCastException if I that above
     * @param r The result object being passed in
     */
    private void checkLogSuccess(ResultObject r) {
        myView.get().loginFailed(r.getMessage());
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
        //if we have updated the current user in the model for this machine, move on to next activity
        if(arg instanceof User){
            Intent intent = new Intent(getActivityContext(), GameListActivity.class);
            myView.get().loginSucceeded(intent);
            //if we got a boolean and it is false
        }else if(arg instanceof  Boolean && !(Boolean)arg){
            myView.get().loginFailed("Failure, please try again");
        }
        Log.d(TAG, "Num of observers: " + o.countObservers());
    }



}