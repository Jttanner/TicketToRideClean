package clientCommands;

import clientModel.CModel;

/**
 * Created by korea on 10/27/2017.
 */

public class PlaceTrainRoute implements ClientCommand {
    String playerName = CModel.getInstance().getUserPlayer().getPlayerName();
    String startCity;
    String endCity;
    @Override
    public void execute() {

        CModel.getInstance().updateCurrGameHistoryList(this.toString());
    }

    @Override
    public String toString() {
        return playerName + " claimed a route from " + startCity + " to " + endCity;
    }
}