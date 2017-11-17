package command;

import ServerModel.ServerFacade;
import ServerModel.ServerModel;
import commandData.Command;
import commandData.DrawTrainCardFaceUpCommandData;
import modeling.Game;
import result.CommandResult;

/**
 * Created by korea on 10/28/2017.
 */

public class DrawTrainCardFaceUpCommand extends DrawTrainCardFaceUpCommandData implements ICommand {
    private DrawTrainCardFaceUpCommandData drawTrainCardFaceUpCommandData;

    public DrawTrainCardFaceUpCommand(DrawTrainCardFaceUpCommandData data){
        super();
        drawTrainCardFaceUpCommandData = data;
        setType("drawTrainCardFaceUp");
    }

    @Override
    public CommandResult execute()
    {
        ServerFacade facade = ServerFacade.getInstance();
        CommandResult result;
        String cardID = drawTrainCardFaceUpCommandData.getResourceCard().getCardID();

        //Add card to player and replace the face up card
        facade.getGameList().findGame(drawTrainCardFaceUpCommandData.getGameID()).getPlayer(drawTrainCardFaceUpCommandData.getPlayerName()).addResourceCard(facade.getGameList().findGame(drawTrainCardFaceUpCommandData.getGameID()).getResourceCardList().drawCard(cardID));
//        facade.getGameList().findGame(drawTrainCardFaceUpCommandData.getGameID()).getResourceCardList().upDateFaceUpPile(drawTrainCardFaceUpCommandData.getPosition());

        //Debugging purposes
//        Game game = facade.getGameList().findGame(drawTrainCardFaceUpCommandData.getGameID());
//        game.getResourceCardList();

        //Adds to the commands to be executed
        facade.addCommandToList(drawTrainCardFaceUpCommandData.getGameID(), drawTrainCardFaceUpCommandData);
        result = new CommandResult(true);

        return result;
    }
}
