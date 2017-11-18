package clientModel;

import commandData.EndTurnCommandData;
import servercomms.ServerProxy;

/**
 * Created by tyler on 11/18/2017.
 */

public class EndMyTurn extends GameState {
    @Override
    public void endTurn() {
        CModel cModel = CModel.getInstance();
        EndTurnCommandData data = new EndTurnCommandData(cModel.getCurrGame().getGameID());
        ServerProxy.getInstance().sendCommand(data);
    }
}
