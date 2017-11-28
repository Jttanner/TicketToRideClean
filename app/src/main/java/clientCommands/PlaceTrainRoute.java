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
    private String claimedColor;
    private boolean isWild;

    public PlaceTrainRoute (ClaimRouteCommandData data) {
        playerName = data.getPlayerName();
        startCity = data.getStartCity();
        endCity = data.getEndCity();
        gameID = data.getGameID();
        //routeColor = data.getRouteColor();
        routeColor = data.isWild() ? "Wild" : data.getRouteColor();
        claimedColor = data.getOriginalTrackColor();
        //isWild = data.isWild();
    }
    @Override
    public void execute() {
        RouteList routeList = CModel.getInstance().getCurrGame().getUnclaimedRouteList();
        Route claimedRoute = routeList.getAvailableRoute(startCity, endCity, routeColor);
        claimedRoute.setOriginalTrackColor(claimedColor);
        //if (isWild){
            //claimedRoute = routeList.getAvailableRoute(startCity, endCity, "Wild");
        //} else{
            //claimedRoute
        //}

        CModel.getInstance().updateRoutes(CModel.getInstance().getCurrGame(), claimedRoute, CModel.getInstance().getCurrGame().getPlayer(playerName));
        //Update the Game History
        //CModel.getInstance().setCurrGameState(new EndMyTurn());
        //ends my turn
        CModel.getInstance().getCurrGameState().endTurn();
        CModel.getInstance().updateCurrGameHistoryList(this.toString(), gameID);
    }

    @Override
    public String toString() {
        return playerName + " claimed a route from " + startCity + " to " + endCity;
    }
}