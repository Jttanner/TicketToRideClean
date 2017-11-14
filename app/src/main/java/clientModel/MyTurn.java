package clientModel;

import java.util.List;

import commandData.DrawTrainCardFaceUpCommandData;
import modeling.DestinationCard;
import modeling.ResourceCard;
import modeling.Route;
import servercomms.ServerProxy;

/**
 * Created by tyler on 11/9/2017.
 * This state will handle all actions that can be done on one's turn.
 * It will set the state in the model to NotMyTurn when done and notify observors
 */

public class MyTurn extends GameState {
    @Override
    public void drawResourceCard(int position) {
        String playerName = CModel.getInstance().getUserPlayer().getPlayerName();
        String gameID = CModel.getInstance().getCurrGame().getGameID();

        //Differentiate between face up and deck
        DrawTrainCardFaceUpCommandData drawTrainCardFaceUpCommandData = new DrawTrainCardFaceUpCommandData(playerName, gameID, "TEMP - My turn State", position);
        ServerProxy.getInstance().sendCommand(drawTrainCardFaceUpCommandData);
        //endState();

    }

    @Override
    void drawDestCard(List<DestinationCard> c) {
        endState();
    }

    @Override
    void endState() {
        CModel.getInstance().setCurrGameState(new NotMyTurn());
    }

    @Override
    void gameEnded() {
        //CModel.getInstance().setCurrGameState(new NotMyTurn());
    }

    @Override
    void claimRoute(Route r) {
        endState();
    }
}
