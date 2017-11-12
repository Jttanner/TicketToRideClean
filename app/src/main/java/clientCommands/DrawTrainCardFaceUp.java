package clientCommands;

import clientModel.CModel;
import commandData.DrawTrainCardFaceUpCommandData;
import modeling.ResourceCard;

/**
 * Created by korea on 10/27/2017.
 */

public class DrawTrainCardFaceUp implements ClientCommand {
    private String playerName;
    private String gameID;
    private String resourceCardID;
    private int position;
    public DrawTrainCardFaceUp (DrawTrainCardFaceUpCommandData data) {
        this.playerName = data.getPlayerName();
        this.gameID = data.getGameID();
        this.resourceCardID = data.getResourceCardID();
        this.position = data.getPosition();
    }
    @Override
    public void execute() {
        resourceCardID = CModel.getInstance().getCurrGame().getResourceCardList().getFaceUpCard(position).getCardID();

        //Update the Game History
        CModel.getInstance().updateCurrGameHistoryList(this.toString(), gameID);

        //Add card to player
        CModel.getInstance().getCurrGame().getPlayer(playerName).addResourceCard(CModel.getInstance().getCurrGame().getResourceCardList().getFaceUpCard(position));

        //Change the face up card
        CModel.getInstance().upDateFaceUpPile(position);

    }

    @Override
    public String toString() {
        return playerName + " drew a resource card from face up pile: " + findColor();
    }
    public String findColor() {
        return CModel.getInstance().getCurrGame().getResourceCardList().getFaceUpCard(position).getMyColor();
    }
}
