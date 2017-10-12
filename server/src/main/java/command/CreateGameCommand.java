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
        setType("createGame");
    }

    @Override
    public CommandResult execute() {


        boolean gameCreatedSuccessful = ServerFacade.getInstance().createGame(getGameObject());
        CommandResult result;
        if (gameCreatedSuccessful) {
            result = new CommandResult(true, mGame, "");
            result.setType(this.getType());
            return result;
        } else {
            result = new CommandResult(false);
            result.setType(this.getType());
            return result;
        }

    }
}
