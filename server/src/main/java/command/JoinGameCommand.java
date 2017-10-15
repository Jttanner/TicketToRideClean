package command;

import ServerModel.ServerFacade;
import commandData.JoinGameCommandData;
import modeling.Game;
import modeling.Player;
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
            //Game game = ServerModel.getInstance().getGamesAsMap().get(gameID);
            Game gameToJoin = ServerFacade.getInstance().joinGame(getUser(),gameID);
            //So the last player added to this list would be the user if we were able to join successfully
            Player lastPlayerAdded = gameToJoin.getPlayers().get(gameToJoin.getPlayers().size()-1);
            if(lastPlayerAdded.getUserName().equals(this.getUser().getUserName())) {
                CommandResult result = new JoinGameCommandResult(true,gameToJoin);
                result.setType("joinGame");
                return result;
            }
            else{
                CommandResult result = new JoinGameCommandResult(false,gameToJoin);
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
