package command;

import java.util.Map;

import ServerModel.ServerModel;
import commandData.GetCmndListDataServer;
import modeling.CommandList;

/**
 * Created by tyler on 10/23/2017.
 * WIP
 */

public class GetCommandListServer extends GetCmndListDataServer {
    public GetCommandListServer() {
        super();
    }

    /**Gets the CommandList to send to the Server*/
    public Map<String,CommandList> execute() {
        ServerModel model = ServerModel.getInstance();
        Map<String, CommandList> commandListMap = model.getCommandListMap();
        return commandListMap;

    }
}
