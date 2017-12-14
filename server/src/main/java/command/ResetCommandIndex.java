package command;

import java.util.ArrayList;
import java.util.List;

import ServerModel.ServerFacade;
import ServerModel.ServerModel;
import commandData.Command;
import modeling.Game;
import modeling.Player;
import result.CommandResult;

/**
 * Created by tyler on 12/13/2017.
 */

public class ResetCommandIndex implements ICommand {
    private String gameID;

    public ResetCommandIndex(String gameID) {
        this.gameID = gameID;
    }

    @Override
    public CommandResult execute() {
        Game game = ServerFacade.getInstance().getGameList().findGame(gameID);
        for (Player player : game.getPlayers()) {
            player.setCommandIndex(0);
        }
        ServerModel.getInstance().getCommandListMap().put(gameID,new ArrayList<Command>());
        return new CommandResult(true);
    }
}
