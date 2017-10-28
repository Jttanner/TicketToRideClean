package command;

import com.sun.org.apache.bcel.internal.generic.ICONST;

import commandData.DrawTrainCardDeckCommandData;
import result.CommandResult;

/**
 * Created by korea on 10/28/2017.
 */

public class DrawTrainCardDeckCommand extends DrawTrainCardDeckCommandData implements ICommand {

    public DrawTrainCardDeckCommand(DrawTrainCardDeckCommandData drawTrainCardDeckCommandData){


    }
    @Override
    public CommandResult execute() {
        return null;
    }
}
