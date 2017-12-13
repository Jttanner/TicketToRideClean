package ServerModel;

import java.util.List;
import java.util.Map;

import commandData.Command;
import modeling.Game;
import modeling.User;

/**
 * Created by tyler on 12/5/2017.
 * ServerModel.SQLPlugin
 */

public class SQLPlugin implements IPlugin {
    private IPersistenceManager persistenceManager;

    @Override
    public String getPluginName() {
        return this.getClass().getName();
    }

    @Override
    public User verifyUser(String name, String password)   {
        return persistenceManager.getUserDao().verifyUser(name, password);
    }

    @Override
    public Game getGame(String gameID)   {
        return persistenceManager.getGameDao().getGameState(gameID);
    }

    @Override
    public List<Command> getGameCommands(String gameID)   {
        return persistenceManager.getCommandDao().getCommandList(gameID);
    }

    @Override
    public boolean saveUser(User user)   {
        return persistenceManager.getUserDao().registerUser(user.getInfo().getUserName(),user.getInfo().getPassword());
    }

    @Override
    public boolean saveGame(Game game)  {
        return persistenceManager.getGameDao().updateGameState(game);
    }

    @Override
    public boolean clearCommandList(String gameID) {
        return persistenceManager.getCommandDao().clear();
    }

    @Override
    public List<Game> getAllGames() {
        return persistenceManager.getGameDao().getAllGames();
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public Map<String, List<Command>> getAllCommands() {
        return null;
    }

    @Override
    public boolean clear()   {
        return persistenceManager.clearDatabase();
    }

    @Override
    public boolean saveGameCommands(String gameID, Command commands) {
        return persistenceManager.getCommandDao().addCommandsToGame(gameID,commands);
    }

    @Override
    public void setPManager(IPersistenceManager persistenceManager) {
        this.persistenceManager = persistenceManager;
    }

}
