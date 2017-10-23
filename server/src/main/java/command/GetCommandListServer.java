package command;

import java.util.Map;

import ServerModel.ServerModel;
import commandData.CommandList;
import commandData.GetCommandListData;
import modeling.Game;

/**
 * Created by tyler on 10/23/2017.
 * WIP
 */

public class GetCommandListServer extends GetCommandListData {
    public GetCommandListServer(String gameID) {
        super(gameID);
    }

    /**Gets the CommandList to send to the Server*/
    public CommandList execute() {
        ServerModel model = ServerModel.getInstance();
        Map<Game, CommandList> gameGameListMap = model.getCommandList();
        Game currGame = model.getGamesAsMap().get(this.getGameID());
        return gameGameListMap.get(currGame);

    }
}
