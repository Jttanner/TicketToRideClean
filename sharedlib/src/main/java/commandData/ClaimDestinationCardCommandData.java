package commandData;

import java.util.List;

import modeling.DestinationCard;
import modeling.Player;

/**
 * Created by ahwang13 on 10/24/17.
 */

public class ClaimDestinationCardCommandData extends Command{

    private String gameID;
    private Player player;
    private List<DestinationCard> claimDestinationCards;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ClaimDestinationCardCommandData() {
    }

    public ClaimDestinationCardCommandData(String gameID, Player player, List<DestinationCard> claimDestinationCards) {
        this.gameID = gameID;
        this.player = player;
        this.claimDestinationCards = claimDestinationCards;
        this.setType("claimDestinationCards");
    }

    public String getGameID() {
        return gameID;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }

    public List<DestinationCard> getClaimDestinationCards() {
        return claimDestinationCards;
    }
}