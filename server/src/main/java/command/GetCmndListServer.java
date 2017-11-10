package command;

import java.util.List;

import ServerModel.ServerModel;
import commandData.Command;
import commandData.GetCmndDataFromServer;
import modeling.CommandList;

/**
 * Created by tyler on 10/23/2017.
 * The command which returns the command list from the server
 * WIP
 */

public class GetCmndListServer extends GetCmndDataFromServer {



    public GetCmndListServer(String gameid) {
        super(gameid);
    }

    /**Gets the CommandList to send to the Server*/
    public List<Command> execute() {
        ServerModel model = ServerModel.getInstance();
        //return model.getCommandListMap();
        return model.getCommandListMap().get(getGameId());
    }
}
