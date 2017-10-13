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
        setType("leaveGame");
    }

    private Game gameObject;
    private Player playerObject;

    @Override
    public CommandResult execute() {

       // boolean leaveCreatedSuccessful = ServerFacade.getInstance().leaveGame(gameObject, playerObject);
        boolean leaveCreatedSuccessful = false;
        CommandResult result;
        if (leaveCreatedSuccessful) {
            result = new CommandResult(true);
            result.setType(this.getType());
        } else {
            result = new CommandResult(false);
            result.setType(this.getType());

        }
        return result;
    }
}
