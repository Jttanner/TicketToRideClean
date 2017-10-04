package command;

import commandData.JoinGameCommandData;
import modeling.User;
import result.Result;

/**
 * Created by Hwang on 9/29/2017.
 */

public class JoinGameCommand extends JoinGameCommandData implements  ICommand{

    public JoinGameCommand(int userID, User user){
        super(userID, user);
    }


   // @Override
    public Result execute() {
        try {
            //JoinGameCommandData joinGameCommandData = new JoinGameCommandData();
            int gameID = this.getGameID();


            //Join game sends gameID
            //Send back bool,

            //String str = joinGameCommandData.getStr();
            //int number = StringProcessor.instance().parseInteger(str);
            //String data = String.valueOf(number);
            Result result = new Result(true, gameID, null);
            return result;
        }
        catch (NumberFormatException e) {
            Result result = new Result(false, null, "Error, not a number!");
            return result;
        }
    }
}
