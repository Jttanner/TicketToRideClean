package command;

import commandData.DeleteGameCommandData;
import result.Result;

/**
 * Created by Hwang on 9/29/2017.
 */

public class DeleteGameCommand extends DeleteGameCommandData implements ICommand{
    public DeleteGameCommand(){
            super();
    }

    @Override
    public Result execute() {
        try {
            GetGameListCommand getGameCommandData = new GetGameListCommand();
            //String str = GetGameListCommand.getStr();
            //int number = StringProcessor.instance().parseInteger(str);
            //String data = String.valueOf(number);
            Result result = new Result(true, "data", null);
            return result;
        }
        catch (NumberFormatException e) {
            Result result = new Result(false, null, "Error, not a number!");
            return result;
        }
    }
}
