package clientModel;

import java.util.List;

import commandData.DrawTrainCardFaceUpCommandData;
import modeling.DestinationCard;
import modeling.ResourceCard;
import modeling.Route;
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

            //Face Up
            if(resourceCard.isFaceUp()) {
                DrawTrainCardFaceUpCommandData drawTrainCardFaceUpCommandData = new DrawTrainCardFaceUpCommandData(playerName, gameID, resourceCard);
                ServerProxy.getInstance().sendCommand(drawTrainCardFaceUpCommandData);
            }
            else {
                //Deck Command
            }
            //Close the View
            CModel.getInstance().closeResourceFragment();

            //Set State
            CModel.getInstance().setCurrGameState(new NotMyTurn());
        }
    }

    @Override
    void drawDestCard(List<DestinationCard> c) {
        super.drawDestCard(c);
    }

    @Override
    void endState() {
        super.endState();
    }

    @Override
    void gameEnded() {
        super.gameEnded();
    }

    @Override
    void claimRoute(Route r) {
        super.claimRoute(r);
    }
}
