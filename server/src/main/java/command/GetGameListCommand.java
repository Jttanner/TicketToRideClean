package command;

import ServerModel.ServerFacade;
import commandData.GetGameListCommandData;
import modeling.GameList;
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
        GetGameCommandResult result;
        GameList gameList = ServerFacade.getInstance().getGameList();
        if(gameList == null) {
            result = new GetGameCommandResult(false, "failed");
            return  result;
        }
        else {
            //TODO this code commented out seems to do nothing
            //GetGameListCommandData cmdData = new GetGameListCommandData();
            //cmdData.setGameListLobby(gameList);
            result = new GetGameCommandResult(true, gameList, "Game Lists sent.");
            return result;
        }
    }
}
