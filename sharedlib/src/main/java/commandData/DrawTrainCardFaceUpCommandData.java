package commandData;

import modeling.Game;
import modeling.ResourceCard;

/**
 * Created by korea on 10/28/2017.
 */

public class DrawTrainCardFaceUpCommandData extends Command {
    //Data Members
    private String playerName;
    private String gameID;
    private ResourceCard resourceCard;

    //Constructors
    public DrawTrainCardFaceUpCommandData (String playerName, String gameID, ResourceCard resourceCard) {
        setType("drawTrainCardFaceUp");
        this.playerName = playerName;
        this.gameID = gameID;
        this.resourceCard = resourceCard;

    }
    public DrawTrainCardFaceUpCommandData () {}

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
    public void setGameID(String game) {
        this.gameID = game;
    }
    public ResourceCard getResourceCard() {
        return resourceCard;
    }
    public void setResourceCard(ResourceCard resourceCard) {
        this.resourceCard = resourceCard;
    }
}
