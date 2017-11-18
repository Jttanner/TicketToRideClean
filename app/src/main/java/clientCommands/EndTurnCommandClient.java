package clientCommands;

import clientModel.CModel;
import clientModel.NotMyTurn;
import commandData.EndTurnCommandData;

/**
 * Created by tyler on 11/18/2017.
 * Tells the client to advance player turns
 */

public class EndTurnCommandClient implements ClientCommand {

    public EndTurnCommandClient() {
    }

    @Override
    public void execute() {
        CModel.getInstance().getCurrGame().advancePlayerTurn();
        //update the player stats
        CModel.getInstance().setCurrGameState(new NotMyTurn());
        CModel.getInstance().updatePlayerStatsView();
    }
}
