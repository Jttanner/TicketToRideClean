package clientModel;

import java.util.List;

import commandData.DrawTrainCardCommandData;
import commandData.EndTurnCommandData;
import modeling.DestinationCard;
import modeling.ResourceCard;
import servercomms.ServerProxy;

/**
 * Created by tyler on 11/9/2017.
 * Handles what we do when we have already drawn one resource card
 */

public class OneCardDrawnState extends GameState {
    @Override
    public void drawResourceCard(ResourceCard resourceCard) {
        String playerName = CModel.getInstance().getUserPlayer().getPlayerName();
        String gameID = CModel.getInstance().getCurrGame().getGameID();

        //If wild and face up then do nothing. Toast that you can't chose face up Wild
        if(resourceCard.getMyColor().equals("Wild") && resourceCard.isFaceUp()) {

        }
        else {
            //Otherwise send command.

            //Send to Server (This does both deck and face up)
            DrawTrainCardCommandData drawTrainCardCommandData = new DrawTrainCardCommandData(playerName, gameID, resourceCard);
            ServerProxy.getInstance().sendCommand(drawTrainCardCommandData);

            //Close the View //I think closing here was giving invoking on null object reference, but now it should be okay
            CModel.getInstance().closeResourceFragment();

            //Set State
            CModel.getInstance().setCurrGameState(new EndMyTurn());

            //Send command to server start next player turn - Tak to Austin?
        }
    }

    /*
    @Override
    public void drawDestCard(List<DestinationCard> c) {
        super.drawDestCard(c);
    }*/

    @Override
    public void endTurn() {
    }

    @Override
    public void gameEnded() {
        super.gameEnded();
    }

    //@Override
    //public void claimRoute(Route r, String color, Boolean isWild) {
        //super.claimRoute(r, color);
    //}
}
