package MVP_coms_classes;

import android.content.Context;

import modeling.ResourceCard;

/**
 * Created by tyler on 10/19/2017.
 */

public interface MVP_CardBank {

    /**
     * Required View methods available to Presenter.
     * A passive layer, responsible to show data
     * and receive user interactions
     */
    interface MapViewOps{
        Context getAppContext();
        Context getActivityContext();
        void addFaceUpCard(ResourceCard c);

    }
    /**
     * Operations offered to View to communicate with Presenter.
     * Processes user interactions, sends data requests to Model, etc.
     */
    interface MapPresOps{
        void drawResourceCard(ResourceCard c) ;
    }
}
