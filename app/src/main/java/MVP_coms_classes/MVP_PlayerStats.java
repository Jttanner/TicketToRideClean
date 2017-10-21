package MVP_coms_classes;

import android.content.Context;

import java.util.ArrayList;

import modeling.Game;
import ui.views.PlayerColumns;

/**
 * Created by tyler on 10/20/2017.
 */

public interface MVP_PlayerStats {

    interface ViewOps{
        /**Returns the application context*/
        Context getAppContext();
        /**The activity context of the view*/
        Context getActivityContext();
        /**updates the player stats*/
        void updatePlayerStats(Game g);
    }

    interface PresOps{
        //Will get the player columns for the Game status
        ArrayList<PlayerColumns> getPlayerColumns();
    }
}
