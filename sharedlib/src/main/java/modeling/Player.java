package modeling;

import java.util.Comparator;

/**
 * Created by tyler on 9/26/2017.
 * This class represents all the data needed for a player in Ticket to Ride
 */

public class Player implements Comparator<Player> {

    public Player(String userID){
        this.userID = userID;
    }

    private String userID;
    private String Name;
    private String Color;

    public int getColor() {
        return getColor();
    }

    public void setColor(String color) {
        Color = color;
    }
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public void startGame(Game game){

    }

    public void forfietGame(Game game){

    }

    public void leaveGame(Game game){

    }

    @Override
    public int compare(Player player, Player other) {
        return Integer.parseInt(player.userID) - Integer.parseInt(other.userID);
    }
}
