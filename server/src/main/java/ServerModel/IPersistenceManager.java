package ServerModel;

/**
 * Created by tyler on 12/5/2017.
 */

interface IPersistenceManager {

    void beginTransaction();

    boolean endTransaction();

    boolean clearDatabase()throws NeedTransactionException;

    void createGameDao();

    void createUserDao();

    void createPlayerDao();

    void createCommandDao();

    IUserDao getUserDao();

    IGameDao getGameDao();

    IPlayerDao getPlayerDao();

    ICommandDao getCommandDao();

}
