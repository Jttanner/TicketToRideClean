package ServerModel;

import com.google.gson.Gson;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modeling.Game;

/**
 * Created by jontt on 12/7/2017.
 */

public class SQLiteGameDao implements IGameDao {

    Connection connection;

    String connectionString;

    Gson myGson = new Gson();

    public SQLiteGameDao(String connectionString){
        this.connectionString = connectionString;
        createTableIfNotExists();
    }

    private void createTableIfNotExists(){
        String query = "create table if not exists Game\n" +
                "( \n" +
                "GameID text unique not null, \n" +
                "GameInfo blob not null\n" +
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
    public boolean updateGameState(Game game) {
        String query1 = "DELETE FROM Game WHERE GameID=?;";
        String query2 = "INSERT INTO Game VALUES(?, ?)";
        try{
            connection = DriverManager.getConnection(connectionString);
            PreparedStatement statement = connection.prepareStatement(query1);
            statement.setString(1, game.getGameID());
            statement.execute();
            connection.close();
            connection = DriverManager.getConnection(connectionString);
            statement = connection.prepareStatement(query2);
            statement.setString(1, myGson.toJson(game));
            statement.setString(2, game.getGameID());
            statement.execute();
            connection.close();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Game getGameState(String gameID)  {
        String query = "SELECT Gameinfo FROM Game WHERE GameID=?";
        try{
            connection = DriverManager.getConnection(connectionString);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, gameID);
            ResultSet resultSet = statement.executeQuery();
            connection.close();
            return myGson.fromJson(resultSet.getString("GameInfo"), Game.class);
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean removeGame(String gameID)  {
        String query = "DELETE FROM GAME WHERE GameID=?";
        try{
            connection = DriverManager.getConnection(connectionString);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, gameID);
            statement.execute();
            connection.close();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean clear() {
        String query = "DELETE FROM GAME";
        try{
            connection = DriverManager.getConnection(connectionString);
            Statement statement = connection.createStatement();
            statement.execute(query);
            connection.close();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
