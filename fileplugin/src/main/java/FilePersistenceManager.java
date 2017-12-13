import java.util.ArrayList;

/**
 * Created by tyler on 12/5/2017.
 */

public class FilePersistenceManager implements IPersistenceManager {
//    Loader loader;
//    ArrayList<String> fileArgs;
//    String fileName;
//    FilePersistenceManager (String fileName) {
//        loader = new Loader();
//        fileArgs = loader.readFile(fileName);
//        this.fileName = fileName;
//    }


    private IGameDao gameDao;

    private IUserDao userDao;

    private ICommandDao commandDao;

    @Override
    public void beginTransaction() {

    }

    @Override
    public boolean endTransaction() {
        return false;
    }

    @Override
    public boolean clearDatabase() {
        return false;
    }

    @Override
    public IUserDao getUserDao() {
        if(userDao == null) {
            //Create the gameDao via reflection?
            //userDao = (FileUserDao) loader.loadClass(fileName, fileArgs.get(4));
            userDao = new FileUserDao();
        }
        return userDao;
    }

    @Override
    public IGameDao getGameDao() {
        if(gameDao == null) {
            //Create the gameDao via reflection?
            //gameDao = (FileGameDao) loader.loadClass(fileName, fileArgs.get(2));
            gameDao = new FileGameDao();
        }
        return gameDao;
    }


    @Override
    public ICommandDao getCommandDao() {
        if(commandDao == null) {
            //Create the gameDao via reflection?
            //commandDao = (FileCommandDao) loader.loadClass(fileName, fileArgs.get(3));
            commandDao = new FileCommandDao();
        }
        return commandDao;
    }

}
