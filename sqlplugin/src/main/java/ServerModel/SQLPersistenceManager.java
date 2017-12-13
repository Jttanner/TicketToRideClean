package ServerModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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

    String databaseURL = "jdbc:sqlite:ttrdb.sqlite";
    private ICommandDao commandDao = new SQLiteCommandDao(databaseURL);

    @Override
    public void beginTransaction() {
        String query = "BEGIN TRANSACTION;";
        try{
            Connection connection = DriverManager.getConnection(databaseURL);
            Statement statement = connection.createStatement();
            statement.execute(query);
        }catch (SQLException e){

        }
    }

    @Override
    public boolean endTransaction() {

        String query = "COMMIT;";
        try{
            Connection connection = DriverManager.getConnection(databaseURL);
            Statement statement = connection.createStatement();
            statement.execute(query);
            return true;
        }catch (SQLException e){
            String rollbackQuery = "ROLLBACK;";
            try{
                Connection connection = DriverManager.getConnection(databaseURL);
                Statement statement = connection.createStatement();
                statement.execute(query);
                return false;
            }catch (SQLException e2){
                return  false;
            }
        }
    }

    @Override
    public boolean clearDatabase() {
        getCommandDao().clear();
        getGameDao().clear();
        getUserDao().clear();
        return true;
    }

//    @Override
//    public void createGameDao() {
//        if (gameDao == null){
//            try{
//                gameDao = new ServerModel.SQLiteGameDao(DriverManager.getConnection(databaseURL));
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
//                userDao = new ServerModel.SQLiteUserDao(DriverManager.getConnection(databaseURL));
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
//                commandDao = new ServerModel.SQLiteCommandDao(DriverManager.getConnection(databaseURL));
//            }catch (SQLException e){
//                e.printStackTrace();
//            }
//        }
//    }

    @Override
    public IUserDao getUserDao() {
        if (userDao == null){
            try{
                userDao = new SQLiteUserDao(databaseURL);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return userDao;
    }

    @Override
    public IGameDao getGameDao() {
        if (gameDao == null){
            try{
                gameDao = new SQLiteGameDao(databaseURL);
            } catch (Exception e){
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
                commandDao = new SQLiteCommandDao(databaseURL);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        System.out.println("GET COMMAND DAO");
        return commandDao;
    }

}
