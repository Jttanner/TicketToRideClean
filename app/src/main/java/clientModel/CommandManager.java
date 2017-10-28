package clientModel;

import android.util.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import clientCommands.ClientCommand;
import clientCommands.StartGameClient;
import commandData.Command;
import modeling.CommandList;
import modeling.Game;
import modeling.Player;

/**
 * Created by tyler on 10/24/2017.
 * The command manager, holds the commands for every game
 */

public class CommandManager {
    private static final String TAG = "CommandManager";
    /**The command map for every game*/
    private Map<String, CommandList> commandListMap = new HashMap<>();
    /**Sets the new commandListMap*/
    public void updateCommandLists(Map<String,CommandList> commandListMap){
        this.commandListMap = commandListMap;
        executeCurrCommands();
    }
    /**Executes the commands for the current game*/
    private void executeCurrCommands() {
        Game currGame = CModel.getInstance().getCurrGame();
        String playerName = CModel.getInstance().getMyUser().getUserName();
        Player myPlayer = currGame.getPlayer(playerName);
        //get the current command index. NOTE: This will need to be incremented properly in the model for this to work, use method
        //incrementUsersCommandIndex() in any appropriate method. Ex: toggleGameHasStarted()
        int commandIndex = myPlayer.getCommandIndex();
        if(this.commandListMap.size() > 0) {
            //grab new command list
            CommandList commandList = this.commandListMap.get(currGame.getGameID());
            List<Command> commands = commandList.getCommandList();
            for (int i = commandIndex; i < commands.size(); i++) {
                ClientCommand clientCommand = findCommandObject(commands.get(i));
                if (clientCommand != null) {
                    clientCommand.execute();
                    CModel.getInstance().incrementUsersCommandIndex();
                }
            }
        }
    }
    /**Returns the appropiate command object to execute for the client
     * @param command Finds correct command from the commandData given*/
    private ClientCommand findCommandObject(Command command) {
        if(command.getType().equals("startGame")) {
            return new StartGameClient();
        }
        else if(command.getType().equals("drawTrainCardDeck")) {

        }
        else if(command.getType().equals("drawTrainCardFaceUp")) {

        }
        else if(command.getType().equals("drawDestinationCard")) {

        }
        else if(command.getType().equals("claimRoute")) {

        }
        //TODO add new commands for the client here
        Log.d(TAG,"NULL command given");
        return  null;
    }

    public CommandList getACommandList(String gameID){
        return this.commandListMap.get(gameID);
    }
}
