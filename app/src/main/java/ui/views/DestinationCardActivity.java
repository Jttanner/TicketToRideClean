package ui.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import teamjapannumbahone.tickettoride.R;

/**
 * Created by ahwang13 on 10/30/17.
 */

public class DestinationCardActivity extends AppCompatActivity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destinationcard);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_destinationcard);
        // “Create a new fragment transaction, include one add operation in it, and then commit it.”
        if(fragment == null){
            fragment = new DestinationCardFragment();
            fm.beginTransaction().add(R.id.fragment_spot1,fragment).commit();
        }
    }
}
