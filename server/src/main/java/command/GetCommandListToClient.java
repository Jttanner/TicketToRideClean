package command;

import java.util.Map;

import commandData.Command;
import modeling.CommandList;
import modeling.GameCommandMap;

/**
 * Created by tyler on 10/23/2017.
 */

public class GetCommandListToClient extends Command{
    private GameCommandMap commandList;
    //takes in a map and wraps it up to send
    public GetCommandListToClient(Map<String,CommandList> commandList) {
        setType("getCommandList");
        this.commandList = new GameCommandMap(commandList);
    }


    public GameCommandMap getCommandList() {
        return commandList;
    }
}
