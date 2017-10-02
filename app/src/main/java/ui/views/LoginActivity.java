package ui.views;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import MVP_coms_classes.MVP_Main;
import presenters.LoginPresenter;
import request.LoginRequest;
import request.RegisterRequest;
import teamjapannumbahone.tickettoride.R;

public class LoginActivity extends AppCompatActivity implements MVP_Main.RequiredLoginViewOps {
    /**The pointer to our presenter object*/
    private MVP_Main.ProvidedLoginPresentOps mPresenter;
    /**Our login button. Click to login*/
    private Button mLoginButton;
    /**User registration button*/
    private Button mRegisterButton;
    /**Where you enter your username*/
    private EditText mUserNameEdit;
    /**where you enter your password*/
    private EditText mPassWordEdit;
    /**Tag for log*/
    private final String TAG = "Login Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setupMVP();
        setupWidgets();

    }


    private void setupMVP() {
        //ALL THIS COMMENTED CODE DEALS WITH THE CLASS StateMaintainer, may be removed, as it is probably not needed
        // Check if StateMaintainer has been created
        // if (CModel.getInstance().getStateMaintainer() == null) {
        // Create the Presenter
        LoginPresenter presenter = new LoginPresenter(this);
        // Add Presenter to StateMaintainer
        //CModel.getInstance().setStateMaintainer(new StateMaintainer(presenter));
        // Set the Presenter as a interface
        // To limit the communication with it
        mPresenter = presenter;

        // }
        // get the Presenter from StateMaintainer
        /*else {
            Log.d(TAG,"Getting the presenter, we should already have it in the model");
            // Get the Presenter
            String presenterName = LoginPresenter.class.getName();
            StateMaintainer main = CModel.getInstance().getStateMaintainer();
            mPresenter  = (MVP_Main.ProvidedLoginPresentOps) main.getPresenter(presenterName);
            // Updated the View in Presenter
            mPresenter.setView(this);
        }*/
    }
    /**Here we setup the widgets so that they can be used*/
    private void setupWidgets() {
        mLoginButton = (Button)findViewById(R.id.loginButton);
        mRegisterButton = (Button)findViewById(R.id.registerButton);
        mUserNameEdit = (EditText) findViewById(R.id.editUserName);
        mPassWordEdit = (EditText) findViewById(R.id.editPassword);
        setTextListeners();
        setOnClicks();

    }

    @Override
    public void toggleButtons(boolean b) {
        mLoginButton.setEnabled(b);
        mRegisterButton.setEnabled(b);
    }

    /**Where we set the text listeners for the edit texts*/
    private void setTextListeners() {
        mUserNameEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //if the textfield has more one or more char in it, we are good to go!
                mPresenter.hasUserName(editable.length() > 0);
            }
        });

        mPassWordEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //if the textfield has more one or more char in it, we are good to go!
                mPresenter.hasPassword(editable.length() > 0);
            }
        });
    }
    /**Here we setup all of our on click listeners*/
    private void setOnClicks() {
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = mUserNameEdit.getText().toString();
                String password = mPassWordEdit.getText().toString();
                mPresenter.login(new LoginRequest(username,password));
            }
        });
        mLoginButton.setEnabled(false);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = mUserNameEdit.getText().toString();
                String password = mPassWordEdit.getText().toString();
                mPresenter.register(new RegisterRequest(username,password));
            }
        });
        mRegisterButton.setEnabled(false);
    }

    @Override
    public Context getAppContext() {
        return getApplicationContext();
    }

    @Override
    public Context getActivityContext() {
        return this;
    }

    @Override
    public void loginSucceeded(Intent intent) {
        // Intent intent = new Intent(this, CreateGame.class);
        //startActivity(intent);
    }

    @Override
    public void loginFailed(Toast toast ) {
        toast.show();
    }

}