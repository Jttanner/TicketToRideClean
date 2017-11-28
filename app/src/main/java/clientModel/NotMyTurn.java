package clientModel;

import android.util.Log;

import java.util.List;

import commandData.ClaimDestinationCardCommandData;
import modeling.DestinationCard;
import modeling.ResourceCard;
import modeling.Route;
import servercomms.ServerProxy;

/**
 * Created by tyler on 11/9/2017.
 * This state will ensure that no actions can be taken if it is not my turn. When the commandmanager gets a
 * command that tells the Model that it is the user's turn again we will set the state to MyTurn, notifying observers
 */

public class NotMyTurn extends GameState {

    @Override
    public void gameEnded() {
        super.gameEnded();
    }

}
