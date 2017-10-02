package commandData;

/**
 * Created by Hwang on 9/28/2017.
 */

public class CreateGameCommandData extends Command {
    private String gameName;
    private int gameID;
    private int playerMax;

    public CreateGameCommandData() {
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public int getPlayerMax() {
        return playerMax;
    }

    public void setPlayerMax(int playerMax) {
        this.playerMax = playerMax;
    }
}
