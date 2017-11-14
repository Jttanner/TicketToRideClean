package clientModel;

import java.util.List;

import modeling.DestinationCard;
import modeling.ResourceCard;
import modeling.Route;

/**
 * Created by tyler on 11/9/2017.
 * Handles what we do when we have already drawn one resource card
 */

public class OneCardDrawnState extends GameState {
    @Override
    public void drawResourceCard(int position) {
        //If wild and face up then do nothing. Toast that you can't chose face up Wild
        //Otherwise send command.

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
