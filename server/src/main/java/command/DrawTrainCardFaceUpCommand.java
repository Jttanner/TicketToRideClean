package command;

import ServerModel.ServerFacade;
import ServerModel.ServerModel;
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

        //Add card to the Person
        //Little confused as to how the Server Model works?


        //Adds to the commands to be executed
        facade.addCommandToList(drawTrainCardFaceUpCommandData.getGameID(), drawTrainCardFaceUpCommandData);
        result = new CommandResult(true);

        return result;
    }
}
