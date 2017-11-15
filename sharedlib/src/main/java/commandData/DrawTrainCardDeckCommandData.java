package commandData;

import modeling.Game;
import modeling.ResourceCard;

/**
 * Created by korea on 10/28/2017.
 */

public class DrawTrainCardDeckCommandData extends Command {

    //Data Members
    private String playerName;
    private String gameID;
    private ResourceCard resourceCard;

    //Constructor
    public DrawTrainCardDeckCommandData (String playerName, String gameID, ResourceCard resourceCard)  {
        setType("drawTrainCardDeck");
        this.gameID = gameID;
        this.resourceCard = resourceCard;
        this.playerName = playerName;
    }
    public DrawTrainCardDeckCommandData () {}

    //Getters and Setters
    public String getPlayerName() {
        return playerName;
    }
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    public String getGameID() {
        return gameID;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }

    public ResourceCard getResourceCard() {
        return resourceCard;
    }

    public void setResourceCard(ResourceCard resourceCard) {
        this.resourceCard = resourceCard;
    }
}
