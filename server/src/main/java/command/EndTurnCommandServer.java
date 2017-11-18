package command;

import ServerModel.ServerFacade;
import commandData.EndTurnCommandData;
import result.CommandResult;

/**
 * Created by ahwang13 on 11/14/17.
 */

public class EndTurnCommandServer extends EndTurnCommandData implements ICommand {

    private EndTurnCommandData commandData;
    public EndTurnCommandServer(EndTurnCommandData data) {
        this.commandData = data;
        setType("endTurn");
    }

    @Override
    public CommandResult execute() {
        ServerFacade facade = ServerFacade.getInstance();
        facade.endTurn(commandData.getGameID());
        facade.addCommandToList(commandData.getGameID(),commandData);
        //dont really need to return anything
        return null;
    }

}

