package clientCommands;

import clientModel.CModel;
import commandData.ClaimRouteCommandData;

/**
 * Created by korea on 10/27/2017.
 */

public class PlaceTrainRoute implements ClientCommand {
    String playerName = CModel.getInstance().getUserPlayer().getPlayerName();
    String startCity;
    String endCity;
    String gameID;

    public PlaceTrainRoute (ClaimRouteCommandData data) {
        startCity = data.getStartCity();
        endCity = data.getEndCity();
        gameID = data.getGame();
    }
    @Override
    public void execute() {

        CModel.getInstance().updateCurrGameHistoryList(this.toString(), gameID);
    }

    @Override
    public String toString() {
        return playerName + " claimed a route from " + startCity + " to " + endCity;
    }
}