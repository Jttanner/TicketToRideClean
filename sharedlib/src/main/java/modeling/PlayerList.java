package modeling;

import java.util.Set;

import modeling.Player;

/**
 * Created by ahwang13 on 10/9/17.
 */

public class PlayerList {

    /*
    TODO: CAN SOMEONE PLEASE EXPLAIN TO ME WHY WE NEED THIS GAMELIST WITHIN OUR RESULT PACKAGE? I FEEL LIKE IT'S A LAZY WAY TO DO THIS
     */

    private Set<Player> players;

    public PlayerList(Set<Player> games) {
        this.players = players;
    }

    public Set<Player> getPlayers() {
        return players;
    }
}
