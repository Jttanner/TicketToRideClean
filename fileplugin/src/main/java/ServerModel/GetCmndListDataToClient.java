package ServerModel;

import java.util.ArrayList;
import java.util.List;

import commandData.Command;

/**
 * Created by tyler on 10/23/2017.
 * This class takes the ServerModel.GameCommandMap and sends it to the client
 */

public class GetCmndListDataToClient extends Command {

    private List<Command> returnCommandList = new ArrayList<>();
    private String gameId;
    //takes in a map and wraps it up to send
    public GetCmndListDataToClient(List<Command> commandList, String gameID) {
        setType("getCommandList");
        this.gameId = gameID;
        returnCommandList = commandList;
//        this.commandList = new ServerModel.GameCommandMap(returnCommandList);
    }


//    public ServerModel.GameCommandMap getCommandList() {
//        return commandList;
//    }

    public List<Command> getReturnCommandList() {
        return returnCommandList;
    }

    public void setReturnCommandList(List<Command> returnCommandList) {
        this.returnCommandList = returnCommandList;
    }

    public String getGameId() {
        return gameId;
    }
}
