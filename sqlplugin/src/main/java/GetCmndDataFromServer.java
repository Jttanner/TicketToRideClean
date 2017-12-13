/**
 * Created by tyler on 10/24/2017.
 */

public class GetCmndDataFromServer extends Command {

    private String GameId;

    public String getGameId() {
        return GameId;
    }

    public void setGameId(String gameId) {
        GameId = gameId;
    }

    public GetCmndDataFromServer(String gameId) {
        setType("getCommandList");
        GameId = gameId;
    }

    public GetCmndDataFromServer() {
        setType("getCommandList");
    }
}
