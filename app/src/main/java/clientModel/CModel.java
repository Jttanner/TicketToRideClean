package clientModel;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import modeling.City;
import modeling.CityList;
import modeling.DestinationCard;
import modeling.DestinationCardList;
import modeling.Game;
import modeling.GameList;
import modeling.History;
import modeling.Player;
import modeling.ResourceCard;
import modeling.Route;
import modeling.RouteList;
import modeling.TrainCarList;
import modeling.User;
import poller.Poller;

/**
 * Created by tyler on 9/27/2017.
 * The root client Model class
 */
public class CModel extends Observable {
    private static final String TAG = "CModel";
    private static CModel ourInstance = new CModel();

    public static CModel getInstance() {
        return ourInstance;
    }

    /**
     * The user associated with this client model
     */
    private User myUser;




    /**
     * The list of games being played or waiting to be played
     */
    private List<Game> allGames = new ArrayList<>();
    /**
     * The game the player is currently playing
     */
    private Game currGame;
    /**
     * The set of players you are playing with
     */
    private Set<Player> allPlayers;
    /**The command Manager, holds our Map of gameId's to CommandLists*/
    private CommandManager commandManager = new CommandManager();

    private GameState currGameState;

    private List<String> chatHistory = new ArrayList<>();

    private List<DestinationCard> temporaryHand;

    public void setTemporaryHand(List<DestinationCard> temporaryHand) {
        this.temporaryHand = temporaryHand;
    }

    private List<DestinationCard> claimedDestinationCards;

    public List<String> getChatHistory() {
        return chatHistory;
    }

    public void setChatHistory(List<String> chatHistory) {
        this.chatHistory = chatHistory;
    }


    //Call this when the commands that will update the Game History are executed
    public void updateCurrGameHistoryList(String history, String gameID) {
        for(Game game: allGames){
            if(game.getGameID().equals(gameID)){
                game.addToGameHistory(history);
            }
        }
        setChanged();
        notifyObservers("UpdateGameHistory");
    }
    public void closeResourceFragment() {
        setChanged();
        notifyObservers("CloseResourceFragment");
    }
    public void upDateFaceUpPile() {
        setChanged();
        notifyObservers("UpdateFaceUpView");
    }

    public void updatePlayerStatsView () {
        Game game = currGame;
        setChanged();
        notifyObservers(currGame);
    }
    public void resourceCardButtonsOn() {
        setChanged();
        notifyObservers("ResourceCardButtonsOn");
    }
    public void resourceCardButtonsOff() {
        setChanged();
        notifyObservers("ResourceCardButtonsOff");
    }
    private CModel() {
    }

    public User getMyUser() {
        return myUser;
    }

    public List<Game> getAllGames() {
        return allGames;
    }

    public Game getCurrGame() {
        return currGame;
    }



    /*
    public List<DestinationCard> threeDestinationCards(){
        DestinationCardList destinationCardList = new DestinationCardList();
        return destinationCardList.get3Cards();
    }*/



    public void UpdateHistory(List<String> history,String gameID){
        for(Game game: allGames){
            if(game.getGameID().equals(gameID)){
                game.setChatHistory(history);
            }
        }
        if(currGame!=null){
            currGame.setChatHistory(history);
        }

        setChanged();
        notifyObservers("addChat");
    }

    /**Increments the command index of the appropriate user player*/
    void incrementUsersCommandIndex(){
        Player myPlayer = this.currGame.getPlayer(getMyUser().getUserName());
        myPlayer.incrementCommandIndex();
    }
    /**Is called by the result of starting a game if I started the game or by being executed by the CommandManager*/
    public void toggleGameHasStarted() {
        Poller.getInstance().stopPoller();
        Log.d(TAG,"setting game has started for game " + this.currGame.getGameName() + " to value of: " + !this.currGame.isHasStarted());
        //this.currGame.setHasStarted(!this.currGame.isHasStarted());
        for(Game game : allGames){
            if(game.getGameID().equals(currGame.getGameID())){
                game.setHasStarted(true);
                //Set our current game to the updated game
                this.currGame = game;
                //updatePlayerStatsView(game);
            }
        }

        //incrementUsersCommandIndex();
        setChanged();
        notifyObservers(Boolean.TRUE);
        updatePlayerStatsView();
    }

    public Set<Player> getAllPlayers() {
        return allPlayers;
    }

    public void setMyUser(User myUser) {
        Log.d(TAG,"Setting user");
        if (myUser != null && myUser.getInfo() != null) {
            this.myUser = myUser;
            setChanged();
            notifyObservers(myUser);
        } else {
            setChanged();
            notifyObservers(false);
        }
    }
    /**Gets the user's player object
     * @return Player User's player object*/
    public Player getUserPlayer(){
        String userName = getMyUser().getInfo().getUserName();
        ArrayList<Player> players = currGame.getPlayers();
        for(Player player : players){
            if(player.getUserName().equals(userName)){
                return player;
            }
        }
        return null;
    }


    /**
     * This method updates the current game for the Playerlist as well as updating the GameList
     *
     * @param allGames THe games we got from the server
     */
    public void setAllGames(GameList allGames) {
        Log.d(TAG,"Setting all games");
        if (allGames.getGames().size() != 0) {
            this.allGames = allGames.getGames();
            if(this.currGame != null) {
                Game g = allGames.findGame(this.currGame.getGameID());
                if (g != null) {
                    this.currGame = g;
                }
            }
            //will notify the gamelist activity of games made/changed
            setChanged();
            GameList gameList = new GameList();
            gameList.setGames(this.allGames);
            notifyObservers(gameList);
        }
    }

    public void setThreeDestinationCards(Player player){
        Log.d(TAG,"Setting destination cards");
        //So the code below takes out the old version of the game we are joining and adds the new one, which has the updated player list

        //set currGame
        //this.threeDestinationCards = threeDestinationCards;
        setChanged();
        notifyObservers(player);
    }

    /**
     * Sets the current game for the user. takes a game object that was saved client side until the server said we were good to
     * make it. This method is used when we join a game
     *
     * @param currGame The game that was just made
     */

    public void setCurrGame(Game currGame) {
        Log.d(TAG,"Setting current game");
        //set currGame
        this.currGame = currGame;
        updateCurrGame();
        setChanged();
        notifyObservers(this.currGame);
    }
    /*Replaces curr game in list**/
    private void updateCurrGame(){
        //So the code below takes out the old version of the game we are joining and adds the new one, which has the updated player list
        if(currGame != null) {
            for (Game g : allGames) {
                if (g.getGameID().equals(currGame.getGameID())) {
                    allGames.remove(g);
                    //add currgame to list
                    allGames.add(currGame);
                    break;
                }
            }
        }
    }

    public void updateRoutes(Game currGame, Route route, Player player){
        //Player userPlayer = CModel.getInstance().getUserPlayer();
        boolean isWild = route.getTrainColorNeeded().equals("Wild") ? true : false;
        currGame.claimAvailableRoute(route, player, isWild);
        player.addRoute(route);
        player.addPoints(route.getPointValue());

        setChanged();
        notifyObservers(currGame);
    }




    //KWANS STUFF :) hey kwan u r kool

    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
        String str = String.valueOf(countObservers());
        Log.d(TAG, "Number of observers " + str);
    }

    @Override
    public synchronized void deleteObserver(Observer o) {
        super.deleteObserver(o);
        String str = String.valueOf(countObservers());
        Log.d(TAG, "Number of observers " + str);
    }

    @Override
    public void notifyObservers() {
        super.notifyObservers();
    }

    @Override
    public void notifyObservers(Object arg) {
        if (arg != null){
            Log.d(TAG, "Notifying observers: sending class " + arg.getClass().toString());
        }
        super.notifyObservers(arg);
    }

    @Override
    public synchronized void deleteObservers() {
        super.deleteObservers();
    }

    @Override
    protected synchronized void setChanged() {
        super.setChanged();
    }

    @Override
    protected synchronized void clearChanged() {
        super.clearChanged();
    }

    @Override
    public synchronized boolean hasChanged() {
        return super.hasChanged();
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public GameState getCurrGameState() {
        return currGameState;
    }

    public void setCurrGameState(GameState currGameState) {
        this.currGameState = currGameState;
        setChanged();
        notifyObservers(this.currGameState);
    }

    public String getIPAddress() {
        return IPAddress;
    }

    private String IPAddress = "";

    public void setIPAddress(String IPAddress) {
        this.IPAddress = IPAddress;
    }
}