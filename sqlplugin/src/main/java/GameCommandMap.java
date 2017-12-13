import java.util.Map;

/**
 * Created by tyler on 10/23/2017.
 * Wrapper for the Map of commands for any particular game
 */

public class GameCommandMap {
    private Map<String, CommandList> commandList;
    public GameCommandMap(Map<String, CommandList> commandList) {
        this.commandList = commandList;
    }

    public Map<String, CommandList> getCommandList() {
        return commandList;
    }
}
