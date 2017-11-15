package command;

import ServerModel.ServerFacade;
import commandData.ClaimRouteCommandData;
import result.CommandResult;

/**
 * Created by korea on 10/28/2017.
 */

public class ClaimRouteCommand extends ClaimRouteCommandData implements ICommand {
    private ClaimRouteCommandData claimRouteCommandData;

    public ClaimRouteCommand(ClaimRouteCommandData data){
        super();
        this.claimRouteCommandData = data;
        setType("claimRoute");
    }

    @Override
    public CommandResult execute() {
        ServerFacade facade = ServerFacade.getInstance();
        CommandResult result;




        //Temporary Implementation. May need to change
        //facade.addCommandToList(claimRouteCommandData.getGameID(), claimRouteCommandData);
        //result = new CommandResult(true);
        result = ServerFacade.getInstance().claimRoute(this.claimRouteCommandData);


        return result;
    }
}
