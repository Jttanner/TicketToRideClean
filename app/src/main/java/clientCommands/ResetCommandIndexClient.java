package clientCommands;

import android.util.Log;

import java.util.List;

import clientModel.CModel;
import modeling.Player;

/**
 * Created by tyler on 12/13/2017.
 */

public class ResetCommandIndexClient implements ClientCommand {
    @Override
    public void execute() {
        List<Player> players = CModel.getInstance().getCurrGame().getPlayers();
        for(Player player : players){
            Log.d("ResetCommandIndexClient","Resetting " + player.getPlayerName() + "command index to zero");
            player.setCommandIndex(0);
        }
    }
}
