package ServerModel;

import java.util.ArrayList;
import java.util.List;

import commandData.Command;

/**
 * Created by tyler on 10/23/2017.
 */

public class CommandList {
    private List<Command> commandList = new ArrayList<>();

    public void setCommandList(List<Command> commandList) {
        this.commandList = commandList;
    }

    public CommandList() {
        this.commandList = new ArrayList<>();
    }
    /**Lets you add to the command list*/
    public void addToList(Command command) {
        commandList.add(command);
    }
    /**Lets you add to the command list*/
    public void addToList(List<Command> commands) {
        commandList.addAll(commands);
    }

    /**Clears the list*/
    public void clearList(){
        commandList.clear();
    }

    /**Gets size of list*/
    public int getSize(){
        return commandList.size();
    }

    /**Gets a unmodifiable list
     * @return List<ServerModel.Command>*/
    public List<Command> getCommandList(){
        return commandList;
    }


}
