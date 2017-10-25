package command;

import java.util.Map;

import ServerModel.ServerModel;
import commandData.GetCmndDataFromServer;
import modeling.CommandList;

/**
 * Created by tyler on 10/23/2017.
 * The command which returns the command list from the server
 * WIP
 */

public class GetCmndListServer extends GetCmndDataFromServer {
    public GetCmndListServer() {
        super();
    }

    /**Gets the CommandList to send to the Server*/
    public Map<String,CommandList> execute() {
        ServerModel model = ServerModel.getInstance();
        return model.getCommandListMap();

    }
}
