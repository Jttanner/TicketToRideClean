package MVP_coms_classes;

import android.content.Context;

import modeling.Game;

/**
 * Created by korea on 10/6/2017.
 */

public interface MVP_WaitingRoom {
    interface RequiredViewOps {
        void updateWaitingRoom(Game g);
        // View operations permitted to Presenter
        Context getAppContext();

        Context getActivityContext();
        /**Starts your game*/
        void goToMap();
        /**Tell the user that going to the map failed, need more players*/
        void goToMapFailed();
    }
    interface RequiredPresenterOps {
        void startGame();

        void deleteObserver();
    }
}
