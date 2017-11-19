package clientModel;

import java.util.List;

import modeling.DestinationCard;
import modeling.ResourceCard;
import modeling.Route;

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
    public void drawDestCard(List<DestinationCard> c) {
        //super.drawDestCard(c);
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
