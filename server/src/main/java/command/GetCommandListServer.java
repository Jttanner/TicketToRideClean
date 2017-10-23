package command;

import java.util.Map;

import ServerModel.ServerModel;
import commandData.GetCommandListData;
import modeling.CommandList;

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
        Map<String, CommandList> commandListMap = model.getCommandListMap();
        CommandList commandList = commandListMap.get(this.getGameID());
        return commandList;

    }
}
