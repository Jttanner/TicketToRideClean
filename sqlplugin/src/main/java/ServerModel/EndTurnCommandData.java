package ServerModel;

/**
 * Created by ahwang13 on 11/14/17.
 */

public class EndTurnCommandData extends Command{

    private String gameID;
    private String playerName;
    /*private String stateClassName;*/

    /*public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }*/

    public EndTurnCommandData(String gameID) {
        this.gameID = gameID;
       // this.playerName = playerID;
        /*this.stateClassName = stateClassName;*/
        this.setType("endTurn");
        this.setData("");
    }

    public EndTurnCommandData() {
    }

    public String getGameID() {
        return gameID;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }

    /*public String getStateClassName() {
        return stateClassName;
    }*/
}