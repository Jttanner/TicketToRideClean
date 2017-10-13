package modeling;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by tyler on 9/26/2017.
 * The user is the client, i mean who doesn't know what a user is
 */
public class User {
    public User(UserInfo info){
        this.info = info;
    }

    private UserInfo info;
    private Set<Game> activeGames = new HashSet<>();
    private Set<Player> ownedPlayers = new HashSet<>();

    public void addGame(Game game){
        activeGames.add(game);
    }

    public void removeGame(Game game){
        activeGames.remove(game);
    }

    public void addPlayer(Player player){
        ownedPlayers.add(player);
    }

    public void removePlayer(Player player){
        ownedPlayers.remove(player);
    }

    public String getUserName(){
        return info.getUserName();
    }

    public UserInfo getInfo() {
        return info;
    }

    //Join an existing game.
    public void joinGame(){

    }

    //Returns reference to the created game.
    public Game createNewGame(){
        return null;
    }


    //Deletes an existing game which has been created but not started, or has finished.
    public void deleteGame(){

    }

}
