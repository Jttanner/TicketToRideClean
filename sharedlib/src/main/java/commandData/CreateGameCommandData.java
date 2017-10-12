package commandData;

import modeling.Game;

/**
 * Created by Hwang on 9/28/2017.
 */

public class CreateGameCommandData extends Command {
    private Game gameObject;

    public CreateGameCommandData() {
        setType("createGame");
    }

    public Game getGameObject() {
        return gameObject;
    }

    public void setGameObject(Game gameObject) {
        this.gameObject = gameObject;
    }
}
