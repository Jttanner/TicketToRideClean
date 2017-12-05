package ServerModel;

/**
 * Created by tyler on 12/5/2017.
 */

interface IPersistenceManager {

    public void beginTransaction();

    public boolean endTransaction();

    public void clearDatabase();

    public void createGameDao();

    public void createUserDao();

    public void createPlayerDao();

    public void createCommandDao();

    public IUserDao getUserDao();

    public IGameDao getGameDao();

    public IPlayerDao getPlayerDao();

    public ICommandDao getCommandDao();
}
