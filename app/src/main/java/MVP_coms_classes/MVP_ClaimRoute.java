package MVP_coms_classes;

import android.content.Context;

import java.util.List;

import modeling.DestinationCard;
import modeling.Game;
import modeling.Player;

/**
 * Created by jontt on 11/2/2017.
 */

public interface MVP_ClaimRoute {
    interface MapViewOps{ //Presenter pushes to the view
        /**Gets application context*/
        Context getAppContext();
        /**gets activity context*/
        Context getActivityContext();
        /**gives the player the destination cards he has chosen*/
        //void giveChosenCards(List<DestinationCard> destinationCards);
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

        //Gives the destination cards that were chosen back to the player and puts back the unchosen cards back to the deck.
        //void claimDestinationCards(Game game, Player player, List<DestinationCard> destinationCards);
    }
}
