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
        setType("deleteGame");
    }

    private Game gameObject;

    @Override
    public CommandResult execute() {

        boolean deleteGameSuccessful = ServerFacade.getInstance().deleteGame(gameObject);
        CommandResult result;
        if (deleteGameSuccessful) {
            result = new CommandResult(true);
            result.setType(this.getType());

        } else {
            result = new CommandResult(false);
            result.setType(this.getType());
        }
        return result;
    }
}
