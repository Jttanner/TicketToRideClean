package command;

import ServerModel.ServerFacade;
import commandData.ClaimDestinationCardCommandData;
import commandData.ClaimInitialDestinationCardCommandData;
import result.CommandResult;

/**
 * Created by ahwang13 on 11/21/17.
 */

public class ClaimInitialDestinationCardCommand extends ClaimInitialDestinationCardCommandData implements ICommand {


    private ClaimInitialDestinationCardCommandData commandData;

    public ClaimInitialDestinationCardCommand(ClaimInitialDestinationCardCommandData data) {
        super();
        this.commandData = data;
        setType("claimInitialDestinationCards");
    }

    @Override
    public CommandResult execute() {
        ServerFacade facade = ServerFacade.getInstance();
        //List<DestinationCard> claimedDestinationCards = facade.distributeUsedDestinationCards(commandData);
        //result = new CommandResult(true);
        CommandResult result;

        result = facade.distributeUsedInitialDestinationCards(commandData);
        //facade.addCommandToList(commandData.getGameID(), commandData);
        if (result.isSuccess() == true) {
            facade.addCommandToList(commandData.getGameID(), commandData);
        }
        //result = new ClaimDestinationCardCommandResult(true, claimedDestinationCards, "Destination Cards given and unchosen cards restored to deck.");
        setType("claimInitialDestinationCards");
        return result;
    }
}
