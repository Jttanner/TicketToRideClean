package clientModel;

import android.util.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import clientCommands.ClientCommand;
import clientCommands.ClaimDestinationCards;
import clientCommands.DrawDestinationCards;
import clientCommands.DrawTrainCard;
import clientCommands.EndGameCommand;
import clientCommands.EndTurnCommandClient;
import clientCommands.InitializeTurns;
import clientCommands.PlaceTrainRoute;
import clientCommands.StartGameClient;
import clientCommands.UpdateChatCommand;
import commandData.ChatCommandData;
import commandData.ClaimDestinationCardCommandData;
import commandData.ClaimInitialDestinationCardCommandData;
import commandData.ClaimRouteCommandData;
import commandData.Command;
import commandData.DrawDestinationCardCommandData;
import commandData.DrawTrainCardCommandData;
import commandData.IncrementCommandIndexCommandData;
import modeling.Player;
import servercomms.ServerProxy;

/**
 * Created by tyler on 10/24/2017.
 * The command manager, holds the commands for every game. This class handles the execution of commands that the client
 * receives from the server. An instance of this object is used to keep track of command lists for the current game for the
 * client.
 *
 */

public class CommandManager {
    /**
     * A String Tag for our Logger
     */
    private static final String TAG = "CommandManager";
    /**
     * Holds the command list for every game the user has been in
     */
    private Map<String, List<Command>> commandListMap = new HashMap<>();

    /**
     * Sets the new commandListMap of a game whose gameID is passed in then causes any new commands to be executed
     *
     * @param commandList The list of new commands given to us from the server
     * @param gameID      The GameID of the game whose commandlist we are going to update
     *
     * @pre commandList != null
     * @pre gameID is equal to the current game's ID
     * @pre currGame != null
     *
     * @exception NullPointerException
     *
     * @post The given Game's command list is updated and any new commands have been executed
     */
    public void updateCommandLists(List<Command> commandList, String gameID) throws NullPointerException {
        //if we didnt get a null command list execute them
        if (commandList != null && CModel.getInstance().getCurrGame().getGameID().equals(gameID)) {
            //put the command list in the map,replaces the old command list and then execute the list
            commandListMap.put(gameID, commandList);
            executeCurrCommands(commandList);
        }
    }

    /**
     * Executes the commands for the current game
     *
     * @param commandList The list of commands to execute from the server
     * @pre commandList != null
     * @pre User's player != null
     * @pre User's commandIndex <= commandList.size()
     *
     * @post User's commandIndex == commandList.size()
     */
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
                    IncrementCommandIndexCommandData incrementCommandIndexCommandData = new IncrementCommandIndexCommandData(CModel.getInstance().getCurrGame().getGameID(), myPlayer.getPlayerName());
                    ServerProxy.getInstance().sendCommand(incrementCommandIndexCommandData);
                    CModel.getInstance().incrementUsersCommandIndex();
                    System.out.println("Executing: " + clientCommand.getClass());
                    clientCommand.execute();

                }
            }
        }
    }

    /**
     * Returns the appropriate command object from the CommandData object given to execute for the client
     *
     * @param command The commandData object given which holds the type of Client Command to be created
     *
     * @pre command != null
     * @pre command.getType() is not empty
     * @pre command.getType() returns a valid command type that is checked for below in the method
     *
     * @post A valid Client Command is returned
     *
     * @return ClientCommand The Client command that matched the given commandData
     */
    private ClientCommand findCommandObject(Command command) {
        System.out.println(command.getClass());

        switch (command.getType()) {
            case "startGame":
                return new StartGameClient();
            /*case "drawTrainCardDeck":
                return new DrawTrainCardDeck((DrawTrainCardDeckCommandData) command);*/
            case "drawTrainCard":
                return new DrawTrainCard((DrawTrainCardCommandData) command);
            case "drawDestinationCards":
                return new DrawDestinationCards((DrawDestinationCardCommandData) command);
            case "claimInitialDestinationCards":
                return new InitializeTurns((ClaimInitialDestinationCardCommandData) command);
            case "claimDestinationCards":
                return new ClaimDestinationCards((ClaimDestinationCardCommandData) command);
            case "claimRoute":
                return new PlaceTrainRoute((ClaimRouteCommandData) command);
            case "endTurn":
                return new EndTurnCommandClient();
            case "addChat":
                if (command instanceof ChatCommandData) {
                    return new UpdateChatCommand((ChatCommandData) command);
                }
                break;
            case "EndGame":
                return new EndGameCommand();
                //break;
            //TODO add new commands for the client here
        }

        Log.d(TAG, "NULL or Unsupported command given");
        return null;
    }

    /**
     * Gets the commandList associated with the given GameID in the map
     * The Command list is only updated for the current game the user is in. So getting the command list
     * of a game the user is not in will not give you an command list that is current
     *
     * @param gameID The gameID whose command list we will return
     *
     * @pre gameID != ""
     * @pre gameID is associated with an existing game
     *
     * @post If preconditions are met this method would return a existing game's command list
     *
     * @return List<Command> All the commands associated with a Game object
     * @exception Exception Thrown if you try to access a gameid's command list that isn't in the map
     */
    public List<Command> getACommandList(String gameID) throws Exception {
        try {
            return this.commandListMap.get(gameID);
        } catch (Exception e) {
            Log.d(TAG, "Exception: " + e.getMessage());
            throw new Exception();
        }
    }
}
