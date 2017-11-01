package clientCommands;

import clientModel.CModel;
import commandData.DrawTrainCardFaceUpCommandData;

/**
 * Created by korea on 10/27/2017.
 */

public class DrawTrainCardFaceUp implements ClientCommand {
    String playerName = CModel.getInstance().getUserPlayer().getPlayerName();
    String resourceCardColor;
    String gameID;
    public DrawTrainCardFaceUp (DrawTrainCardFaceUpCommandData data) {
        resourceCardColor = data.getResourceCardColor();
        gameID = data.getGame();
    }
    @Override
    public void execute() {

        CModel.getInstance().updateCurrGameHistoryList(this.toString(), gameID);
    }

    @Override
    public String toString() {
        return playerName + " drew a resource card from face up pile: " + resourceCardColor;
    }
}
