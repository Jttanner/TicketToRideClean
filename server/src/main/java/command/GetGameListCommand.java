package command;

import ServerModel.ServerFacade;
import commandData.GetGameListCommandData;
import result.CommandResult;

/**
 * Created by Hwang on 9/29/2017.
 */

public class GetGameListCommand extends GetGameListCommandData implements ICommand {
    public GetGameListCommand() {
        super();
        setType("getGameList");
    }

    @Override
    public CommandResult execute() {
        ServerFacade facade = ServerFacade.getInstance();

        CommandResult result = new CommandResult(true, facade.getGameList(), "Game List sent.");
        result.setType(this.getType());
        return result;
    }
}
