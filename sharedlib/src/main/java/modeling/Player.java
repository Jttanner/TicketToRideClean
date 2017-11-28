package modeling;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tyler on 9/26/2017.
 * This class represents all the data needed for a player in Ticket to Ride
 */

public class Player implements Comparator<Player> {

    private String userName;
    private String playerName;
    private String color;
    private TrainCarList trainCarList;
    private Map<String,List<ResourceCard>> resourceCards;
    private List<DestinationCard> destinationCards = new ArrayList<>();
    private List<Route> routes = new ArrayList<>();
    private int points;
    private List<DestinationCard> temporaryHand = new ArrayList();

    public void setPoints(int points) {
        this.points = points;
    }

    /**Where in the Commadlist we are for this player*/
    private int commandIndex;
    private boolean isMyTurn;

    public Player(String userName){
        this.userName = userName;
        this.playerName = userName;
        setup();
    }

    public Player(String userName, String name, String color) {
        this.userName = userName;
        playerName = name;
        this.color = color;
        setup();

    }

    private void setup() {
        resourceCards = new HashMap<>();
        trainCarList = new TrainCarList();
        //Setup the resource card map
        resourceCards.put("Red",new ArrayList<ResourceCard>());
        resourceCards.put("Blue",new ArrayList<ResourceCard>());
        resourceCards.put("Black",new ArrayList<ResourceCard>());
        resourceCards.put("Yellow",new ArrayList<ResourceCard>());
        resourceCards.put("Orange",new ArrayList<ResourceCard>());
        resourceCards.put("Wild",new ArrayList<ResourceCard>());
        resourceCards.put("Green",new ArrayList<ResourceCard>());
        resourceCards.put("Purple",new ArrayList<ResourceCard>());
        resourceCards.put("White",new ArrayList<ResourceCard>());
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

    public void discardResourceCardsToPlaceCars(Route route, Game game){
        List<ResourceCard> correctColorResourceCard = resourceCards.get(route.getTrainColorNeeded());
        List<ResourceCard> wildCardList = resourceCards.get("Wild");
        List<ResourceCard> discardedCards = new ArrayList<>();
        for (int i = 0; i < route.getDistance(); ++i){
            ResourceCard spentCard = null;
            if (correctColorResourceCard.size() > 0){
                spentCard = correctColorResourceCard.remove(0);
            } else{
                spentCard = wildCardList.remove(0);
            }
            discardedCards.add(spentCard);
        }

    }

    @Override
    public int compare(Player player, Player other) {
        return Integer.parseInt(player.userName) - Integer.parseInt(other.userName);
    }

    /**Grabs the current number of destination cards*/
    public int getNumOfResourceCardsTotal(){
        int num = 0;
        Collection<List<ResourceCard>> cards = resourceCards.values();
        for(List<ResourceCard> cardArrayList : cards){
            num += cardArrayList.size();
        }
        return num;
    }

    @Override
    public String toString() {
        //I know..its ugly, just a quick fix
        //String.format()
        return playerName + "\t\t\t" + points + "\t\t\t\t\t" + trainCarList.getNumOfCars() + "\t\t\t\t\t" + getNumOfResourceCardsTotal() + "\t\t\t\t\t" + destinationCards.size() + "\t";
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

    /**Gets resource card map*/
    public Map<String,List<ResourceCard>> getResourceCards() {
        return resourceCards;
    }
    /**Gets the list of the appropriate color*/


    public List<ResourceCard> getResourceColorList(String color){
        return Collections.unmodifiableList(resourceCards.get(color));
    }

    /**Adds a resource card to the player's hand
     * @param c The resource card*/
    public void addResourceCard(ResourceCard c){
        //TODO need to add player as owner? Do we even needs this method, wont server handle it?
        List<ResourceCard> cardList = resourceCards.get(c.getMyColor());
        cardList.add(c);
    }

    public List<Route> getRoutes() {
        return routes;
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
    public void addDestinationCards(List<DestinationCard> cards){
        destinationCards.addAll(cards);
    }

    public void addDestinationCard(DestinationCard card){
        destinationCards.add(card);
    }

    public void addtoTemporaryHand(List<DestinationCard> cards) {
        temporaryHand.addAll(cards);
    }

    public List<DestinationCard> getTemporaryHand() {
        return temporaryHand;
    }

    public void clearTemporaryHand() {
        temporaryHand.clear();
    }

    public boolean isMyTurn() {
        return isMyTurn;
    }

    public void toggleMyTurn() {
        this.isMyTurn = !this.isMyTurn;
    }

    public void setIsMyTurn(boolean isMyTurn){
        this.isMyTurn = isMyTurn;
    }

    public TrainCarList getTrainCarList() {
        return trainCarList;
    }

    public int getCommandIndex() {
        return commandIndex;
    }

    public void incrementCommandIndex(){
        commandIndex++;
    }
}
