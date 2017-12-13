package ServerModel;

import java.util.List;

import modeling.ResourceCard;

/**
 * Created by korea on 10/28/2017.
 */

public class DrawTrainCardCommandData extends Command {
    //Data Members
    private String playerName;
    private String gameID;
    private ResourceCard resourceCard;
    private List<ResourceCard> cardList;

    //Constructors
    public DrawTrainCardCommandData(String playerName, String gameID, ResourceCard resourceCard) {
        setType("drawTrainCard");
        this.playerName = playerName;
        this.gameID = gameID;
        this.resourceCard = resourceCard;

    }
    public DrawTrainCardCommandData() {}

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

    public List<ResourceCard> getCardList() {
        return cardList;
    }

    public void setCardList(List<ResourceCard> cardList) {
        this.cardList = cardList;
    }
}
