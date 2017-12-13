package command;



import java.util.List;

import ServerModel.ServerFacade;
import ServerModel.ServerModel;
import commandData.DrawDestinationCardCommandData;
import modeling.DestinationCard;
import modeling.DestinationCardList;
import modeling.Player;
import result.CommandResult;

/**
 * Created by ahwang13 on 10/24/17.
 */


public class DrawDestinationCardCommand extends DrawDestinationCardCommandData implements ICommand {

    private DrawDestinationCardCommandData commandData;
    public DrawDestinationCardCommand(DrawDestinationCardCommandData data) {
        super();
        this.commandData = data;
        setType("drawDestinationCards");
    }

    @Override
    public CommandResult execute() {
        /*
        ServerFacade facade = ServerFacade.getInstance();
        DrawDestinationCardCommandResult result;
        List<DestinationCard> destinationCardList = facade.getDestinationCardList(commandData);
        if(destinationCardList == null) {
            //facade.addCommandToList(facade.getGameName().getGameID(),commandData);
            result = new DrawDestinationCardCommandResult(false, "failed");
            setType("drawDestinationCards");
            return result;
        }
        else {
            facade.addCommandToList(commandData.getGameID(), commandData);
            //GetGameListCommandData cmdData = new GetGameListCommandData();
            //cmdData.setGameListLobby(gameList);
            result = new DrawDestinationCardCommandResult(true, destinationCardList, "Destination card list sent.");
            setType("drawDestinationCards");
            return result;
        }
*/
        ServerFacade facade = ServerFacade.getInstance();
        //List<DestinationCard> claimedDestinationCards = facade.distributeUsedDestinationCards(commandData);
        CommandResult result = new CommandResult();
        List<DestinationCard> destinationCardList = facade.getDestinationCardList(commandData);
        commandData.setDrawDestinationCards(destinationCardList);
        facade.addCommandToList(commandData.getGameID(), commandData);
        result.setSuccess(true);
        //result = new ClaimDestinationCardCommandResult(true, claimedDestinationCards, "Destination Cards given and unchosen cards restored to deck.");
        //setType("claimDestinationCards");
        return result;
    }

}
