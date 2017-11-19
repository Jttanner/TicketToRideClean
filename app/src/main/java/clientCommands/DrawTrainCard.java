package clientCommands;

import clientModel.CModel;
import commandData.DrawTrainCardCommandData;
import modeling.ResourceCard;

/**
 * Created by korea on 10/27/2017.
 */

public class DrawTrainCard implements ClientCommand {
    private String playerName;
    private String gameID;
    private ResourceCard resourceCard;
    public DrawTrainCard(DrawTrainCardCommandData data) {
        this.playerName = data.getPlayerName();
        this.gameID = data.getGameID();
        this.resourceCard = data.getResourceCard();
    }
    @Override
    public void execute() {
        //Update the Game History
        CModel.getInstance().updateCurrGameHistoryList(this.toString(), gameID);

        //Add card to player on Client Side and deletes that card from the deck
        CModel.getInstance().getCurrGame().getPlayer(playerName).addResourceCard(CModel.getInstance().getCurrGame().getResourceCardList().drawCard(resourceCard.getCardID()));
        CModel.getInstance().updatePlayerStatsView();
        //Change the face up card on Client Side
        CModel.getInstance().upDateFaceUpPile();
        //Set my new state

        //will only end our turn if we are in EndMyTurn state
        CModel.getInstance().getCurrGameState().endTurn();


    }

    @Override
    public String toString() {
        return playerName + " drew a resource card from face up pile: " + resourceCard.getMyColor();
    }

}
