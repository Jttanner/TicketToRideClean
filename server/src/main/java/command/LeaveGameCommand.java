package command;

import ServerModel.ServerFacade;
import commandData.LeaveGameCommandData;
import modeling.Game;
import modeling.Player;
import result.CommandResult;

/**
 * Created by Hwang on 9/29/2017.
 */

public class LeaveGameCommand extends LeaveGameCommandData implements ICommand {

    public LeaveGameCommand() {
        super();
    }

    private Game gameObject;
    private Player playerObject;

    @Override
    public CommandResult execute() {

        //CreateGameCommandData createGameCommandData = new CreateGameCommandData();

        //TODO: Finish the changes necessary here in the comment block to for the serverModel/serverFacade. Same for other commands

        //Create game sends a game object & A USER to the server
        /*Set<Player> players;
        boolean hasStarted;
        String gameID;
        String gameName;
        int maxPlayers;*/
        //Sends back a bool in an object telling if it successfully created a game or not
        //Game gameObject = createGameCommandData.getGameObject();

        boolean leaveCreatedSuccessful = ServerFacade.getInstance().leaveGame(gameObject, playerObject);
        if (leaveCreatedSuccessful) {
            return new CommandResult(true, "", "");
        } else {
            return new CommandResult(false, "", "");
        }
    }
}
