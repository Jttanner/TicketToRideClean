package result;


import modeling.Game;

/**
 * Created by jontt on 10/13/2017.
 */

public class CreateGameCommandResult extends CommandResult {

    Game game;

    public CreateGameCommandResult(Game game){
        super();
        this.game = game;
    }

}
