package clientCommands;

import clientModel.CModel;
import commandData.DrawDestinationCardCommandData;

/**
 * Created by korea on 10/27/2017.
 */

public class DrawDestinationCards implements ClientCommand {
    //Need Player Name
    //How many he decided to keep
    String playerName;
    int numberOfCards;
    String gameID;

    public DrawDestinationCards(DrawDestinationCardCommandData data) {
        this.playerName = data.getPlayerName();
        this.gameID = data.getGameID();
    }

    @Override
    public void execute() {

        CModel.getInstance().updateCurrGameHistoryList(this.toString(),CModel.getInstance().getCurrGame().getGameID());
    }

    @Override
    public String toString() {
        return  playerName + " took " + numberOfCards + " destination cards";
    }
}
