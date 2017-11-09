package clientModel;

import java.util.List;

import modeling.DestinationCard;
import modeling.ResourceCard;
import modeling.Route;

/**
 * Created by tyler on 11/9/2017.
 * The Game State abstract class
 */

public abstract class GameState {
    /**Deals with sending commands to the server when the player has drawn a resource card
     * @param c The Resource card drawn*/
    void drawResourceCard(ResourceCard c){

    }
    /**Deals with sending commands to the server when the player has drawn a Destinaton card
     * @param c The Destination cards drawn*/
    void drawDestCard(List<DestinationCard> c){

    }
    /**Handles ending the current game state and moving on to the next*/
    void endState(){

    }
    /**Handles what to do from a certain phase when the game ends*/
    void gameEnded(){

    }
    /**Handles claiming a route in any certain phase
     * @param r  The Route claimed*/
    void claimRoute(Route r){

    }
}
