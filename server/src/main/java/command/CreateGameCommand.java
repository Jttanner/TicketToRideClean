package command;

import ServerModel.ServerFacade;
import commandData.CreateGameCommandData;
import result.CommandResult;

/**
 * Created by Hwang on 9/29/2017.
 */

public class CreateGameCommand extends CreateGameCommandData implements ICommand {

    public CreateGameCommand() {
        super();
        setType("createGame");
    }

    @Override
    public CommandResult execute() {


        boolean gameCreatedSuccessful = ServerFacade.getInstance().createGame(getGame());
        if (gameCreatedSuccessful) {
            CommandResult result = new CommandResult(true, "", "");
            result.setType("createGame");
            return result;
        } else {
            CommandResult result = new CommandResult(false, "", "");
            result.setType("createGame");
            return result;
        }
    }
}
