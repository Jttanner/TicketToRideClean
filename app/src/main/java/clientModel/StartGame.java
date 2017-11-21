package clientModel;

import android.util.Log;

import java.util.List;

import commandData.ClaimDestinationCardCommandData;
import commandData.ClaimInitialDestinationCardCommandData;
import commandData.DrawDestinationCardCommandData;
import modeling.DestinationCard;
import modeling.ResourceCard;
import modeling.Route;
import servercomms.ServerProxy;

/**
 * Created by tyler on 11/9/2017.
 * This state will handle the intial setup phse where everyone draws their destination cards.
 * This state will be set when the user executes the startGame Command.
 * It will then set the state object held in the model to the MyTurn or NotMyTurn state,whichever is appropriate.
 * That change will be sent to the Observors through the notifyObservors method
 */

public class StartGame extends GameState {
    @Override
    public void drawResourceCard(ResourceCard resourceCard) {
        //super.drawResourceCard(c);
    }


    @Override
    public void getDestCard() {
        String playerName = CModel.getInstance().getUserPlayer().getPlayerName();
        String gameID = CModel.getInstance().getCurrGame().getGameID();

        DrawDestinationCardCommandData data = new DrawDestinationCardCommandData(gameID, playerName);
        ServerProxy.getInstance().sendCommand(data);
        Log.d("DestCardPresenter", "get3DestinationCards");
    }

    @Override
    public void claimDestCard(List<DestinationCard> cards) {
        String playerName = CModel.getInstance().getUserPlayer().getPlayerName();
        String gameID = CModel.getInstance().getCurrGame().getGameID();

        CModel.getInstance().getUserPlayer().clearTemporaryHand();
        ClaimInitialDestinationCardCommandData data = new ClaimInitialDestinationCardCommandData(gameID, playerName, cards);
        ServerProxy.getInstance().sendCommand(data);
        Log.d("DestCardPresenter", "claimDestinationCards");

        //CModel.getInstance().setCurrGameState(new EndMyTurn());
    }

    //@Override
    //void endTurn() {

    //}

    @Override
    public void gameEnded() {
        //super.gameEnded();
    }

    //@Override
    //public void claimRoute(Route r, String color, Boolean isWildRoute) {
        //super.claimRoute(r);
    //}
}
