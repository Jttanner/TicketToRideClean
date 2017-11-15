package clientCommands;

import clientModel.CModel;
import commandData.ClaimRouteCommandData;
import modeling.Route;
import modeling.RouteList;

/**
 * Created by korea on 10/27/2017.
 */

public class PlaceTrainRoute implements ClientCommand {
    private String playerName;
    private String startCity;
    private String endCity;
    private String gameID;
    private String routeColor;

    public PlaceTrainRoute (ClaimRouteCommandData data) {
        playerName = data.getPlayerName();
        startCity = data.getStartCity();
        endCity = data.getEndCity();
        gameID = data.getGameID();
        routeColor = data.getRouteColor();
    }
    @Override
    public void execute() {
        RouteList routeList = CModel.getInstance().getCurrGame().getUnclaimedRouteList();
        Route claimedRoute = routeList.getAvailableRoute(startCity, endCity, routeColor);
        CModel.getInstance().updateRoutes(CModel.getInstance().getCurrGame(), claimedRoute, CModel.getInstance().getUserPlayer());
    }

    @Override
    public String toString() {
        return playerName + " claimed a route from " + startCity + " to " + endCity;
    }
}