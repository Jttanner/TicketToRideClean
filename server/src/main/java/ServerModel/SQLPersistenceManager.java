package ServerModel;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by tyler on 12/5/2017.
 */

public class SQLPersistenceManager implements IPersistenceManager {
    /**The GameDAo*/
    private IGameDao gameDao;
    /**The UserDao*/
    private IUserDao userDao;
    /**The PlayerDao*/
//    private IPlayerDao playerDao;
    /**The CommandDao*/
    private ICommandDao commandDao;

    String pluginName = "SQLPlugin";

    String databaseURL = "c/Users/jontt/AndroidStudioProjects/TicketToRidePhase3/TicketToRidePhase3Testing/test.db";

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

//    @Override
//    public void createGameDao() {
//        if (gameDao == null){
//            try{
//                gameDao = new SQLiteGameDao(DriverManager.getConnection(databaseURL));
//            } catch (SQLException e){
//                e.printStackTrace();
//            }
//        }
//    }
//
//    @Override
//    public void createUserDao() {
//        if (userDao == null){
//            try{
//                userDao = new SQLiteUserDao(DriverManager.getConnection(databaseURL));
//            }catch (SQLException e){
//                e.printStackTrace();
//            }
//        }
//    }
//
//    @Override
//    public void createPlayerDao() {
//        if (playerDao == null){
//            try{
//                playerDao = new SQLitePlayerDao(DriverManager.getConnection(databaseURL));
//            }catch (SQLException e){
//                e.printStackTrace();
//            }
//        }
//    }
//
//    @Override
//    public void createCommandDao() {
//        if (commandDao == null){
//            try{
//                commandDao = new SQLiteCommandDao(DriverManager.getConnection(databaseURL));
//            }catch (SQLException e){
//                e.printStackTrace();
//            }
//        }
//    }

    @Override
    public IUserDao getUserDao() {
        if (userDao == null){
            try{
                //userDao = new SQLiteUserDao(DriverManager.getConnection(databaseURL));
                Class<?> myUserDaoClass = null;
                myUserDaoClass = Class.forName(pluginName + "UserDao");
                userDao = (IUserDao) myUserDaoClass.getConstructor(Connection.class).newInstance(DriverManager.getConnection(databaseURL));
            }catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return userDao;
    }

    @Override
    public IGameDao getGameDao() {
        if (gameDao == null){
            try{
                Class<?> myGameDaoClass = null;
                myGameDaoClass = Class.forName(pluginName);
                gameDao = (IGameDao) myGameDaoClass.getConstructor(Connection.class).newInstance(DriverManager.getConnection(databaseURL));
            } catch (ClassNotFoundException e){
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return gameDao;
    }

//    @Override
//    public IPlayerDao getPlayerDao() {
//        if (playerDao == null){
//            createPlayerDao();
//        }
//        return playerDao;
//    }

    @Override
    public ICommandDao getCommandDao() {
        if (commandDao == null){
            try{
               // commandDao = new SQLiteCommandDao(DriverManager.getConnection(databaseURL));
                Class<?> myCommandDaoClass = null;
                myCommandDaoClass = Class.forName(pluginName + "SQLiteCommandDao");
                commandDao = (ICommandDao) myCommandDaoClass.getConstructor(Connection.class).newInstance(DriverManager.getConnection(databaseURL));
            }catch (ClassNotFoundException e){
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return commandDao;
    }

}
