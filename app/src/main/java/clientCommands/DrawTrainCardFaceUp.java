package clientCommands;

import clientModel.CModel;
import clientModel.NotMyTurn;
import clientModel.OneCardDrawnState;
import commandData.DrawTrainCardFaceUpCommandData;
import modeling.ResourceCard;

/**
 * Created by korea on 10/27/2017.
 */

public class DrawTrainCardFaceUp implements ClientCommand {
    private String playerName;
    private String gameID;
    private ResourceCard resourceCard;
    public DrawTrainCardFaceUp (DrawTrainCardFaceUpCommandData data) {
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

            //Change the face up card on Client Side
            CModel.getInstance().upDateFaceUpPile();
    }

    @Override
    public String toString() {
//        return playerName + " drew a resource card from face up pile: " + findColor();
        return playerName + " drew a resource card from face up pile: ";
    }
//    public String findColor() {
//        return CModel.getInstance().getCurrGame().getResourceCardList().getFaceUpCard(position).getMyColor();
//    }
}
