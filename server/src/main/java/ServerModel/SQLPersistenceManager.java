package ServerModel;

/**
 * Created by tyler on 12/5/2017.
 */

public class SQLPersistenceManager implements IPersistenceManager {
    @Override
    public void beginTransaction() {

    }

    @Override
    public boolean endTransaction() {
        return false;
    }

    @Override
    public void clearDatabase() {

    }

    @Override
    public void createGameDao() {

    }

    @Override
    public void createUserDao() {

    }

    @Override
    public void createPlayerDao() {

    }

    @Override
    public void createCommandDao() {

    }

    @Override
    public IUserDao getUserDao() {
        return null;
    }

    @Override
    public IGameDao getGameDao() {
        return null;
    }

    @Override
    public IPlayerDao getPlayerDao() {
        return null;
    }

    @Override
    public ICommandDao getCommandDao() {
        return null;
    }
}
