package commandData;

import modeling.User;

/**
 * Created by Hwang on 9/28/2017.
 */

public class JoinGameCommandData extends Command {

    private int gameID;
    private User user;

    public JoinGameCommandData(int gameID, User user) {
        this.gameID = gameID;
        this.user = user;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }
}
