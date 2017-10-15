package result;


import modeling.Game;

/**
 * Created by jontt on 10/13/2017.
 */

public class JoinGameCommandResult extends CommandResult {

    private Game game;

    public JoinGameCommandResult(boolean success,Game game){
        super(success);
        this.game = game;
    }

    public JoinGameCommandResult(boolean failed,String error){
        super(failed,error);
    }

    public Game getGame() {
        return game;
    }
}
