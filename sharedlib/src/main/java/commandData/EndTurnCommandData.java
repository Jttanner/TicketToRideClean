package commandData;

/**
 * Created by ahwang13 on 11/14/17.
 */

public class EndTurnCommandData extends Command{

    private String gameID;
    private String playerName;


    public EndTurnCommandData() {
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public EndTurnCommandData(String gameID, String playerID) {
        this.gameID = gameID;
        this.playerName = playerID;
        this.setType("endTurn");
        this.setData("");
    }

    public String getGameID() {
        return gameID;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }

}