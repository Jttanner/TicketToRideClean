package clientModel;

import java.util.List;

import commandData.EndTurnCommandData;
import modeling.DestinationCard;
import modeling.ResourceCard;
import modeling.Route;
import servercomms.ServerProxy;

/**
 * Created by tyler on 11/9/2017.
 * The Game State abstract class
 */

public abstract class GameState {
    /**Deals with sending commands to the server when the player has drawn a resource card
     * @param resourceCard The Resource card drawn*/
    public void drawResourceCard(ResourceCard resourceCard){

    }
    /**Deals with sending commands to the server when the player has drawn a Destinaton card
     * @param c The Destination cards drawn*/
    void drawDestCard(List<DestinationCard> c){

    }
    /**Handles ending the current game state and moving on to the next*/
    void endTurn(){
        CModel cModel = CModel.getInstance();
        EndTurnCommandData data = new EndTurnCommandData(cModel.getCurrGame().getGameID(),cModel.getUserPlayer().getPlayerName());
        ServerProxy.getInstance().sendCommand(data);
        //TODO SERVERSIDE
    }
    /**Handles what to do from a certain phase when the game ends*/
    void gameEnded(){

    }
    /**Handles claiming a route in any certain phase
     * @param r  The Route claimed*/
    void claimRoute(Route r){

    }
}
