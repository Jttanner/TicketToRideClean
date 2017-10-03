package ui.views;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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

public class LoginActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.activity_login);
        // “Create a new fragment transaction, include one add operation in it, and then commit it.”
        if(fragment == null){
            fragment = new LoginFragment();
            fm.beginTransaction().add(R.id.fragment_spot,fragment).commit();
        }
    }

}