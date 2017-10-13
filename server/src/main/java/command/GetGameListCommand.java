package command;

import java.util.List;

import ServerModel.ServerFacade;
import commandData.GetGameListCommandData;
import modeling.Game;
import modeling.GameList;
import result.GetGameCommandResult;
import result.Result;

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
        ServerFacade facade = ServerFacade.getInstance();
        GameList gameList = ServerFacade.getInstance().getGameList();
        if(gameList == null) {
            result = new GetGameCommandResult(false, gameList, "failed");
            return  result;
        }
        else {
            GetGameListCommandData cmdData = new GetGameListCommandData();
            cmdData.setGameListLobby(gameList);
            result = new GetGameCommandResult(true, gameList, "Game Lists sent.");
            //result.setType(this.getType());
            return result;
        }
    }
}
