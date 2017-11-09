package ui.views;

import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import MVP_coms_classes.MVP_DrawResourceCard;
import presenters.DrawResourceCardPresenter;
import teamjapannumbahone.tickettoride.R;

/**
 * Created by korea on 11/8/2017.
 */

public class DrawResourceCardFragment extends DialogFragment implements MVP_DrawResourceCard.DrawResourceCardViewOps {
    private ImageButton resourceCard1;
    private ImageButton resourceCard2;
    private ImageButton resourceCard3;
    private ImageButton resourceCard4;
    private ImageButton resourceCard5;
    private ImageButton resourceCardDeck;
    private Button endTurn;

    private MVP_DrawResourceCard.DrawResourceCardPresOps myPresenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_draw_resource_card, container, false);
        getDialog().show();
        getDialog().getWindow().setLayout(1000,1000);

        setUp(v);
        onClickers();
        setUpResourceView();

        return v;
    }

    public void setUp(View v)
    {
        myPresenter = new DrawResourceCardPresenter(this);
        resourceCard1 = (ImageButton) v.findViewById(R.id.resourceCard1);
        resourceCard2 = (ImageButton) v.findViewById(R.id.resourceCard2);
        resourceCard3 = (ImageButton) v.findViewById(R.id.resourceCard3);
        resourceCard4 = (ImageButton) v.findViewById(R.id.resourceCard4);
        resourceCard5 = (ImageButton) v.findViewById(R.id.resourceCard5);
        resourceCardDeck = (ImageButton) v.findViewById(R.id.resourceCardDeck);
        endTurn = (Button) v.findViewById(R.id.resourceCardEndTurn);
    }
    public void onClickers() {
        resourceCard1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        resourceCard2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        resourceCard3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        resourceCard4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        resourceCard5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        resourceCardDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        endTurn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    /*
    * Set up face up cards and deck view when game starts.
    *
    */
    public void setUpResourceView() {

    }
    @Override
    public void upDateFaceUp() {

    }
}
