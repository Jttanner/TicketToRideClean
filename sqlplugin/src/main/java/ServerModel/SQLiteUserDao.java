package ServerModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modeling.*;

/**
 * Created by jontt on 12/7/2017.
 */

public class SQLiteUserDao implements IUserDao {

    String connectionString;

    Connection connection;

    SQLiteUserDao(String connectionString){
        try{
            Class.forName("org.sqlite.JDBC");
        }catch (Exception e){
            e.printStackTrace();
        }
        this.connectionString = connectionString;
        createTableIfNotExists();
    }

    private void createTableIfNotExists(){
        String query = "create table if not exists User \n" +
                "( \n" +
                "Username text unique not null, \n" +
                "Password text not null \n" +
                ");";
        try{
            connection = DriverManager.getConnection(connectionString);
            Statement statement = connection.createStatement();
            statement.execute(query);
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean registerUser(String userName, String password) {
        String query = "INSERT INTO User(Username, Password) VALUES(?, ?)";
        try{

            if (verifyUser(userName, password) != null){
                //connection.close();
                return false;
            } else{
                connection = DriverManager.getConnection(connectionString);
                PreparedStatement queryStatement = connection.prepareStatement(query);
                queryStatement.setString(1, userName);
                queryStatement.setString(2, password);
                queryStatement.execute();
                connection.close();
                return true;
            }
        }catch (Exception e){
            System.out.println("USER ALREADY EXISTS");
            return false;
        }
    }

    @Override
    public User verifyUser(String name, String password) {
        String query = "SELECT * FROM User WHERE Username=? AND Password=?;";
        try{
            connection = DriverManager.getConnection(connectionString);
            PreparedStatement queryStatement = connection.prepareStatement(query);
            queryStatement.setString(1, name);
            queryStatement.setString(2, password);
            ResultSet resultSet = queryStatement.executeQuery();
            //get data to build modeling.User object from resultSet
            UserInfo foundUserInfo = new UserInfo(resultSet.getString("Username"), resultSet.getString("Password"));
            User foundUser = new User(foundUserInfo);
            connection.close();
            return foundUser;
        }catch (SQLException e){
            //e.printStackTrace();
            System.out.println("USER NOT FOUND");
            return null;
        }
    }

    @Override
    public boolean clear() {
        String query = "DELETE FROM User";
        try{
            connection = DriverManager.getConnection(connectionString);
            PreparedStatement queryStatement = connection.prepareStatement(query);
            queryStatement.execute();
            connection.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<
            User> getAllUsers() {
        String query = "SELECT * FROM User";
        try{
            List<User> users = new ArrayList<>();
            connection = DriverManager.getConnection(connectionString);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                UserInfo userInfo = new UserInfo(resultSet.getString("Username"), resultSet.getString("Password"));
                users.add(new User(userInfo));
            }

            return users;
        }catch (SQLException e){
            System.out.println("no users");
            return null;
        }
    }
}
