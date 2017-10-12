package commandData;

import modeling.Game;
import modeling.Player;

/**
 * Created by Hwang on 10/4/2017.
 */

public class LeaveGameCommandData {
    private Game game;
    private Player player;

    public LeaveGameCommandData() {
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
