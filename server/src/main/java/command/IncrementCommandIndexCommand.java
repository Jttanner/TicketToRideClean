package command;

import java.util.List;

import ServerModel.*;
import commandData.IncrementCommandIndexCommandData;
import modeling.Game;
import result.CommandResult;


/**
 * Created by jontt on 11/27/2017.
 */

public class IncrementCommandIndexCommand extends IncrementCommandIndexCommandData implements ICommand {

    public IncrementCommandIndexCommand(String gameID, String playerName) {
        super(gameID, playerName);
        this.setType("incrementCommandIndex");
    }

    @Override
    public CommandResult execute() {
        List<Game> games = ServerFacade.getInstance().getGameList().getGames();
        for (Game g : games){
            if (g.getGameID().equals(this.getGameID())){
                g.getPlayer(this.getPlayerName()).incrementCommandIndex();
            }
        }
        return new CommandResult(true);
    }
}
