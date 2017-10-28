package MVP_coms_classes;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by tyler on 10/19/2017.
 */

public interface MVP_GameHistory {
    /**
     * Required View methods available to Presenter.
     * A passive layer, responsible to show data
     * and receive user interactions
     */
    interface GameHistoryViewOps{
        /**Gets application context*/
        Context getAppContext();
        /**gets activity context*/
        Context getActivityContext();
        /**Any commands toString method is passed in here to add to the GameHistoryView*/
        void updateGameHistory(ArrayList gameHistoryList);

    }
    /**
     * Operations offered to View to communicate with Presenter.
     * Processes user interactions, sends data requests to Model, etc.
     */
    interface GameHistoryPresOps{
        /**Tells the view to exit itself*/
        void exitView();
    }
}
