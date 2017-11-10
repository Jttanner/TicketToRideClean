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
            //Check if the player already exists in the game



            //JoinGameCommandData joinGameCommandData = new JoinGameCommandData();
            String gameID = this.getGameID();
            Game gameToJoin = ServerFacade.getInstance().joinGame(getUser(),gameID);
            //If we joined the game successfully we will not a not null player object below
            Player usersPlayer = gameToJoin.getPlayer(getUser().getUserName());
            if(usersPlayer != null) {
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
