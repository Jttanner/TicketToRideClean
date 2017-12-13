package ServerModel;

/**
 * Created by jontt on 11/27/2017.
 */

public class IncrementCommandIndexCommandData extends Command {
    String gameID;
    String playerName;

    public IncrementCommandIndexCommandData(String gameID, String playerName){
        this.gameID = gameID;
        this.playerName = playerName;
        this.setType("incrementCommandIndex");
    }

    public String getGameID() {
        return gameID;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
