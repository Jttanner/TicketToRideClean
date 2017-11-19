package ui.views;

import android.support.v4.app.DialogFragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import MVP_coms_classes.MVP_DrawResourceCard;
import clientModel.CModel;
import modeling.ResourceCard;
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
    private TextView resourceCardCount;
    private MVP_DrawResourceCard.DrawResourceCardPresOps myPresenter;
    private View v;
    public DrawResourceCardFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_draw_resource_card, container, false);
        getDialog().show();
        getDialog().getWindow().setLayout(1000,1000);

        setUp(v);
        onClickers();
        displayResourceCards(v);

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
        resourceCardCount = (TextView) v.findViewById(R.id.resourceCardCount);
    }
    public void onClickers() {
        resourceCard1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //In order to create the right resource card we need to get it at the position it was clicked
                //instead of position we might need to send in the card ID...im not sure
                ResourceCard card = CModel.getInstance().getCurrGame().getResourceCardList().getAvailableCards().get(0);
                card.setFaceUp(true);
                myPresenter.drawCard(card);
            }
        });
        resourceCard2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //In order to create the right resource card we need to get it at the position it was clicked
                ResourceCard card = CModel.getInstance().getCurrGame().getResourceCardList().getAvailableCards().get(1);
                card.setFaceUp(true);
                myPresenter.drawCard(card);

            }
        });
        resourceCard3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //In order to create the right resource card we need to get it at the position it was clicked
                ResourceCard card = CModel.getInstance().getCurrGame().getResourceCardList().getAvailableCards().get(2);
                card.setFaceUp(true);
                myPresenter.drawCard(card);
            }
        });
        resourceCard4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //In order to create the right resource card we need to get it at the position it was clicked
                ResourceCard card = CModel.getInstance().getCurrGame().getResourceCardList().getAvailableCards().get(3);
                card.setFaceUp(true);
                myPresenter.drawCard(card);
            }
        });
        resourceCard5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //In order to create the right resource card we need to get it at the position it was clicked
                ResourceCard card = CModel.getInstance().getCurrGame().getResourceCardList().getAvailableCards().get(4);
                card.setFaceUp(true);
                myPresenter.drawCard(card);
            }
        });
        //Draw the next card in deck
        //Add it to player
        resourceCardDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //In order to create the right resource card we need to get it at the position it was clicked
                ResourceCard card = CModel.getInstance().getCurrGame().getResourceCardList().getAvailableCards().get(5);
                card.setFaceUp(false);
                myPresenter.drawCard(card);
            }
        });

    }

    /*
    * Set up face up cards and deck view when game starts.
    *
    */
    public void displayResourceCards(View v) {
        int availableCardSize = CModel.getInstance().getCurrGame().getResourceCardList().getAvailableCards().size();
//        if(CModel.getInstance().getCurrGame().getResourceCardList().getAvailableCards().size() < 5) {
//            availableCardSize = CModel.getInstance().getCurrGame().getResourceCardList().getAvailableCards().size();
//        }
//        else {
//            availableCardSize = 5;
//        }
        for (int i = 0; i < 5; ++i) {
            ResourceCard card = CModel.getInstance().getCurrGame().getResourceCardList().getAvailableCards().get(i);
            int cardID = 0;
            switch (i) {
                case 0:
                    cardID = R.id.resourceCard1;
                    break;
                case 1:
                    cardID = R.id.resourceCard2;
                    break;
                case 2:
                    cardID = R.id.resourceCard3;
                    break;
                case 3:
                    cardID = R.id.resourceCard4;
                    break;
                case 4:
                    cardID = R.id.resourceCard5;
                    break;
                default:
                    break;
            }
            if (i >= availableCardSize) {
                ((ImageButton) v.findViewById(cardID)).setImageResource(R.drawable.rainbow_sq);
                switch (i) {
                    case 0:
                        resourceCard1.setEnabled(false);
                        break;
                    case 1:
                        resourceCard1.setEnabled(false);
                        break;
                    case 2:
                        resourceCard1.setEnabled(false);
                        break;
                    case 3:
                        resourceCard1.setEnabled(false);
                        break;
                    case 4:
                        resourceCard1.setEnabled(false);
                        break;
                    default:
                        break;
                }
            } else {
                ((ImageButton) v.findViewById(cardID)).setImageResource(getResourceCardColorByID(card.getMyColor()));
            }
        }
        if (availableCardSize <= 5) {
            resourceCardDeck.setEnabled(false);
        } else {
            resourceCardDeck.setEnabled(true);
        }

        resourceCardDeck.setImageResource(R.drawable.backcard);
        resourceCardCount.setText("Resource Card Remaining in Deck: " + CModel.getInstance().getCurrGame().getResourceCardList().getAvailableCards().size());
    }

    public int getResourceCardColorByID(String color) {
        switch (color.toLowerCase()) {
            case "black":
                return R.drawable.blacktrain;
            case "purple":
                return R.drawable.purpletrain;
            case "white":
                return R.drawable.whitetrain;
            case "blue":
                return R.drawable.bluetrain;
            case "yellow":
                return R.drawable.yellowtrain;
            case "green":
                return R.drawable.greentrain;
            case "red":
                return R.drawable.redtrain;
            case "orange":
                return R.drawable.orangetrain;
            default:
                return R.drawable.wildtrain;
        }
    }

    @Override
    public void upDateFaceUp() {
        displayResourceCards(v);
        ButtonsOn();
    }

    @Override
    public void close() {
        if(getDialog() != null) {
            getDialog().dismiss();
        }
    }

    @Override
    public void lock() {
        getDialog().setCancelable(false);
    }

    @Override
    public void unlock() {
        getDialog().setCancelable(true);
    }

    @Override
    public void noCards() {

    }

    @Override
    public void ButtonsOn() {
        resourceCard1.setEnabled(true);
        resourceCard2.setEnabled(true);
        resourceCard3.setEnabled(true);
        resourceCard4.setEnabled(true);
        resourceCard5.setEnabled(true);
        resourceCardDeck.setEnabled(true);
    }

    @Override
    public void ButtonsOff() {
        resourceCard1.setEnabled(false);
        resourceCard2.setEnabled(false);
        resourceCard3.setEnabled(false);
        resourceCard4.setEnabled(false);
        resourceCard5.setEnabled(false);
        resourceCardDeck.setEnabled(false);
    }
}
