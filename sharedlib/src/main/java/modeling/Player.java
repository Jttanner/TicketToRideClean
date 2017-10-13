package modeling;

import java.util.Comparator;

/**
 * Created by tyler on 9/26/2017.
 * This class represents all the data needed for a player in Ticket to Ride
 */

public class Player implements Comparator<Player> {

    private String userName;
    private String playerName;
    private String color;

    public Player(String userName){
        this.userName = userName;
    }

    public Player(String userName, String name, String color) {
        this.userName = userName;
        playerName = name;
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String name) {
        playerName = name;
    }

    public void startGame(Game game){

    }

    public void forfeitGame(Game game){

    }

    public void leaveGame(Game game){

    }

    @Override
    public int compare(Player player, Player other) {
        return Integer.parseInt(player.userName) - Integer.parseInt(other.userName);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (!(o instanceof Player)) {
            return false;
        }
        Player oPlayer = (Player) o;
        //All instance variables must match to return true
        return (this.getColor().equals(oPlayer.getColor())) && (this.getUserName().equals(oPlayer.getUserName()))
                && (this.getPlayerName().equals(oPlayer.getPlayerName()));
    }

    public String getUserName() {
        return userName;
    }
}
