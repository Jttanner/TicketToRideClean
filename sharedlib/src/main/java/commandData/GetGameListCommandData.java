package commandData;


import java.util.ArrayList;
import java.util.List;

import modeling.Game;

/**
 * Created by Hwang on 9/29/2017.
 */

public class GetGameListCommandData extends Command {
    //We don't need it! WHY DON'T WE NEED IT???
    private List<Game> gameListLobby;

    public GetGameListCommandData() {
    }

    public List<Game> getGameListLobby() {
        return gameListLobby;
    }

    public void setGameListLobby(ArrayList<Game> gameListLobby) {
        this.gameListLobby = gameListLobby;
    }
}
