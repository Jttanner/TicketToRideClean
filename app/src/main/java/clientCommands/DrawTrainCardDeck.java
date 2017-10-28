package clientCommands;

import clientModel.CModel;

/**
 * Created by korea on 10/27/2017.
 */

public class DrawTrainCardDeck implements ClientCommand {
    @Override
    public void execute() {

        CModel.getInstance().getUserPlayer().getPlayerName();
    }
}
