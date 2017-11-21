package MVP_coms_classes;

import android.content.Context;

import java.util.List;
import java.util.Observable;

import modeling.DestinationCard;
import modeling.Game;
import modeling.Player;

/**
 * Created by tyler on 10/19/2017.
 */

public interface MVP_DestCard {
    /**
     * Required View methods available to Presenter.
     * A passive layer, responsible to show data
     * and receive user interactions
     */
    interface MapViewOps{ //Presenter pushes to the view
        /**Gets application context*/
        Context getAppContext();
        /**gets activity context*/
        Context getActivityContext();
        /**gives the player the destination cards he has chosen*/
        void giveChosenCards(List<DestinationCard> destinationCards);
        //Or is it string destinationCard?
        //void update(Observable o, Object arg);

    }
    /**
     * Operations offered to View to communicate with Presenter.
     * Processes user interactions, sends data requests to Model, etc.
     */
    interface MapPresOps{ //View pushes to the presenter
        /**Sends the presenter what destination cards were picked*/
        //void pickDestCards(List<DestinationCard> cards) ;

        //void getDestinationCards(Game game, Player player);
        void getDestinationCards();

        //Gives the destination cards that were chosen back to the player and puts back the unchosen cards back to the deck.
        //void claimDestinationCards(Game game, Player player, List<DestinationCard> destinationCards);
        void claimDestinationCards(List<DestinationCard> claimed);

        boolean hasGameJustStarted(Game game, Player player);
    }

    //TODO: BITMAP! Map <String, Bitmap value> cardChoice; to change cards to images of the train cards
}
