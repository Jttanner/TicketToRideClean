package command;

import java.util.List;

import ServerModel.ServerFacade;
import commandData.DrawDestinationCardCommandData;
import modeling.DestinationCard;
import modeling.DestinationCardList;
import result.DrawDestinationCardCommandResult;

/**
 * Created by ahwang13 on 10/24/17.
 */

public class DrawDestinationCardCommand extends DrawDestinationCardCommandData implements ICommand {
    public DrawDestinationCardCommand() {
        super();
        setType("drawDestinationCards");
    }

    @Override
    public DrawDestinationCardCommand execute() {
        DrawDestinationCardCommandResult result;
        List<DestinationCard> destinationCardList = ServerFacade.getInstance().getDestinationCardList();
        if(destinationCardList == null) {
            result = new DrawDestinationCardCommandResult(false, "failed");
            return result;
        }
        else {
            //TODO this code commented out seems to do nothing
            //GetGameListCommandData cmdData = new GetGameListCommandData();
            //cmdData.setGameListLobby(gameList);
            result = new DrawDestinationCardCommandResult(true, gameList, "Game Lists sent.");
            return result;
        }
    }

}
