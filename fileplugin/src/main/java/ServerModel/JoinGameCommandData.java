package ServerModel;

import commandData.Command;
import modeling.Player;
import modeling.User;

/**
 * Created by Hwang on 9/28/2017.
 */

public class JoinGameCommandData extends Command {

    private String gameID;
    private User user;
    private Player player;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public JoinGameCommandData(String gameID, User user) {
        setType("joinGame");
        this.gameID = gameID;
        this.user = user;
    }

    public String getGameID() {
        return gameID;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
