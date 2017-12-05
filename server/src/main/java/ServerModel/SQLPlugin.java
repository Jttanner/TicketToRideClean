package ServerModel;

import java.util.List;

import commandData.Command;
import modeling.Game;
import modeling.User;

/**
 * Created by tyler on 12/5/2017.
 */

public class SQLPlugin implements IPlugin {
    @Override
    public String getPluginName() {
        return null;
    }

    @Override
    public User getUser(String playerName) {
        return null;
    }

    @Override
    public Game getGame(String gameID) {
        return null;
    }

    @Override
    public List<Command> getGameCommands(String gameID) {
        return null;
    }

    @Override
    public boolean saveUser() {
        return false;
    }

    @Override
    public boolean saveGame() {
        return false;
    }

    @Override
    public boolean saveGameCommands() {
        return false;
    }

    @Override
    public boolean clear() {
        return false;
    }

    @Override
    public String getPManager() {
        return "SQLPersistenceManager";
    }
}
