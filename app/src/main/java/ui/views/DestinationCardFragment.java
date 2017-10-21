package ui.views;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import clientModel.CModel;
import modeling.Player;
import teamjapannumbahone.tickettoride.R;

/**
 * Created by Hwang on 10/20/2017.
 */

public class DestinationCardFragment extends Fragment {

    //Game game;
    Player player;

    private TextView mRoute1;
    private TextView mRoute2;
    private TextView mRoute3;
    private Button buttonStart;
    private final String TAG = "Destination Frag";

    public DestinationCardFragment(){
        System.out.println("hello");
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

        wireUp();
    }
    void wireUp(){
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
                //((MapActivity)getActivity()).
                //getDialog().dismiss();
            }
        });
        /*
        buttonBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.setColor(MyColor.BLUE.toString());
            }
        });

        buttonSubmit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //game.addPlayer(player);
                ((GameListActivity)getActivity()).presenter.CreateGame(game);
                getDialog().dismiss();
            }
        });
        buttonCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                getDialog().dismiss();

            }
        });*/
    }
}
