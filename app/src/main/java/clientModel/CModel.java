package clientModel;

import java.util.List;
import java.util.Set;

import modeling.Game;
import modeling.Player;
import modeling.User;

/**
 * Created by tyler on 9/27/2017.
 * The root client Model class
 */
class CModel {
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
    /**Holds our persenters*/
    //private StateMaintainer stateMaintainer;

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
    }

    public void setAllGames(List<Game> allGames) {
        this.allGames = allGames;
    }

    public void setCurrGame(Game currGame) {
        this.currGame = currGame;
    }

    public void setAllPlayers(Set<Player> allPlayers) {
        this.allPlayers = allPlayers;
    }

   /* public void setStateMaintainer(StateMaintainer stateMaintainer) {
        this.stateMaintainer = stateMaintainer;
    }*/
}