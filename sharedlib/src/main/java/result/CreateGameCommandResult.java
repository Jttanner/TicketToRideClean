package result;


/**
 * Created by jontt on 10/13/2017.
 */

public class CreateGameCommandResult extends CommandResult {

    public CreateGameCommandResult(boolean success){
        super(success);
    }

    public CreateGameCommandResult(boolean success,String error){
        super(success,error);
    }


}
