package command;

import ServerModel.ServerFacade;
import commandData.GetGameListCommandData;
import result.GetGameCommandResult;

/**
 * Created by Hwang on 9/29/2017.
 */

public class GetGameListCommand extends GetGameListCommandData implements ICommand {
    public GetGameListCommand() {
        super();
        setType("getGameList");
    }

    @Override
    public GetGameCommandResult execute() {
        ServerFacade facade = ServerFacade.getInstance();

        GetGameCommandResult result = new GetGameCommandResult(true, facade.getGameList(), "Game List sent.");
        result.setType(this.getType());
        return result;
    }
}
