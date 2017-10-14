package clientModel;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import modeling.Game;
import modeling.GameList;
import modeling.Player;
import modeling.User;

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

    /**
     * Our Presenters
     */
    //private Set<Observer> observerSet = new TreeSet<>();
    private CModel() {
    }

    /*public void addGame(Game game){
        this
    }*/


    public User getMyUser() {
        return myUser;
    }

    public List<Game> getAllGames() {
        return allGames;
    }

    public Game getCurrGame() {
        return currGame;
    }

    public void toggleGameHasStarted() {
        this.currGame.setHasStarted(!this.currGame.isHasStarted());
        setChanged();
        notifyObservers(Boolean.TRUE);
    }

    public void addGame(Game game) {
        this.allGames.add(game);
        setChanged();
        GameList wrapperGameList = new GameList();
        wrapperGameList.setGames(this.allGames);
        notifyObservers(wrapperGameList);
    }

    public Set<Player> getAllPlayers() {
        return allPlayers;
    }

    public void setMyUser(User myUser) {
        if (myUser != null) {
            this.myUser = myUser;
            setChanged();
            notifyObservers(myUser);
        } else {
            Log.d(TAG, "You gave us a null user???");
        }
    }

    /**
     * This method updates the current game for the Playerlist as well as updating the GameList
     *
     * @param allGames THe games we got from the server
     */
    public void setAllGames(GameList allGames) {
        if (allGames.getGames().size() != 0) {
            this.allGames = allGames.getGames();

            //Updating the player list
            if (currGame != null) {
                setCurrGame(allGames.findGame(currGame.getGameID()));
            }
            //will notify the gamelist activity of games made/changed
            setChanged();
            notifyObservers(allGames);
        }
    }

    /**
     * Sets the current game for the user. takes a game object that was saved client side until the server said we were good to
     * make it
     *
     * @param currGame The game that was just made
     */
    public void setCurrGame(Game currGame) {
        this.currGame = currGame;
        setChanged();
        notifyObservers(this.currGame);
    }

    /**
     * Sets the current game for the user. takes a gameID from the server to do so
     *
     * @param gameID THe gameID
     */
    public void setCurrGame(String gameID) {
        for (Game g : allGames) {
            if (g.getGameID().equals(gameID)) {
                setCurrGame(g);
            }
        }
    }

    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
        Log.d(TAG, "Number of observers " + String.valueOf(countObservers()));
    }

    @Override
    public synchronized void deleteObserver(Observer o) {
        super.deleteObserver(o);
    }

    @Override
    public void notifyObservers() {
        super.notifyObservers();
    }

    @Override
    public void notifyObservers(Object arg) {
        Log.d(TAG, "Notifying observers: sending class " + arg.getClass().toString());
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
}