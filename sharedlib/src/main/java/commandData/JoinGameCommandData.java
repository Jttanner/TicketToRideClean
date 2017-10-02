package commandData;

/**
 * Created by Hwang on 9/28/2017.
 */

public class JoinGameCommandData extends Command {

    private int gameID;

    public JoinGameCommandData() {
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }
}
