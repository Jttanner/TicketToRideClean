package modeling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by tyler on 9/26/2017.
 * This class represents all the data needed for a player in Ticket to Ride
 */

public class Player implements Comparator<Player> {

    private String userName;
    private String playerName;
    private String color;
    private List<ResourceCard> resourceCards = new ArrayList<>();
    private List<DestinationCard> destinationCards = new ArrayList<>();
    private List<Route> routes = new ArrayList<>();
    private int points;

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
    public String toString() {
        return playerName + "/n" + points + "/n" + resourceCards.size() + "/n" + destinationCards.size() + "/n" + routes.size();
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

    public List<ResourceCard> getResourceCards() {
        return Collections.unmodifiableList(resourceCards);
    }
    /**Adds a resource card to the player's hand
     * @param c The resource card*/
    public void addResourceCard(ResourceCard c){
        resourceCards.add(c);
    }

    public List<Route> getRoutes() {
        return Collections.unmodifiableList(routes);
    }
    /**Adds a route to what the player has claimed already
     * @param route Their newly claimed route*/
    public void addRoute(Route route){
        routes.add(route);
    }

    public int getPoints() {
        return points;
    }

    /**Adds some number of points, positive or negative, to the player's score*/
    public void addPoints(int pointsToAdd){
        points += pointsToAdd;
    }

    public List<DestinationCard> getDestinationCards() {
        return Collections.unmodifiableList(destinationCards);
    }

    /**Adds a list of destination cards to what the player has already
     * @param cards Their newly added*/
    public void addDestinationCard(List<DestinationCard> cards){
        destinationCards.addAll(cards);
    }
}
