package command;

import ServerModel.ServerFacade;
import commandData.DeleteGameCommandData;
import modeling.Game;
import result.CommandResult;

/**
 * Created by Hwang on 9/29/2017.
 */

public class DeleteGameCommand extends DeleteGameCommandData implements ICommand{
    public DeleteGameCommand(){
            super();
    }

    private Game gameObject;

    @Override
    public CommandResult execute() {

        boolean deleteGameSuccessful = ServerFacade.getInstance().deleteGame(gameObject);
        if (deleteGameSuccessful) {
            return new CommandResult(true, "", "");
        } else {
            return new CommandResult(false, "", "");
        }
    }
}
