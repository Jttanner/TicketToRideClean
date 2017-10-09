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

        boolean leaveCreatedSuccessful = ServerFacade.getInstance().leaveGame(gameObject, playerObject);
        if (leaveCreatedSuccessful) {
            return new CommandResult(true, "", "");
        } else {
            return new CommandResult(false, "", "");
        }
    }
}
