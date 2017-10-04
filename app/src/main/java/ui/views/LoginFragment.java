package ui.views;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import MVP_coms_classes.MVP_Login;
import presenters.LoginPresenter;
import request.LoginRequest;
import request.RegisterRequest;
import teamjapannumbahone.tickettoride.R;


/**
 * Created by tyler on 3/13/2017.
 * Our loginFragment
 */

public class LoginFragment extends Fragment implements MVP_Login.RequiredLoginViewOps {
    /**The pointer to our presenter object*/
    private MVP_Login.ProvidedLoginPresentOps mPresenter;
    /**Our login button. Click to login*/
    private Button mLoginButton;
    /**User registration button*/
    private Button mRegisterButton;
    /**Where you enter your username*/
    private EditText mUserNameEdit;
    /**where you enter your password*/
    private EditText mPassWordEdit;
    /**Tag for log*/
    private final String TAG = "login Activity";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "Entering OnCreateView");
        super.onCreate(savedInstanceState);
        View v = inflater.inflate(R.layout.activity_login, container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setupMVP();
        setupWidgets(v);
        //This code below just ensures our buttons are enabled with our hardcoded input
        int textLength = mUserNameEdit.getText().toString().length();
        mPresenter.hasUserName(textLength > 0);
        textLength = mPassWordEdit.getText().toString().length();
        mPresenter.hasPassword( textLength > 0);

        Log.d(TAG, "Returning View and exiting the method");
        return v;
    }


    private void setupMVP() {
        // Create the Presenter
        LoginPresenter presenter = new LoginPresenter(this);
        // Set the Presenter as a interface
        // To limit the communication with it
        mPresenter = presenter;
    }
    /**Here we setup the widgets so that they can be used
     * @param v Our View Object*/
    private void setupWidgets(View v) {
        mLoginButton = (Button) v.findViewById(R.id.loginButton);
        mRegisterButton = (Button) v.findViewById(R.id.registerButton);
        mUserNameEdit = (EditText) v.findViewById(R.id.editUserName);
        mPassWordEdit = (EditText) v.findViewById(R.id.editPassword);
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
        return this.getActivity().getApplicationContext();
    }

    @Override
    public Context getActivityContext() {
        return this.getActivity();
    }

    @Override
    public void loginSucceeded(Intent intent) {
        //startActivity(intent);
    }

    @Override
    public void loginFailed(String errorMessage ) {
        Toast.makeText(getActivityContext(),errorMessage,Toast.LENGTH_LONG).show();
    }

}

