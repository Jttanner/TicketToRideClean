package clientCommands;

import clientModel.CModel;
import commandData.ClaimRouteCommandData;

/**
 * Created by korea on 10/27/2017.
 */

public class PlaceTrainRoute implements ClientCommand {
    private String playerName = CModel.getInstance().getUserPlayer().getPlayerName();
    private String startCity;
    private String endCity;
    private String gameID;

    public PlaceTrainRoute (ClaimRouteCommandData data) {
        startCity = data.getStartCity();
        endCity = data.getEndCity();
        gameID = data.getGameName();
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