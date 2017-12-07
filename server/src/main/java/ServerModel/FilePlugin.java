package ServerModel;

import java.util.List;

import commandData.Command;
import modeling.Game;
import modeling.User;

/**
 * Created by tyler on 12/5/2017.
 */

public class FilePlugin implements IPlugin {
    private IPersistenceManager persistenceManager;

    @Override
    public String getPluginName() {
        return this.getClass().getName();
    }

    @Override
    public User verifyUser(String name) throws NeedTransactionException {
        return persistenceManager.getUserDao().verifyUser(name);
    }

    @Override
    public Game getGame(String gameID) throws NeedTransactionException {
        return persistenceManager.getGameDao().getGameState(gameID);
    }

    @Override
    public List<Command> getGameCommands(String gameID) throws NeedTransactionException {
        return persistenceManager.getCommandDao().getCommandList(gameID);
    }

    @Override
    public boolean saveUser(User user) throws NeedTransactionException {
        return persistenceManager.getUserDao().registerUser(user.getInfo().getUserName(),user.getInfo().getPassword());
    }

    @Override
    public boolean saveGame(Game game) throws NeedTransactionException {
        return persistenceManager.getGameDao().updateGameState(game);
    }

    @Override
    public boolean clear() throws NeedTransactionException {
        return persistenceManager.clearDatabase();
    }

    @Override
    public boolean saveGameCommands(String gameID, List<Command> commands) throws NeedTransactionException {
        return persistenceManager.getCommandDao().addCommandsToGame(gameID,commands);
    }


    @Override
    public void setPManager(IPersistenceManager persistenceManager) {
        this.persistenceManager = persistenceManager;
    }

    @Override
    public String getPManagerClassName() {
        return "FilePersistenceManager";
    }
}
