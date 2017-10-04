package command;

import ServerModel.ServerFacade;
import commandData.DeleteGameCommandData;
import modeling.Game;
import result.Result;

/**
 * Created by Hwang on 9/29/2017.
 */

public class DeleteGameCommand extends DeleteGameCommandData implements ICommand{
    public DeleteGameCommand(){
            super();
    }

    private Game gameObject;

    @Override
    public Result execute() {

        boolean deleteGameSuccessful = ServerFacade.getInstance().deleteGame(gameObject);
        if (deleteGameSuccessful == true) {
            return new Result(true, "", "");
        } else {
            return new Result(false, "", "");
        }
    }
}
