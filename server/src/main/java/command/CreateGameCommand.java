package command;

import ServerModel.ServerFacade;
import commandData.CreateGameCommandData;
import modeling.Game;
import result.CommandResult;
import result.CreateGameCommandResult;

/**
 * Created by Hwang on 9/29/2017.
 */

public class CreateGameCommand extends CreateGameCommandData implements ICommand {

    /*

    */

    public CreateGameCommand(Game game) {
        super(game);
        setType("createGame");
    }

    @Override
    public CommandResult execute() {

        boolean gameCreatedSuccessful = ServerFacade.getInstance().createGame(getGame());
        CommandResult result;
        if (gameCreatedSuccessful) {
            result = new CommandResult(true, new CreateGameCommandResult(this.getGame()), "");
            result.setType(this.getType());
            return result;
        } else {
            result = new CommandResult(false);
            result.setType(this.getType());
            return result;
        }

    }
}
