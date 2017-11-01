package clientCommands;

import clientModel.CModel;
import commandData.DrawTrainCardFaceUpCommandData;

/**
 * Created by korea on 10/27/2017.
 */

public class DrawTrainCardFaceUp implements ClientCommand {
    String playerName = CModel.getInstance().getUserPlayer().getPlayerName();
    String resourceCardColor;
    public DrawTrainCardFaceUp (DrawTrainCardFaceUpCommandData data) {
        resourceCardColor = data.getResourceCardColor();
    }
    @Override
    public void execute() {

        CModel.getInstance().updateCurrGameHistoryList(this.toString(),CModel.getInstance().getCurrGame().getGameID());
    }

    @Override
    public String toString() {
        return playerName + " drew a resource card from face up pile: " + resourceCardColor;
    }
}
