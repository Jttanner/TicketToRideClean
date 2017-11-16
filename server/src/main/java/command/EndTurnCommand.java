package command;

import ServerModel.ServerFacade;
import commandData.EndTurnCommandData;
import result.CommandResult;

/**
 * Created by ahwang13 on 11/14/17.
 */
/*
public class EndTurnCommand extends EndTurnCommandData implements ICommand {

    private EndTurnCommandData commandData;
    public EndTurnCommand(EndTurnCommandData data) {
        super();
        this.commandData = data;
        setType("endTurn");
    }

    @Override
    public CommandResult execute() {
        ServerFacade facade = ServerFacade.getInstance();
        boolean success = facade.endTurn();
        //DrawDestinationCardCommandResult result;
        //List<DestinationCard> destinationCardList = facade.getDestinationCardList();
        //if(destinationCardList == null) {
            //facade.addCommandToList(facade.getGameName().getGameID(),commandData);
            result = new DrawDestinationCardCommandResult(false, "failed");
            setType("endTurn");
            return result;
        }
        else {
            //facade.addCommandToList(getGameID(), commandData);
            //GetGameListCommandData cmdData = new GetGameListCommandData();
            //cmdData.setGameListLobby(gameList);
            result = new DrawDestinationCardCommandResult(true, destinationCardList, "Destination card list sent.");
            setType("endTurn");
            return result;
        }
    }

}*/

