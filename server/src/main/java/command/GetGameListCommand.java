package command;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ServerModel.ServerFacade;
import commandData.GetGameListCommandData;
import modeling.Game;
import result.CommandResult;

/**
 * Created by Hwang on 9/29/2017.
 */

public class GetGameListCommand extends GetGameListCommandData implements ICommand{
    public GetGameListCommand(){
        super();
    }

    @Override
    public CommandResult execute() {
        try {
            //GetGameListCommand getGameCommandData = new GetGameListCommand();

            ServerFacade facade = ServerFacade.getInstance();

            List<Game> gameList = new ArrayList<>();

            for (Map.Entry<String, Game> game : facade.getGameList().entrySet()){
                if (!game.getValue().isHasStarted()){
                    gameList.add(game.getValue());
                }
            }

            CommandResult result = new CommandResult(true, gameList, "Game List sent.");
            return result;
        }
        catch (NumberFormatException e) {
            CommandResult result = new CommandResult(false, null, "Error, not a number!");
            return result;
        }
    }
}
