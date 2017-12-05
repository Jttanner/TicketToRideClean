package MVP_coms_classes;

import android.content.Context;

import java.util.List;

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

    }
    /**
     * Operations offered to View to communicate with Presenter.
     * Processes user interactions, sends data requests to Model, etc.
     */
    interface MapPresOps{ //View pushes to the presenter
        void getDestinationCards();

        void claimDestinationCards(List<DestinationCard> claimed);

        boolean hasGameJustStarted(Game game, Player player);
    }


}
