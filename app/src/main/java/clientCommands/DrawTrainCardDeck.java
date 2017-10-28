package clientCommands;

import clientModel.CModel;

/**
 * Created by korea on 10/27/2017.
 */

public class DrawTrainCardDeck implements ClientCommand {
    String playerName = CModel.getInstance().getUserPlayer().getPlayerName();

    @Override
    public void execute() {

        CModel.getInstance().updateCurrGameHistoryList(this.toString());
    }

    @Override
    public String toString() {
        return playerName + " drew a resource card from the deck";
    }
}
