package presenters;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import java.lang.ref.WeakReference;

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

public class LoginPresenter implements MVP_Main.RequiredPresenterOps, MVP_Main.ProvidedLoginPresentOps{
    private WeakReference<MVP_Main.RequiredLoginViewOps> myView;
    /**Booleans that tell us whether the user has entered a  suitable userName or password yet*/
    private boolean hasUserName,hasPassword;

    /**
     * Presenter Constructor
     * @param view  MainActivity
     */
    public LoginPresenter(MVP_Main.RequiredLoginViewOps view) {
        myView = new WeakReference<>(view);
    }

    /**
     * Called by View during the reconstruction events
     *
     @Override
     public void setView(MVP_Main.RequiredLoginViewOps view) {
     myView = new WeakReference<>(view);
     }*/

    @Override
    public void login(LoginRequest request) {
        LoginResult result = ServerProxy.getInstance().Login(request);
        checkSuccess(result);
    }

    @Override
    public void register(RegisterRequest request) {
        RegisterResult result = ServerProxy.getInstance().Register(request);
        checkSuccess(result);
    }


    /**Checks the success of the login or registration tells the GUI to take the appropitae action
     * @param result The result of the login/reg
     * */
    private void checkSuccess(ResultObject result) {
        Toast toast = new Toast(myView.get().getAppContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        if(result.isSuccess()){
            //Intent intent = new Intent(this, CreateGame.class);
            //myView.get().loginSucceeded(intent);
            //So obviously the below code will not be in the final product...just a placeholder until we get the next class
            toast.setText("Login worked!");
            myView.get().loginFailed(toast);
        }
        else{
            toast.setText("Login failed: " + result.getErrorMessage());
            myView.get().loginFailed(toast);
        }
    }

    /**
     * Return the View reference.
     * Throw an exception if the View is unavailable.
     */
    private MVP_Main.RequiredLoginViewOps getView() throws NullPointerException{
        if ( myView != null )
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
     * @return  Activity context
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

}