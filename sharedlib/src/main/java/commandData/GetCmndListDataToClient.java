package commandData;

import java.util.Map;

import modeling.CommandList;
import modeling.GameCommandMap;

/**
 * Created by tyler on 10/23/2017.
 * This class takes the GameCommandMap and sends it to the client
 */

public class GetCmndListDataToClient extends Command{
    private GameCommandMap commandList;
    //takes in a map and wraps it up to send
    public GetCmndListDataToClient(Map<String,CommandList> commandList) {
        setType("getCommandList");
        this.commandList = new GameCommandMap(commandList);
    }


    public GameCommandMap getCommandList() {
        return commandList;
    }
}
