package commandData;

import modeling.Game;

/**
 * Created by jontt on 10/9/2017.
 */

public class StartGameCommandData extends Command {
    private Game game;

    public StartGameCommandData(Game game){
        setType("startGame");
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
