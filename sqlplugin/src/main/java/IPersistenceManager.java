/**
 * Created by tyler on 12/5/2017.
 */

interface IPersistenceManager {
    void beginTransaction();

    boolean endTransaction();

    boolean clearDatabase();

    IUserDao getUserDao();

    IGameDao getGameDao();

    ICommandDao getCommandDao();

}
