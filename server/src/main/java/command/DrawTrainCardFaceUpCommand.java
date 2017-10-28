package command;

import commandData.DrawTrainCardFaceUpCommandData;
import result.CommandResult;

/**
 * Created by korea on 10/28/2017.
 */

public class DrawTrainCardFaceUpCommand extends DrawTrainCardFaceUpCommandData implements ICommand {
    public DrawTrainCardFaceUpCommand(DrawTrainCardFaceUpCommandData drawTrainCardFaceUpCommandData){


    }

    @Override
    public CommandResult execute() {
        return null;
    }
}
