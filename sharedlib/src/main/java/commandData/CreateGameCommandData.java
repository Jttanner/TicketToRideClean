package commandData;

import modeling.Game;
import modeling.User;

/**
 * Created by Hwang on 9/28/2017.
 */

public class CreateGameCommandData extends Command {
    private Game gameObject;
    private User user;

    public CreateGameCommandData() {
    }

    public Game getGameObject() {
        return gameObject;
    }

    public void setGameObject(Game gameObject) {
        this.gameObject = gameObject;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
