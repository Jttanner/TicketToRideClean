package commandData;


import modeling.Game;

/**
 * Created by shawnli on 12/13/17.
 */

public class GameCommandData extends Command {

    Game game = null;
    public GameCommandData(){

    }
    public GameCommandData(Game aGame){
        this.game = aGame;
    }
}
