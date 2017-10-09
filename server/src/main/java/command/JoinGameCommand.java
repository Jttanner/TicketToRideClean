package command;

import commandData.JoinGameCommandData;
import modeling.User;
import result.CommandResult;

/**
 * Created by Hwang on 9/29/2017.
 */

public class JoinGameCommand extends JoinGameCommandData implements  ICommand{

    public JoinGameCommand(int userID, User user){
        super(userID, user);
    }


   // @Override
    public CommandResult execute() {
        try {
            //JoinGameCommandData joinGameCommandData = new JoinGameCommandData();
            int gameID = this.getGameID();

            CommandResult result = new CommandResult(true, gameID, null);
            return result;
        }
        catch (NumberFormatException e) {
            CommandResult result = new CommandResult(false, null, "Error, not a number!");
            return result;
        }
    }
}
