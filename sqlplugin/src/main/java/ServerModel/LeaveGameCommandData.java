package ServerModel;

import modeling.Game;
import modeling.Player;

/**
 * Created by Hwang on 10/4/2017.
 */

public class LeaveGameCommandData extends Command{
    private Game gameObject;
    private Player playerObject;

    public LeaveGameCommandData() {
        setType("leaveGame");
    }

    public Game getGameObject() {
        return gameObject;
    }

    public void setGameObject(Game gameObject) {
        this.gameObject = gameObject;
    }

    public Player getPlayerObject() {
        return playerObject;
    }

    public void setPlayerObject(Player playerObject) {
        this.playerObject = playerObject;
    }
}
