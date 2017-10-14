package command;

import ServerModel.ServerFacade;
import commandData.StartGameCommandData;
import modeling.Game;
import result.CommandResult;

/**
 * Created by jontt on 10/9/2017.
 */

public class StartGameCommand extends StartGameCommandData implements ICommand {
    Game game;

    public StartGameCommand(Game game) {
        super(game);
        this.game = game;
        setType("startGame");
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public CommandResult execute() {
        ServerFacade facade = ServerFacade.getInstance();
        CommandResult result;
        if (facade.startGame(game)){
            result = new CommandResult(true);
        } else{
            result = new CommandResult(false);
        }
        result.setType(this.getType());
        return result;
    }
}
