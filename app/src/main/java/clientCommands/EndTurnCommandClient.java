package clientCommands;

import clientModel.CModel;
import clientModel.MyTurn;
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
        //Once one players turn ends, see if I am the next player through advance player turn.
        String nextPlayer = CModel.getInstance().getCurrGame().advancePlayerTurn();
        if(nextPlayer.equals(CModel.getInstance().getMyUser().getUserName())) {
            CModel.getInstance().setCurrGameState(new MyTurn());
        }
        else {
            //update the player stats
            CModel.getInstance().setCurrGameState(new NotMyTurn());
        }
        CModel.getInstance().updatePlayerStatsView();
    }
}
