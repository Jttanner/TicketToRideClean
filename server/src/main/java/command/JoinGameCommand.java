package command;

import ServerModel.ServerFacade;
import ServerModel.ServerModel;
import commandData.JoinGameCommandData;
import modeling.Game;
import modeling.User;
import result.CommandResult;
import result.JoinGameCommandResult;

/**
 * Created by Hwang on 9/29/2017.
 */

public class JoinGameCommand extends JoinGameCommandData implements  ICommand{

    public JoinGameCommand(String gameID, User user){
        super(gameID, user);
        setType("joinGame");
    }


   // @Override
    public CommandResult execute() {
        try {
            //JoinGameCommandData joinGameCommandData = new JoinGameCommandData();
            String gameID = this.getGameID();
            Game game = ServerModel.getInstance().getGamesAsMap().get(gameID);
            boolean success = ServerFacade.getInstance().joinGame(getUser(),gameID);
            if(success) {
                CommandResult result = new CommandResult(true, new JoinGameCommandResult(gameID), null);
                result.setType("joinGame");
                return result;
            }
            else{
                CommandResult result = new CommandResult(false, new JoinGameCommandResult(gameID),null);
                result.setType("joinGame");
                return result;
            }
        }
        catch (NumberFormatException e) {
            CommandResult result = new CommandResult(false, null, "Error, not a number!");
            result.setType("joinGame");
            return result;
        }
    }
}
