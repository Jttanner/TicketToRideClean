package command;

import ServerModel.ServerFacade;
import commandData.JoinGameCommandData;
import modeling.User;
import result.CommandResult;

/**
 * Created by Hwang on 9/29/2017.
 */

public class JoinGameCommand extends JoinGameCommandData implements  ICommand{

    public JoinGameCommand(String gameID, User user){
        super(gameID, user);
    }


   // @Override
    public CommandResult execute() {
        try {
            //JoinGameCommandData joinGameCommandData = new JoinGameCommandData();
            String gameID = this.getGameID();
            ServerFacade.getInstance().joinGame(getUser(),gameID);
            CommandResult result = new CommandResult(true, gameID, null);
            result.setType("joinGame");
            return result;
        }
        catch (NumberFormatException e) {
            CommandResult result = new CommandResult(false, null, "Error, not a number!");
            result.setType("joinGame");
            return result;
        }
    }
}
