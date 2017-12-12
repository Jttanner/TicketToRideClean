package command;

import ServerModel.ServerFacade;
import ServerModel.ServerModel;
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
        //add BLOB
        ServerModel.getInstance().getPlugin().saveGame(getGame());
        CommandResult result;
        result = new CreateGameCommandResult(gameCreatedSuccessful);
        result.setType(this.getType());
        return result;

    }
}
