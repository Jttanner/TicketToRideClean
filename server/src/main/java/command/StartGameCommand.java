package command;

import com.sun.corba.se.spi.activation.Server;

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
        CommandResult result = null;
        if (facade.startGame(game)){
            result = new CommandResult(true, null, "");
            result.setType("startGame");
        } else{
            result = new CommandResult(false, null, "");
        }
        return result;
    }
}
