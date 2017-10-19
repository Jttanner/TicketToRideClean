package MVP_coms_classes;

import android.content.Context;

/**
 * Created by tyler on 10/19/2017.
 */

public interface MVP_GameChat {
    /**
     * Required View methods available to Presenter.
     * A passive layer, responsible to show data
     * and receive user interactions
     */
    interface GameChatVOps{
        /**Gets application context*/
        Context getAppContext();
        /**gets activity context*/
        Context getActivityContext();
        /**sends chat string to presenter drawn*/
        void sendChatToServer(String chatString);

    }
    /**
     * Operations offered to View to communicate with Presenter.
     * Processes user interactions, sends data requests to Model, etc.
     */
    interface MapPresOps{
        /**Sends the presenter what destination cards were picked*/
        void updateChat(String chatString) ;
    }
}
