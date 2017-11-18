/*
package command;

import com.sun.org.apache.bcel.internal.generic.ICONST;

import ServerModel.ServerFacade;
import commandData.Command;
import commandData.DrawTrainCardDeckCommandData;
import result.CommandResult;

*/
/**
 * Created by korea on 10/28/2017.
 *//*


public class DrawTrainCardDeckCommand extends DrawTrainCardDeckCommandData implements ICommand {
    private DrawTrainCardDeckCommandData drawTrainCardDeckCommandData;
    public DrawTrainCardDeckCommand(DrawTrainCardDeckCommandData data){
        super();
        drawTrainCardDeckCommandData = data;
        setType("drawTrainCardDeck");
    }
    @Override
    public CommandResult execute() {
        ServerFacade facade = ServerFacade.getInstance();
        CommandResult result;



        //Temporary Implementation. May need to change
        facade.addCommandToList(drawTrainCardDeckCommandData.getGameID(), drawTrainCardDeckCommandData);
        result = new CommandResult(true);

        return result;
    }
}
*/
