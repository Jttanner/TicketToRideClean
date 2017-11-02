package clientModel;

import android.util.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import clientCommands.ClientCommand;
import clientCommands.DrawDestinationCards;
import clientCommands.DrawTrainCardDeck;
import clientCommands.DrawTrainCardFaceUp;
import clientCommands.PlaceTrainRoute;
import clientCommands.StartGameClient;
import clientCommands.UpdateChatCommand;
import commandData.ChatCommandData;
import commandData.ClaimRouteCommandData;
import commandData.Command;
import commandData.DrawDestinationCardCommandData;
import commandData.DrawTrainCardDeckCommandData;
import commandData.DrawTrainCardFaceUpCommandData;
import modeling.CommandList;
import modeling.Player;

/**
 * Created by tyler on 10/24/2017.
 * The command manager, holds the commands for every game.
 */

public class CommandManager {
    /**A String Tag for our Logger*/
    private static final String TAG = "CommandManager";
    /**The command map for every game*/
    private Map<String, List<Command>> commandListMap = new HashMap<>();
    /**Sets the new commandListMap of a game whose gameID is passed in
     * @param commandList The list of new commands given to us from the server
     * @param gameID The GameID of the game whose commandlist we are going to update*/
    public void updateCommandLists(List<Command> commandList,String gameID){
        //if we didnt get a null command list execute them
        if(commandList != null) {
            //put the command list in the map,replaces the old command list and then execute the list
            commandListMap.put(gameID, commandList);
            executeCurrCommands(commandList);
        }
    }
    /**Executes the commands for the current game
     * @param commandList The list of commands to execute from the server
     *
     * {@invariant commadList != null}
     *
     * @pre commandList != null
     * @pre User's player != null
     * @pre User's commandIndex <= commandList.size()
     *
     * @post User's commandIndex == commandList.size()
     * */
    private void executeCurrCommands(List<Command> commandList) {
        Player myPlayer = CModel.getInstance().getUserPlayer();
        //get the current command index.
        int commandIndex = myPlayer.getCommandIndex();
        //if there is anything to execute, do so
        if (commandList.size() > 0) {
            for (int i = commandIndex; i < commandList.size(); i++) {
                //return the appropiate client command
                ClientCommand clientCommand = findCommandObject(commandList.get(i));
                if (clientCommand != null) {
                    clientCommand.execute();
                    CModel.getInstance().incrementUsersCommandIndex();
                }
            }
        }
    }
    /**Returns the appropriate command object from the CommandData object given to execute for the client
     * @param command Finds correct command from the commandData given
     *
     * @pre command != null
     * @pre command.getType() is not empty but gives back a String representing a type of command
     *
     * @post
     * @return ClientCommand The Client command that matched the given commandData*/
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
            return  new DrawDestinationCards((DrawDestinationCardCommandData)command);
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
        Log.d(TAG,"NULL or Unsupported command given");
        return  null;
    }
    /**Gets the commandList associated with the given GameID in the map
     * @param gameID The gameID whose commandlist we will return
     *
     * @pre gameID != ""
     *
     * @return List<Command> All the commands associated with a Game object*/
    public List<Command> getACommandList(String gameID){
        return this.commandListMap.get(gameID);
    }
}
