package command;

import ServerModel.ServerFacade;
import commandData.CreateGameCommandData;
import modeling.Game;
import result.CommandResult;

/**
 * Created by Hwang on 9/29/2017.
 */

public class CreateGameCommand extends CreateGameCommandData implements ICommand {
    private Game mGame;
    public CreateGameCommand(Game game) {
        super();
        mGame = game;
    }

    @Override
    public CommandResult execute() {


        boolean gameCreatedSuccessful = ServerFacade.getInstance().createGame(getGameObject());
        if (gameCreatedSuccessful) {
            return new CommandResult(true, mGame, "");
        } else {
            return new CommandResult(false, "", "");
        }
    }
}
