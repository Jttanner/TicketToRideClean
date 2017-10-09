package modeling;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by tyler on 9/26/2017.
 * The game class is everything that a Game is in ticket to ride. So, you know, stuff
 */
public class Game {


    @Override
    public String toString() {
        return gameName;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (!(o instanceof Game)) {
            return false;
        }
        Game oGame = (Game) o;
        return this.gameID.equals(oGame.getGameID());
    }

    private ArrayList<Player> players;

    private boolean hasStarted;

    private String gameID;

    private String gameName;

    private int playerMax;

    public Game() {
        this.hasStarted = false;
        gameID = UUID.randomUUID().toString();
        players = new ArrayList<>();

    }

    public Game(ArrayList<Player> players, boolean hasStarted, String gameID, String gameName, int playerMax) {
        this.players = players;
        this.hasStarted = hasStarted;
        this.gameID = gameID;
        this.gameName = gameName;
        this.playerMax = playerMax;
    }

    public int getPlayerMax() {
        return playerMax;
    }

    public void setPlayerMax(int playerMax) {
        this.playerMax = playerMax;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public boolean canJoinGame() {
        return !hasStarted;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public boolean addPlayer(Player p) {
        if (players.contains(p)) {
            return false;
        } else {
            players.add(p);
            return true;
        }
    }

    public void removePlayer(Player player) {
        players.remove(players.indexOf(player));
    }

    public String getGameID() {
        return gameID;
    }

    public boolean isHasStarted() {
        return hasStarted;
    }

    public void setHasStarted(boolean hasStarted) {
        this.hasStarted = hasStarted;
    }
}
