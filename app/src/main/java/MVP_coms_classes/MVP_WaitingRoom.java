package MVP_coms_classes;

import android.content.Context;

import commandData.StartGameCommandData;
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

    }
    interface RequiredPresenterOps {
        void startGame(StartGameCommandData startGameCommandData);

    }
}
