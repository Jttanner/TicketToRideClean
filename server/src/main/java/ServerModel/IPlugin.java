package ServerModel;

import java.util.List;

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
    User getUser(String playerName);
    /**Gets a game from a its gameID*/
    Game getGame(String gameID);
    /**Gets a list of commands associated with a gameid*/
    List<Command> getGameCommands(String gameID);
    /**saves a user object to the DB*/
    boolean saveUser(User user);
    /**Saves a game to the game*/
    boolean saveGame(Game game);
    /**Saves a list of commands for a game*/
    boolean saveGameCommands(String gameID, List<Command> commands);
    /**Clears the database*/
    boolean clear();
    /**Gets the PManager class name*/
    String getPManagerClassName();
    /**Sets the p Manager*/
    void setPManager(IPersistenceManager persistenceManager);
}
