package commandData;

import modeling.DestinationCard;
import modeling.Player;

/**
 * Created by ahwang13 on 10/24/17.
 */

public class DrawDestinationCardCommandData extends Command{

    private String gameID;
    private Player player;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public DrawDestinationCardCommandData() {

    }

    public DrawDestinationCardCommandData(String gameID, Player player) {
        this.gameID = gameID;
        this.player = player;
    }

    public String getGameID() {
        return gameID;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }

}
