package clientCommands;

import android.util.Log;

import java.util.List;

import clientModel.CModel;
import clientModel.CommandManager;
import commandData.Command;
import modeling.CommandList;
import modeling.GameCommandMap;

/**
 * Created by tyler on 10/24/2017.
 * This command replaces our GameMap in the Command Manager with the one the server has
 */

public class SetGameMap implements ClientCommand {
    /**TAg for our logger*/
    private static final String TAG = "SetGameMap";
    /**Our GameCommandMap*/
    private GameCommandMap gameCommandMap;

    private List<Command> commandList;

    private String gameID;

    public SetGameMap(List<Command> gameCommandMap, String gameID){
        this.commandList = gameCommandMap;
        this.gameID = gameID;
    }

    @Override
    public void execute() {
        Log.d(TAG,"Executing SetGameMap Command");
        CommandManager manager = CModel.getInstance().getCommandManager();
        try {
            manager.updateCommandLists(commandList, this.gameID);
        }catch (Exception e){
            //This won't break anything if we get a null command list
            Log.d(TAG,"Gave a null commandList: " + e.getMessage());
        }
    }
}
