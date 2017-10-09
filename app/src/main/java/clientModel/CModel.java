package clientModel;

import android.util.Log;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import modeling.Game;
import modeling.Player;
import modeling.User;

/**
 * Created by tyler on 9/27/2017.
 * The root client Model class
 */
public class CModel extends Observable{
    private static final String TAG = "CModel";
    private static CModel ourInstance = new CModel();

    public static CModel getInstance() {
        return ourInstance;
    }
    /**The user associated with this client model*/
    private User myUser;
    /**The list of games being played or waiting to be played*/
    private List<Game> allGames;
    /**The game the player is currently playing*/
    private Game currGame;
    /**The set of players you are playing with*/
    private Set<Player> allPlayers;
    /**Our Presenters*/
    //private Set<Observer> observerSet = new TreeSet<>();

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

    public Set<Player> getAllPlayers() {
        return allPlayers;
    }

   /* public StateMaintainer getStateMaintainer() {
        return stateMaintainer;
    }*/

    public void setMyUser(User myUser) {
        this.myUser = myUser;
        /*setChanged();
        notifyObservers(this);*/
    }

    public void setAllGames(List<Game> allGames) {
        this.allGames = allGames;
        setChanged();
        notifyObservers(this.allGames);
    }

    public void setCurrGame(Game currGame) {
        this.currGame = currGame;
        setChanged();
        notifyObservers(this.currGame);
    }

    public void setAllPlayers(Set<Player> allPlayers) {
        this.allPlayers = allPlayers;
        setChanged();
        notifyObservers(this.allPlayers);
    }

    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
        Log.d(TAG,"Number of observers " + String.valueOf(countObservers()));
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