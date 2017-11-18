package command;

import java.util.List;

import ServerModel.ServerFacade;
import commandData.ClaimDestinationCardCommandData;
import commandData.Command;
import modeling.DestinationCard;
import result.CommandResult;

/**
 * Created by ahwang13 on 10/24/17.
 */

public class ClaimDestinationCardCommand extends ClaimDestinationCardCommandData implements ICommand{


    private ClaimDestinationCardCommandData commandData;
    public ClaimDestinationCardCommand(ClaimDestinationCardCommandData data) {
        super();
        this.commandData = data;
        setType("claimDestinationCards");
    }

    @Override
    public CommandResult execute() {
        ServerFacade facade = ServerFacade.getInstance();
        //List<DestinationCard> claimedDestinationCards = facade.distributeUsedDestinationCards(commandData);
        CommandResult result;

        result = facade.distributeUsedDestinationCards(commandData);
        if (result.isSuccess() == true) {
            facade.addCommandToList(commandData.getGameID(), commandData);
        }
        //result = new ClaimDestinationCardCommandResult(true, claimedDestinationCards, "Destination Cards given and unchosen cards restored to deck.");
        setType("claimDestinationCards");
        return result;
    }
}
