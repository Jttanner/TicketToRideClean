package command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ServerModel.ServerFacade;
import commandData.GetGameListCommandData;
import modeling.Game;
import result.Result;

/**
 * Created by Hwang on 9/29/2017.
 */

public class GetGameListCommand extends GetGameListCommandData implements ICommand{
    public GetGameListCommand(){
        super();
    }

    @Override
    public Result execute() {
        try {
            //GetGameListCommand getGameCommandData = new GetGameListCommand();

            ServerFacade facade = ServerFacade.getInstance();

            List<Game> gameList = new ArrayList<>();

            for (Map.Entry<String, Game> game : facade.getGameList().entrySet()){
                gameList.add(game.getValue());
            }

            Result result = new Result(true, gameList, "Game List sent.");
            return result;
        }
        catch (NumberFormatException e) {
            Result result = new Result(false, null, "Error, not a number!");
            return result;
        }
    }
}
