package MVP_coms_classes;

import android.content.Context;
import android.widget.ImageButton;

/**
 * Created by Hwang on 10/29/2017.
 */

public interface MVP_TrainCard {
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

        }
        /**
         * Operations offered to View to communicate with Presenter.
         * Processes user interactions, sends data requests to Model, etc.
         */
        interface MapPresOps{ //View pushes to the presenter
            void cardClicked(ImageButton card);
            void deckClicked();

        }

    }
