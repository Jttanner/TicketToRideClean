package command;

import ServerModel.ServerFacade;
import command.*;
import commandData.*;
import result.*;

/**
 * Created by Hwang on 9/29/2017.
 */

public class CreateGameCommand extends CreateGameCommandData implements ICommand{

    public CreateGameCommand(){
        super();
    }

    @Override
    public Result execute() {
        try {
            CreateGameCommandData createGameCommandData = new CreateGameCommandData();

            //TODO: Finish the changes necessary here in the comment block to for the serverModel/serverFacade. Same for other commands
            /*
            String gameName = createGameCommandData.getGameName();
            int gameID = createGameCommandData.getPlayerMax();
            boolean gameCreatedSuccessful = ServerFacade.getInstance().createGameLobby(gameName, gameID);
            if (gameCreatedSuccessful == true){
                return new Result (true, "", "");
            }
            else {
                return new Result (false, "", "");
            }
            */
            return null;
        }
        catch (NumberFormatException e) {
            Result result = new Result(false, null, "Error, not a number!");
            return result;
        }
    }
}
