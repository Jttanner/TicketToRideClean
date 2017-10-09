package command;

import ServerModel.ServerFacade;
import commandData.GetGameListCommandData;
import result.GameList;
import result.GetGameCommandResult;

/**
 * Created by Hwang on 9/29/2017.
 */

public class GetGameListCommand extends GetGameListCommandData implements ICommand{
    public GetGameListCommand(){
        super();
    }

    @Override
    public GetGameCommandResult execute() {
        try {
            //GetGameListCommand getGameCommandData = new GetGameListCommand();

            ServerFacade facade = ServerFacade.getInstance();

            GetGameCommandResult result = new GetGameCommandResult(true, facade.getGameList(), "Game List sent.");
            return result;
        }
        catch (NumberFormatException e) {
            GetGameCommandResult result = new GetGameCommandResult(false, new GameList(null), "Error, not a number!");
            return result;
        }
    }
}
