package command;

import ServerModel.ServerFacade;
import ServerModel.ServerModel;
import commandData.ClaimRouteCommandData;
import result.CommandResult;

/**
 * Created by korea on 10/28/2017.
 */

public class ClaimRouteCommand extends ClaimRouteCommandData implements ICommand {
    private ClaimRouteCommandData claimRouteCommandData;

    /*private String startCity;
    private String endCity;
    private String gameID;
    private String playerName;
    private String routeColor;
    private int distance;*/

    public ClaimRouteCommand(ClaimRouteCommandData data){
        super();
        this.claimRouteCommandData = data;
        setType("claimRoute");
        setStartCity(data.getStartCity());
        setEndCity(data.getEndCity());
        setGameID(data.getGameID());
        setPlayerName(data.getPlayerName());
        setRouteColor(data.getRouteColor());
        setDistance(data.getDistance());
        setWild(data.isWild());
    }

    @Override
    public CommandResult execute() {
        ServerFacade facade = ServerFacade.getInstance();
        CommandResult result;




        //Temporary Implementation. May need to change
        //facade.addCommandToList(claimRouteCommandData.getGameID(), claimRouteCommandData);
        //result = new CommandResult(true);
        result = ServerFacade.getInstance().claimRoute(this.claimRouteCommandData);

        if(result.isSuccess()){
            facade.addCommandToList(claimRouteCommandData.getGameID(), this);
        }

        return result;
    }

    public ClaimRouteCommandData getClaimRouteCommandData() {
        return claimRouteCommandData;
    }

    public void setClaimRouteCommandData(ClaimRouteCommandData claimRouteCommandData) {
        this.claimRouteCommandData = claimRouteCommandData;
    }

}
