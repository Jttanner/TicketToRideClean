package result;


import modeling.Game;

/**
 * Created by jontt on 10/13/2017.
 */

public class CreateGameCommandResult extends CommandResult {

    private Game game;

    public CreateGameCommandResult(boolean success, Game game){
        super(success);
        this.game = game;
    }

    public CreateGameCommandResult(boolean success,String error){
        super(success,error);
    }

    public Game getGame() {
        return game;
    }

}
