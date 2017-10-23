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

    //Comparing objects to see if they are truly equal
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
        //All instance variables must match to return true
        return  (this.isHasStarted() == oGame.isHasStarted()) && (this.gameID.equals(oGame.getGameID()))
                && (this.gameName.equals(oGame.gameName))
                && (this.getPlayerMax() == oGame.getPlayerMax())
                && (this.players.equals(oGame.players));
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
    /**This method determines if we can join a game by checking if adding a player would put us over the player max or
     * if the game has already started or not
     * @return boolean*/
    public boolean canJoinGame() {
        boolean stillCanJoin = players.size() >= 0 && players.size() < playerMax;
        //Adding in to see if the user is already in the game
        return !hasStarted && stillCanJoin;
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
    /**Set if the game has started or not
     * @param hasStarted Boolean, if it has started or not
     **/
    public void setHasStarted(boolean hasStarted) {

        this.hasStarted = hasStarted;
        //if we are starting and the first player in the list hasnt had their switch for thier turn toggled yet
        if(this.hasStarted && !players.get(0).isMyTurn()){
            players.get(0).toggleMyTurn();
        }
    }
}
