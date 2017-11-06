package command;

import ServerModel.ServerFacade;
import commandData.StartGameCommandData;
import result.CommandResult;

/**
 * Created by jontt on 10/9/2017.
 */

public class StartGameCommand extends StartGameCommandData implements ICommand {
    private StartGameCommandData commandData;
    public StartGameCommand(StartGameCommandData data) {
        super();
        //hold onto dataobjct in case of success
        this.commandData = data;
        setType("startGame");
    }

    @Override
    public CommandResult execute() {
        ServerFacade facade = ServerFacade.getInstance();

        CommandResult result;
        //start the game then if it succeeds add this commanddata to the list for the game object
        //others will take this startGameData and start the game themselves
        //if (facade.startGame(commandData.getGame())){
            result = new CommandResult(true);
            facade.addCommandToList(commandData.getGame(),commandData);
        //} else{
       //     result = new CommandResult(false);
      //  }
        result.setType(this.getType());
        return result;
    }
}

