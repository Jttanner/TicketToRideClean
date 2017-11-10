package clientCommands;

import clientModel.CModel;
import commandData.DrawTrainCardDeckCommandData;

/**
 * Created by korea on 10/27/2017.
 */

public class DrawTrainCardDeck implements ClientCommand {
    String playerName = CModel.getInstance().getUserPlayer().getPlayerName();
    String resourceCardColor;
    String gameID;

    public DrawTrainCardDeck (DrawTrainCardDeckCommandData data) {
        resourceCardColor = data.getResourceCardColor();
        gameID = data.getGame();
    }
    @Override
    public void execute() {

        CModel.getInstance().updateCurrGameHistoryList(this.toString(), gameID);
    }

    @Override
    public String toString() {
        return playerName + " drew a resource card from the deck: " + resourceCardColor;
    }
}
