package commandData;

/**
 * Created by tyler on 10/23/2017.
 */

public class GetCommandListData extends Command{
    private String gameID;

    public GetCommandListData(String gameID) {
        this.gameID = gameID;
        this.setType("getCommandList");
    }

    public String getGameID() {
        return gameID;
    }
}
