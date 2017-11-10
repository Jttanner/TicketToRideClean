package modeling;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * The Game stores information pertaining to an ongoing game in the app Ticket to Ride.
 *
 * DOMAIN
 *   gameHistoryList: ArrayList<String>, history of the Game
 *   players: ArrayList<Player>, players in game
 *   chatHistory: List<String>, game chat history
 *   hasStarted: boolean, tells us whether the game is in a start state
 *   gameID: String, unique ID for the game
 *   gameName: String, the name given by user creating this game
 *   playerMax: int, maximum number of players in one game
 *   resourceCardList: ResourceCardList, the deck or list of resource cards the game has
 *
 * {@invariant getPlayers.size() > playerMax}
 */
public class Game {

    //Data Members
    private ArrayList<String> gameHistoryList = new ArrayList<>();
    private ArrayList<Player> players;
    private List<String> chatHistory = new ArrayList<>();
    private boolean hasStarted;
    private String gameID;
    private String gameName;
    private int playerMax;
    private ResourceCardList resourceCardList;

    //**************************CONSTRUCTORS*************************************************************************************************//

    /**
     * Constructs a new Game which has its resource cards initialized, given an unique game ID, and game has not started yet
     *
     * @params empty
     *
     * {@post isHasStarted = false}
     * {@post getGameID() is unique}
     * {@post getPlayers().size() == 0}
     * {@post getResourceCardList != null}
     */
    public Game() {
        this.hasStarted = false;
        gameID = UUID.randomUUID().toString();
        players = new ArrayList<>();
        //card deck created for the game
        resourceCardList = new ResourceCardList();
    }
    /**
     * Constructs a new Game which has its resource cards initialized, given an unique game ID, and game has not started yet
     *
     * @params players Players in the game
     * @params hasStarted Boolean telling if the game has started
     * @params gameID Unique ID for the game
     * @params gameName Name of the game that the user created
     * @params playerMax Number of maximum players in one game
     *
     * {@pre players != null players.size() > 2 && players.size() <= 5}
     * {@pre gameID unique}
     * {@pre gameName != null}
     * {@pre playerMax < 2 && playerMax <= 5}
     *
     *
     * {@post getPlayerMax() == playerMax}
     */
    public Game(ArrayList<Player> players, boolean hasStarted, String gameID, String gameName, int playerMax) {
        this.players = players;
        this.hasStarted = hasStarted;
        this.gameID = gameID;
        this.gameName = gameName;
        this.playerMax = playerMax;
    }

    //*****************************METHODS*************************************************************************************************//

    /**Set if the game has started or not
     * @param hasStarted Boolean, if it has started or not
     * @return nothing
     * {@pre hasStarted != null}
     *
     * {@post isHasStarted() == hasStarted}
     *
     **/
    public void setHasStarted(boolean hasStarted) {
        this.hasStarted = hasStarted;
        //if we are starting and the first player in the list hasnt had their switch for thier turn toggled yet
        if(this.hasStarted && !players.get(0).isMyTurn()){
            setupStartingCards();//setup starting cards when game starts
            players.get(0).toggleMyTurn();
        }
    }

    /**This method determines if we can join a game by checking if adding a player would put us over the player max or
     * if the game has already started or not
     *
     * @return boolean
     *
     * {@pre none}
     * {@post return value is true if the game hasn't started and stillCanJoin is true}
     */
    public boolean canJoinGame() {
        boolean stillCanJoin = players.size() >= 0 && players.size() < playerMax;
        //Adding in to see if the user is already in the game
        return !hasStarted && stillCanJoin;
    }
    /**Adds a player to the game, if the player doesn't already exist in the game
     *
     * @param p Player
     * @return boolean
     *
     * {@pre p != null}
     * {@post return value is true if the user isn't already in the game, otherwise false}
     */
    public boolean addPlayer(Player p) {
        if (players.contains(p)) {
            return false;
        } else {
            players.add(p);
            return true;
        }
    }
//    public void removePlayer(Player player) {
//        players.remove(players.indexOf(player));
//    }
    /**Assigns a player a turn in order
     *
     * @return boolean
     *
     * {@pre none}
     * {@post the next player is assigned a turn}
     */
    public void advancePlayerTurn(){
        boolean wasToggled = false;
        for (Player p : players){
            if (wasToggled){
                p.toggleMyTurn();
                return;
            }
            if (p.isMyTurn()){
                p.toggleMyTurn();
                wasToggled = true;
                if (p.equals(players.get(players.size() - 1))){
                    players.get(0).toggleMyTurn();
                }
            }
        }
    }

    /**Adds a player to the game, if the player doesn't already exist in the game
     *
     * @param playerName String of player name
     * @return Player, returns the Playr if playerName is found in players
     *
     * {@pre players.size() >= 0}
     * {@post if playerName exists in the game return the Player, otherwise returns null}
     * {@post players.size() == pre players.size()}
     */
    public Player getPlayer(String playerName) {
        for(Player player : players){
            if(player.getUserName().equals(playerName)){
                return player;
            }
        }
        return null;
    }

    /**Sets up the cards for the players
     *
     * @return none
     *
     * {@pre players.size() >= 0}
     * {@post players will be assigned cards}
     *
     */
    private void setupStartingCards() {
        resourceCardList.setUpPlayers(players);
    }

    /**Get the player whose turn it is
     * @return The player whose turn it is*/
    public Player getCurrentPlayer(){
        for (Player player : players){
            if(player.isMyTurn()){
                return player;
            }
        }
        return null;
    }
    /**Adds to the gameHistoryList
     *
     * @param oneHistory history being added to the gameHistoryList
     * @return nothing
     *
     * {@pre oneHistory != null}
     * {@post gameHistoryList.size() == pre players.size() + 1}
     */
    public void addToGameHistory(String oneHistory) {
        gameHistoryList.add(oneHistory);
    }

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

    //*****************************Getters and Setters*****************************************************************************************/
    /**
     * Returns the List of String
     *
     * @pre List is not empty
     *
     * @Post
     */
    public List<String> getChatHistory() {
        return chatHistory;
    }
    /**
     * Returns the List of String
     *
     * @pre List is not empty
     *
     * @Post
     */
    public void setChatHistory(List<String> chatHistory) {
        this.chatHistory = chatHistory;
    }
    /**
     * Returns the List of String
     *
     * @pre List is not empty
     *
     * @Post
     */
    public ArrayList<String> getGameHistoryList() {
        return gameHistoryList;
    }
    /**
     * Returns the List of String
     *
     * @pre List is not empty
     *
     * @Post
     */
    public void setGameHistoryList(ArrayList<String> gameHistoryList) {
        this.gameHistoryList = gameHistoryList;
    }
    /**
     * Returns the List of String
     *
     * @pre List is not empty
     *
     * @Post
     */
    public int getPlayerMax() {
        return playerMax;
    }
    /**
     * Returns the List of String
     *
     * @pre List is not empty
     *
     * @Post
     */
    public void setPlayerMax(int playerMax) {
        this.playerMax = playerMax;
    }
    /**
     * Returns the List of String
     *
     * @pre List is not empty
     *
     * @Post
     */
    public String getGameName() {
        return gameName;
    }
    /**
     * Returns the List of String
     *
     * @pre List is not empty
     *
     * @Post
     */
    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
    /**
     * Returns the List of String
     *
     * @pre List is not empty
     *
     * @Post
     */
    public ResourceCardList getResourceCardList() {
        return resourceCardList;
    }
    /**
     * Returns the List of String
     *
     * @pre List is not empty
     *
     * @Post
     */
    public String getGameID() {
        return gameID;
    }
    /**
     * Returns the List of String
     *
     * @pre List is not empty
     *
     * @Post
     */
    public boolean isHasStarted() {
        return hasStarted;
    }
    /**
     * Returns the List of String
     *
     * @pre List is not empty
     *
     * @Post
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }
    /**
     * Returns the List of String
     *
     * @pre List is not empty
     *
     * @Post
     */
    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }
}
