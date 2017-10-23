package MVP_coms_classes;

import android.content.Context;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

import clientModel.MyColor;
import clientModel.PlayerColumn;
import modeling.Game;

/**
 * Created by tyler on 10/20/2017.
 * Our interfaces for the player stats view and presenter
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
        ArrayList<PlayerColumn> getPlayerColumns();

        void setCardNumbers(Map<MyColor, TextView> colorNumMap);
    }
}
