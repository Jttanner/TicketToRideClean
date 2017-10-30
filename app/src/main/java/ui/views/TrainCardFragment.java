package ui.views;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import teamjapannumbahone.tickettoride.R;

/**
 * Created by Hwang on 10/28/2017.
 */

public class TrainCardFragment extends DialogFragment {

    private MapActivity mapActivity;

    ImageButton trainCardButton1;
    ImageButton trainCardButton2;
    ImageButton trainCardButton3;
    ImageButton trainCardButton4;
    ImageButton trainCardButton5;
    ImageButton trainDeckCardButton;
    Button chooseNewDestinationCardsButton;

    public TrainCardFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.fragment_creategame, container, false);

        setUp(v);

        return v;
    }
    private void setUp(View v){


        trainCardButton1 = (ImageButton) v.findViewById(R.id.trainCardButton1);
        trainCardButton2 = (ImageButton) v.findViewById(R.id.trainCardButton2);
        trainCardButton3 = (ImageButton) v.findViewById(R.id.trainCardButton3);
        trainCardButton4 = (ImageButton) v.findViewById(R.id.trainCardButton4);
        trainCardButton5 = (ImageButton) v.findViewById(R.id.trainCardButton5);
        trainDeckCardButton = (ImageButton) v.findViewById(R.id.trainDeckCardButton);
        chooseNewDestinationCardsButton = (Button) v.findViewById(R.id.chooseNewDestinationCardsButton);

        wireUp();
    }
    void wireUp(){
        trainCardButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        trainCardButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        trainCardButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        trainCardButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        trainCardButton5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });
        trainDeckCardButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                
            }
        });
        chooseNewDestinationCardsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Switch to Destination Card Fragment
            }
        });
        /*
        public void switchFragments(){
        mapActivity = (MapActivity) getActivity();
        mapActivity.switchFragments();
    }*/
    }
}
