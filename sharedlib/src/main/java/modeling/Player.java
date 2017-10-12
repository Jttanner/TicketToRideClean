package modeling;

import java.util.Comparator;

/**
 * Created by tyler on 9/26/2017.
 * This class represents all the data needed for a player in Ticket to Ride
 */

public class Player implements Comparator<Player> {

    private String userID;
    private String Name;
    private String color;

    public Player(String userID){
        this.userID = userID;
    }

    public Player(String userID, String name, String color) {
        this.userID = userID;
        Name = name;
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public void startGame(Game game){

    }

    public void forfeitGame(Game game){

    }

    public void leaveGame(Game game){

    }

    @Override
    public int compare(Player player, Player other) {
        return Integer.parseInt(player.userID) - Integer.parseInt(other.userID);
    }
}
