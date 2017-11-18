package clientModel;

import java.util.List;

import commandData.ClaimRouteCommandData;
import commandData.DrawTrainCardCommandData;
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
    public void drawResourceCard(ResourceCard resourceCard) {

        String playerName = CModel.getInstance().getUserPlayer().getPlayerName();
        String gameID = CModel.getInstance().getCurrGame().getGameID();

        //Send to Server (This does both deck and face up
        DrawTrainCardCommandData drawTrainCardCommandData = new DrawTrainCardCommandData(playerName, gameID, resourceCard);
        ServerProxy.getInstance().sendCommand(drawTrainCardCommandData);

        //Set new State
        //If card is wild and was face up, end turn
        if(resourceCard.getMyColor().equals("Wild") && resourceCard.isFaceUp()) {
            //Close the View //I think closing here was giving invoking on null object reference, but not it should be okay
            CModel.getInstance().closeResourceFragment();
            CModel.getInstance().setCurrGameState(new EndMyTurn());

            //Send command to server start next player turn - Tak to Austin?
        }
        else {
            CModel.getInstance().setCurrGameState(new OneCardDrawnState());
        }
    }

    @Override
    public void drawDestCard(List<DestinationCard> c) {
        //endTurn();
    }

    @Override
    public void gameEnded() {
        //CModel.getInstance().setCurrGameState(new NotMyTurn());
    }

    @Override
    public void claimRoute(Route currRoute, String color, boolean isWild) {
        ClaimRouteCommandData claimRouteCommandData = new ClaimRouteCommandData(CModel.getInstance().getCurrGame().getGameID(),
                currRoute.getFirstCityName(), currRoute.getSecondCityName(), CModel.getInstance().getUserPlayer().getPlayerName(),
                color, currRoute.getDistance(), isWild);
        ServerProxy.getInstance().sendCommand(claimRouteCommandData);
        //endTurn();
    }
}
