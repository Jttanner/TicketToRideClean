package clientModel;

import java.util.List;

import modeling.Game;

/**
 * Created by tyler on 10/8/2017.
 */

public class GameList {
    private List<Game> games;

    public GameList(List<Game> games) {
        this.games = games;
    }

    public List<Game> getGames() {
        return games;
    }

}
