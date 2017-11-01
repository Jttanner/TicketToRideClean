package command;

import com.sun.corba.se.spi.activation.Server;

import java.util.List;

import ServerModel.ServerFacade;
import commandData.DrawDestinationCardCommandData;
import modeling.DestinationCard;
import modeling.DestinationCardList;
import modeling.Player;
import result.CommandResult;
import result.DrawDestinationCardCommandResult;

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
        ServerFacade facade = ServerFacade.getInstance();
        DrawDestinationCardCommandResult result;
        List<DestinationCard> destinationCardList = facade.getDestinationCardList();
        if(destinationCardList == null) {
            //facade.addCommandToList(facade.getGame().getGameID(),commandData);
            result = new DrawDestinationCardCommandResult(false, "failed");
            setType("drawDestinationCards");
            return result;
        }
        else {
            //facade.addCommandToList(getGameID(), commandData);
            //GetGameListCommandData cmdData = new GetGameListCommandData();
            //cmdData.setGameListLobby(gameList);
            result = new DrawDestinationCardCommandResult(true, destinationCardList, "Destination card list sent.");
            setType("drawDestinationCards");
            return result;
        }
    }

}
