package ServerModel;

import modeling.Game;

/**
 * Created by Hwang on 9/28/2017.
 */

public class CreateGameCommandData extends Command {
    private Game game;

    public CreateGameCommandData(Game game)
    {
        this.game = game;
        setType("createGame");
    }

    public CreateGameCommandData()
    {
        setType("createGame");
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
