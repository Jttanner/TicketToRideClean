package clientModel;

import android.util.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import clientCommands.ClientCommand;
import clientCommands.DrawTrainCardDeck;
import clientCommands.DrawTrainCardFaceUp;
import clientCommands.PlaceTrainRoute;
import clientCommands.StartGameClient;
import clientCommands.UpdateChatCommand;
import commandData.ChatCommandData;
import commandData.ClaimRouteCommandData;
import commandData.Command;
import commandData.DrawTrainCardDeckCommandData;
import commandData.DrawTrainCardFaceUpCommandData;
import modeling.CommandList;
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
    public void updateCommandLists(CommandList commandList,String gameID){
        //if we didnt get a null command list execute them
        if(commandList != null) {
            commandListMap.put(gameID, commandList);
            executeCurrCommands(commandList);
        }
    }
    /**Executes the commands for the current game*/
    private void executeCurrCommands(CommandList commandList) {
        Player myPlayer = CModel.getInstance().getUserPlayer();
        //get the current command index. NOTE: This will need to be incremented properly in the model for this to work, use method
        //incrementUsersCommandIndex() in any appropriate method. Ex: toggleGameHasStarted()
        int commandIndex = myPlayer.getCommandIndex();

//            CommandList commandList = this.commandListMap.get(currGame.getGameID());
        if (commandList.getCommandList().size() > 0) {
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

        System.out.println(command.getClass());

        if(command.getType().equals("startGame")) {
            return new StartGameClient();
        }
        else if(command.getType().equals("drawTrainCardDeck")) {
            return new DrawTrainCardDeck((DrawTrainCardDeckCommandData)command);
        }
        else if(command.getType().equals("drawTrainCardFaceUp")) {
            return new DrawTrainCardFaceUp((DrawTrainCardFaceUpCommandData)command);
        }
        else if(command.getType().equals("drawDestinationCard")) {
            //
        }
        else if(command.getType().equals("claimRoute")) {
            return new PlaceTrainRoute((ClaimRouteCommandData)command);
        }
        else if(command.getType().equals("addChat")){
            if(command instanceof  ChatCommandData) {
                return new UpdateChatCommand((ChatCommandData) command);
            }
        }
        //TODO add new commands for the client here
        Log.d(TAG,"NULL command given");
        return  null;
    }

    public CommandList getACommandList(String gameID){
        return this.commandListMap.get(gameID);
    }
}
