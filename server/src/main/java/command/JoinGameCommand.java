package command;

import commandData.JoinGameCommandData;
import result.Result;

/**
 * Created by Hwang on 9/29/2017.
 */

public class JoinGameCommand {
    public JoinGameCommand(){
        super();
    }

   // @Override
    public Result execute() {
        try {
            JoinGameCommandData joinGameCommandData = new JoinGameCommandData();
            //String str = joinGameCommandData.getStr();
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
