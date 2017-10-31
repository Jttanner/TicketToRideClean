package ui.views;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import MVP_coms_classes.MVP_TrainCard;
import clientModel.CModel;
import modeling.Game;
import modeling.Player;
import presenters.TrainCardPresenter;
import teamjapannumbahone.tickettoride.R;

/**
 * Created by Hwang on 10/28/2017.
 */

public class TrainCardFragment extends DialogFragment implements MVP_TrainCard.MapViewOps{

    Game game;
    Player player;
    MVP_TrainCard.MapPresOps presenter;
    ImageButton trainCardButton1;
    ImageButton trainCardButton2;
    ImageButton trainCardButton3;
    ImageButton trainCardButton4;
    ImageButton trainCardButton5;
    ImageButton trainDeckCardButton;
    Button chooseNewDestinationCardsButton;

    public TrainCardFragment(){
        game = CModel.getInstance().getCurrGame();
        player = CModel.getInstance().getUserPlayer();
        List<ImageButton> imageButtonList = new ArrayList<>(Arrays.asList(trainCardButton1, trainCardButton2,
                trainCardButton3, trainCardButton4, trainCardButton5));
        presenter = new TrainCardPresenter(this, imageButtonList);
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

    @Override
    public Context getAppContext() {
        return getActivity().getApplication();
    }

    @Override
    public Context getActivityContext() {
        return getActivity();
    }

    void wireUp(){
        trainCardButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.cardClicked(trainCardButton1);
                //player.addResourceCard();
            }
        });
        trainCardButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.cardClicked(trainCardButton2);
                //player.addResourceCard();
            }
        });
        trainCardButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.cardClicked(trainCardButton3);
                //player.addResourceCard();
            }
        });
        trainCardButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.cardClicked(trainCardButton4);
                //player.addResourceCard();
            }
        });
        trainCardButton5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                presenter.cardClicked(trainCardButton5);
                //player.addResourceCard();
            }
        });
        trainDeckCardButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                presenter.deckClicked();
                //player.addResourceCard();
            }
        });
        /*
        public void switchFragments(){
        mapActivity = (MapActivity) getActivity();
        mapActivity.switchFragments();
    }*/
    }
}
