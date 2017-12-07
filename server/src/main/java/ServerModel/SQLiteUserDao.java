package ServerModel;


import java.sql.*;

import modeling.User;
import modeling.UserInfo;

/**
 * Created by jontt on 12/7/2017.
 */

public class SQLiteUserDao implements IUserDao {

    Connection connection;

    SQLiteUserDao(Connection connection){
        this.connection = connection;
        createTableIfNotExists();
    }

    private void createTableIfNotExists(){
        String query = "create table if not exists User \n" +
                "( \n" +
                "Username text unique not null, \n" +
                "Password text not null, \n" +
                ");";
        try{
            Statement statement = connection.createStatement();
            statement.execute(query);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean registerUser(String userName, String password) throws NeedTransactionException {
        String query = "INSERT INTO User(Username, Password) VALUES(?, ?)";
        try{
            if (verifyUser(userName, password) != null){
                return false;
            } else{
                PreparedStatement queryStatement = connection.prepareStatement(query);
                queryStatement.setString(1, userName);
                queryStatement.setString(2, userName);
                queryStatement.execute();
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public User verifyUser(String name, String password) throws NeedTransactionException {
        String query = "SELECT * FROM User WHERE Username=? AND Password=?";
        try{
            PreparedStatement queryStatement = connection.prepareStatement(query);
            queryStatement.setString(1, name);
            queryStatement.setString(2, password);
            ResultSet resultSet = queryStatement.executeQuery(query);
            //get data to build User object from resultSet
            UserInfo foundUserInfo = new UserInfo(resultSet.getString("Username"), resultSet.getString("Password"));
            User foundUser = new User(foundUserInfo);
            return foundUser;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean clear() throws NeedTransactionException {
        String query = "DELETE * FROM User";
        try{
            PreparedStatement queryStatement = connection.prepareStatement(query);
            queryStatement.execute();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
