package ui.views;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import MVP_coms_classes.MVP_DestCard;
import clientModel.CModel;
import modeling.DestinationCard;
import modeling.Game;
import modeling.Player;
import presenters.DestinationCardPresenter;
import teamjapannumbahone.tickettoride.R;

/**
 * Created by Hwang on 10/20/2017.
 */

public class DestinationCardFragment extends DialogFragment implements MVP_DestCard.MapViewOps {

    Game game;
    Player player;

    private TextView mRoute1;
    private TextView mRoute2;
    private TextView mRoute3;
    private Button buttonStart;
    MVP_DestCard.MapPresOps presenter;
    private final String TAG = "Destination Frag";



    public DestinationCardFragment(){
        presenter = new DestinationCardPresenter(this);
        game = CModel.getInstance().getCurrGame();
        player = CModel.getInstance().getUserPlayer();
        /*game = new Game();
        game.setPlayerMax(2);
        System.out.println(CModel.getInstance().getMyUser().getUserName());
        player = new Player(CModel.getInstance().getMyUser().getUserName());
        player.setPlayerName(CModel.getInstance().getMyUser().getInfo().getUserName());
        player.setColor(MyColor.BLACK.toString());*/

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.fragment_destinationcard, container, false);

        this.setCancelable(false);
        setUp(v);
        Log.d(TAG, "Returning View and exiting the method");
        return v;
    }

    //TODO: Create the view, draw randomly from the list of routes, change the textview based on those routes
    private void setUp(View v){
        mRoute1 = (TextView) v.findViewById(R.id.mRoute1);
        mRoute2 = (TextView) v.findViewById(R.id.mRoute2);
        mRoute3 = (TextView) v.findViewById(R.id.mRoute3);
        buttonStart = (Button) v.findViewById(R.id.buttonStart);

        //presenter.getDestinationCards(game, player);
        presenter.getDestinationCards();
        //TODO: How do we update the view after the it goes to the server and back????
    }



    void wireUp(final List<DestinationCard> cards){
        mRoute1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mRoute1.getCurrentTextColor() == Color.RED)
                {
                    mRoute1.setTextColor(Color.GREEN);
                }
                else if (mRoute1.getCurrentTextColor() == Color.GREEN)
                {
                    mRoute1.setTextColor(Color.RED);
                };}
        });
        mRoute2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mRoute2.getCurrentTextColor() == Color.RED)
                {
                    mRoute2.setTextColor(Color.GREEN);
                }
                else if (mRoute2.getCurrentTextColor() == Color.GREEN)
                {
                    mRoute2.setTextColor(Color.RED);
                };}
        });
        mRoute3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mRoute3.getCurrentTextColor() == Color.RED)
                {
                    mRoute3.setTextColor(Color.GREEN);
                }
                else if (mRoute3.getCurrentTextColor() == Color.GREEN)
                {
                    mRoute3.setTextColor(Color.RED);
                };}
        });

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Goes back to the Map activity
                int claimed = 0;
                List<DestinationCard> cardsSelected = new ArrayList();

                if(mRoute1.getCurrentTextColor() == Color.GREEN) {
                    cardsSelected.add(cards.get(0));
                    cards.get(0).setClaimed(true);
                    claimed++;
                }
                else if (mRoute1.getCurrentTextColor() == Color.RED) {
                    cardsSelected.add(cards.get(0));
                }
                if(mRoute2.getCurrentTextColor() == Color.GREEN) {
                    cardsSelected.add(cards.get(1));
                    cards.get(1).setClaimed(true);
                    claimed++;
                }
                else if (mRoute2.getCurrentTextColor() == Color.RED) {
                    cardsSelected.add(cards.get(1));
                }
                if(mRoute3.getCurrentTextColor() == Color.GREEN) {
                    cardsSelected.add(cards.get(2));
                    cards.get(2).setClaimed(true);
                    claimed++;
                }
                else if (mRoute3.getCurrentTextColor() == Color.RED) {
                    cardsSelected.add(cards.get(2));
                }
                boolean gameStarted = presenter.hasGameJustStarted(game, player);
                if(gameStarted == false) {
                    if(claimed >= 2) {
                        //presenter.claimDestinationCards(game, player, cardsSelected);
                        presenter.claimDestinationCards(cardsSelected);

                        //((TextView) getActivity().findViewById(R.id.destination_deck_size)).setText("25");
                        getDialog().dismiss();
                        //presenter.endTurn(game, player);
                    }
                    else {
                        Toast.makeText(getActivity(), "Please select 2 or 3 destinations", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    if(claimed >= 1) {
                        //presenter.claimDestinationCards(game, player, cardsSelected);
                        presenter.claimDestinationCards(cardsSelected);


                        //TODO: We need to be able to update the view here?
                        //((TextView) getActivity().findViewById(R.id.destination_deck_size)).setText("25");
                        getDialog().dismiss();
                        //presenter.endTurn(game, player);
                    }
                    else {
                        Toast.makeText(getActivity(), "Please select at least 1 destination", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    @Override
    public Context getAppContext() {
        return null;
    }

    @Override
    public Context getActivityContext() {
        return null;
    }

    @Override
    public void giveChosenCards(List<DestinationCard> destinationCards) {

        if (destinationCards.get(0) == null) {
            this.setCancelable(true);
            Toast.makeText(getActivity(), "No more Destination Cards left. Sad.", Toast.LENGTH_LONG).show();
        }
        else {
            mRoute1.setText(destinationCards.get(0).getDestinationCardString());
            mRoute2.setText(destinationCards.get(1).getDestinationCardString());
            mRoute3.setText(destinationCards.get(2).getDestinationCardString());
            //mRoute1.setText("Hi my name is Kwan");
            //mRoute2.setText("What is your name?");
            //mRoute3.setText("Nice to meet you!");
            mRoute1.setTextColor(Color.RED);
            mRoute2.setTextColor(Color.RED);
            mRoute3.setTextColor(Color.RED);

            //Toast.makeText(getActivity(), "Picking Destination Cards", Toast.LENGTH_LONG).show();

            wireUp(destinationCards);
        }
    }
}
