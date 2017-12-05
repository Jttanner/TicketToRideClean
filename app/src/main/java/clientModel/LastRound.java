package clientModel;

import java.util.List;

import modeling.DestinationCard;
import modeling.ResourceCard;
import modeling.Route;

/**
 * Created by tyler on 11/9/2017.
 * This state will handle actions when it is the last round of the game.
 * It does not need to do anything different from MyTurn except when the last player has played it will send a
 * EndGame Command to the server
 */

public class LastRound extends MyTurn {
    @Override
    public void drawResourceCard(ResourceCard resourceCard) {
        super.drawResourceCard(resourceCard);
    }

    @Override
    public void getDestCard() {
        super.getDestCard();
    }

    @Override
    public void claimDestCard(List<DestinationCard> cards) {
        super.claimDestCard(cards);
    }

    @Override
    public void gameEnded() {
        super.gameEnded();
    }

    @Override
    public void claimRoute(Route currRoute, String color, boolean isWild) {
        super.claimRoute(currRoute, color, isWild);
    }
}
