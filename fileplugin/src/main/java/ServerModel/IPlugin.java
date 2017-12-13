package ServerModel;

import java.util.List;
import java.util.Map;

import commandData.Command;
import modeling.Game;
import modeling.User;

/**
 * Created by tyler on 12/5/2017.
 */

public interface IPlugin {
    /**Gets the plugin*/
    String getPluginName();
    /**Gets the user associated with a playername*/
    User verifyUser(String playerName, String password);
    /**Gets a game from a its gameID*/
    Game getGame(String gameID) ;
    /**Gets a list of commands associated with a gameid*/
    List<Command> getGameCommands(String gameID) ;
    /**saves a user object to the DB*/
    boolean saveUser(User user) ;
    /**Saves a game to the game*/
    boolean saveGame(Game game) ;

    boolean saveGameCommands(String gameID, Command commands);
    /**Clears the database*/
    boolean clear();
    /**Sets the p Manager*/
    void setPManager(IPersistenceManager persistenceManager);

    boolean clearCommandList(String gameID);

    List<Game> getAllGames();

    List<User> getAllUsers();

    Map<String,List<Command>> getAllCommands();
}
