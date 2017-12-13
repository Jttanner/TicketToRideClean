package ServerModel;

/**
 * Created by Hwang on 9/29/2017.
 */

public class GetGameListCommandData extends Command {

    //private ServerModel.GameList gameListLobby;

    public GetGameListCommandData() {
        setType("getGameList");
    }
    /*
    public ServerModel.GameList getGameListLobby() {
        return gameListLobby;
    }

    public void setGameListLobby(ServerModel.GameList gameListLobby) {
        this.gameListLobby = gameListLobby;
    }
    */
}