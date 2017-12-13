import java.util.List;

/**
 * Created by ahwang13 on 10/24/17.
 */

public class DrawDestinationCardCommandData extends Command{

    private String gameID;
    private String playerName;
    private List<DestinationCard> drawDestinationCards;


    public DrawDestinationCardCommandData() {
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public DrawDestinationCardCommandData(String gameID, String playerID) {
        this.gameID = gameID;
        this.playerName = playerID;
        this.setType("drawDestinationCards");
        this.setData("");
    }

    public String getGameID() {
        return gameID;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }

    public List<DestinationCard> getDrawDestinationCards() {
        return drawDestinationCards;
    }

    public void setDrawDestinationCards(List<DestinationCard> drawDestinationCards) {
        this.drawDestinationCards = drawDestinationCards;
    }
}
