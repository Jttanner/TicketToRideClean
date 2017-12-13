package command;

import java.util.List;

import ServerModel.ServerFacade;
import modeling.Game;
import modeling.Player;
import result.CommandResult;

/**
 * Created by tyler on 12/13/2017.
 */

public class ResetCommandIndex implements ICommand {
    @Override
    public CommandResult execute() {
        List<Game> games = ServerFacade.getInstance().getGameList().getGames();
        for (Game g : games){
            for(Player player : g.getPlayers()){
                player.setCommandIndex(0);
            }
        }
        return new CommandResult(true);
    }
}
