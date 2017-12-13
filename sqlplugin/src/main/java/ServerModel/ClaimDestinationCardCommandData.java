package ServerModel;

import java.util.List;

/**
 * Created by ahwang13 on 10/24/17.
 */

public class ClaimDestinationCardCommandData extends Command{

    private String gameID;
    private String playerID;
    private List<DestinationCard> claimDestinationCards;

    public String getPlayerID() {
        return playerID;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public ClaimDestinationCardCommandData() {
    }

    public ClaimDestinationCardCommandData(String gameID, String playerID, List<DestinationCard> claimDestinationCards) {
        this.gameID = gameID;
        this.playerID = playerID;
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