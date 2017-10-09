package command;

import ServerModel.ServerFacade;
import commandData.GetGameListCommandData;
import result.CommandResult;

/**
 * Created by Hwang on 9/29/2017.
 */

public class GetGameListCommand extends GetGameListCommandData implements ICommand{
    public GetGameListCommand(){
        super();
    }

    @Override
    public CommandResult execute() {
        try {
            //GetGameListCommand getGameCommandData = new GetGameListCommand();

            ServerFacade facade = ServerFacade.getInstance();

            CommandResult result = new CommandResult(true, facade.getGameList(), "Game List sent.");
            return result;
        }
        catch (NumberFormatException e) {
            CommandResult result = new CommandResult(false, null, "Error, not a number!");
            return result;
        }
    }
}
