package command;

import ServerModel.ServerFacade;
import commandData.Command;
import commandData.DrawTrainCardFaceUpCommandData;
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

        //Temporary Implementation. May need to change
        ServerFacade.getInstance().addCommandToList(drawTrainCardFaceUpCommandData.getGame().getGameID(), drawTrainCardFaceUpCommandData);
        result = new CommandResult(true);

        return result;
    }
}
