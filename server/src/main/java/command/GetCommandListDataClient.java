package command;

import commandData.Command;
import commandData.CommandList;

/**
 * Created by tyler on 10/23/2017.
 */

public class GetCommandListDataClient extends Command{
    private CommandList commandList;
    public GetCommandListDataClient(CommandList commandList) {
        setType("getCommandList");
        this.commandList = commandList;
    }


    public CommandList getCommandList() {
        return commandList;
    }
}
