package commandData;

import java.util.ArrayList;

import modeling.Game;

/**
 * Created by Hwang on 9/29/2017.
 */

public class GetGameListCommandData extends Command {

    private ArrayList<Game> gameListLobby;

    public GetGameListCommandData() {
    }

    public ArrayList<Game> getGameListLobby() {
        return gameListLobby;
    }

    public void setGameListLobby(ArrayList<Game> gameListLobby) {
        this.gameListLobby = gameListLobby;
    }
}
