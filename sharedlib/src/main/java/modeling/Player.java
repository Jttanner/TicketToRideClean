package modeling;

/**
 * Created by tyler on 9/26/2017.
 * This class represents all the data needed for a player in Ticket to Ride
 */

public class Player {

    public Player(String userID){
        this.userID = userID;
    }

    String userID;
    String Name;

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

}
