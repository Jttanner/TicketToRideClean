package command;

import ServerModel.ServerFacade;
import commandData.DrawTrainCardCommandData;
import result.CommandResult;

/**
 * Created by korea on 10/28/2017.
 */

public class DrawTrainCardCommand extends DrawTrainCardCommandData implements ICommand {
    private DrawTrainCardCommandData drawTrainCardCommandData;

    public DrawTrainCardCommand(DrawTrainCardCommandData data){
        super();
        drawTrainCardCommandData = data;
        setType("drawTrainCardFaceUp");
    }

    @Override
    public CommandResult execute()
    {
        ServerFacade facade = ServerFacade.getInstance();
        CommandResult result;
        String cardID = drawTrainCardCommandData.getResourceCard().getCardID();

        //Add card to player and replace the face up card
        facade.getGameList().findGame(drawTrainCardCommandData.getGameID()).getPlayer(drawTrainCardCommandData.getPlayerName()).addResourceCard(facade.getGameList().findGame(drawTrainCardCommandData.getGameID()).getResourceCardList().drawCard(cardID));
//        facade.getGameList().findGame(drawTrainCardCommandData.getGameID()).getResourceCardList().upDateFaceUpPile(drawTrainCardCommandData.getPosition());

        //Debugging purposes
//        Game game = facade.getGameList().findGame(drawTrainCardCommandData.getGameID());
//        game.getResourceCardList();

        //Adds to the commands to be executed
        facade.addCommandToList(drawTrainCardCommandData.getGameID(), drawTrainCardCommandData);
        result = new CommandResult(true);

        return result;
    }
}
