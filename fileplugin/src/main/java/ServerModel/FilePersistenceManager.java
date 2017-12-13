package ServerModel;

/**
 * Created by tyler on 12/5/2017.
 */

public class FilePersistenceManager implements IPersistenceManager {
//    Loader loader;
//    ArrayList<String> fileArgs;
//    String fileName;
//    ServerModel.FilePersistenceManager (String fileName) {
//        loader = new Loader();
//        fileArgs = loader.readFile(fileName);
//        this.fileName = fileName;
//    }


    private IGameDao gameDao;

    private IUserDao userDao;

    private ICommandDao commandDao;

    @Override
    public void beginTransaction() {
        return;
    }

    @Override
    public boolean endTransaction() {
        return true;
    }

    @Override
    public boolean clearDatabase() {
        if(userDao == null) {
            userDao = new FileUserDao();
        }
        if(gameDao == null) {
            gameDao = new FileGameDao();
        }
        if(commandDao == null) {
            commandDao = new FileCommandDao();
        }
        userDao.clear();
        gameDao.clear();
        commandDao.clear();
        return true;
    }

    @Override
    public IUserDao getUserDao() {
        if(userDao == null) {
            //Create the gameDao via reflection?
            //userDao = (ServerModel.FileUserDao) loader.loadClass(fileName, fileArgs.get(4));
            userDao = new FileUserDao();
        }
        return userDao;
    }

    @Override
    public IGameDao getGameDao() {
        if(gameDao == null) {
            //Create the gameDao via reflection?
            //gameDao = (ServerModel.FileGameDao) loader.loadClass(fileName, fileArgs.get(2));
            gameDao = new FileGameDao();
        }
        return gameDao;
    }


    @Override
    public ICommandDao getCommandDao() {
        if(commandDao == null) {
            //Create the gameDao via reflection?
            //commandDao = (ServerModel.FileCommandDao) loader.loadClass(fileName, fileArgs.get(3));
            commandDao = new FileCommandDao();
        }
        return commandDao;
    }

}
