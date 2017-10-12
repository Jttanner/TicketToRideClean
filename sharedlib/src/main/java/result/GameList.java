package result;

import java.util.List;

import modeling.Game;

/**
 * Created by tyler on 10/8/2017.
 */

public class GameList {

    /*
    TODO: CAN SOMEONE PLEASE EXPLAIN TO ME WHY WE NEED THIS GAMELIST WITHIN OUR RESULT PACKAGE? I FEEL LIKE IT'S A LAZY WAY TO DO THIS
     */

    private List<Game> games;

    public GameList(List<Game> games) {
        this.games = games;
    }

    public List<Game> getGames() {
        return games;
    }

}
