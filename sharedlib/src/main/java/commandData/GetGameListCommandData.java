package commandData;


import java.util.ArrayList;
import java.util.List;

import modeling.Game;
import modeling.GameList;

/**
 * Created by Hwang on 9/29/2017.
 */

public class GetGameListCommandData extends Command {

    //private GameList gameListLobby;

    public GetGameListCommandData() {
        setType("getGameList");
    }
    /*
    public GameList getGameListLobby() {
        return gameListLobby;
    }

    public void setGameListLobby(GameList gameListLobby) {
        this.gameListLobby = gameListLobby;
    }
    */
}