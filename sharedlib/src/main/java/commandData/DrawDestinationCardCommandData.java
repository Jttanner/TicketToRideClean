package commandData;

import modeling.DestinationCard;
import modeling.Player;

/**
 * Created by ahwang13 on 10/24/17.
 */

public class DrawDestinationCardCommandData extends Command{

    private String gameID;
    private String playerID;


    public DrawDestinationCardCommandData() {
    }

    public String getPlayerID() {
        return playerID;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public DrawDestinationCardCommandData(String gameID, String playerID) {
        this.gameID = gameID;
        this.playerID = playerID;
        this.setType("drawDestinationCards");
        this.setData("");
    }

    public String getGameID() {
        return gameID;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }

}
