package commandData;

/**
 * Created by tyler on 12/13/2017.
 * Tells people to reset their command indices
 */

public class ResetCommandIndexData extends Command {
    private String gameID;
    public ResetCommandIndexData(String gameID) {
        this.setType("ResetCommandIndex");
        this.gameID = gameID;
    }

    public String getGameId() {
        return gameID;
    }
}
