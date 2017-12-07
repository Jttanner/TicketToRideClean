package ServerModel;

import com.google.gson.Gson;

import java.sql.Connection;
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

    Gson myGson = new Gson();

    public SQLiteGameDao(Connection connection){
        this.connection = connection;
        createTableIfNotExists();
    }

    private void createTableIfNotExists(){
        String query = "create table if not exists Game\n" +
                "( \n" +
                "GameID text unique not null, \n" +
                "GameInfo blob not null\n" +
                ");";
        try{
            Statement statement = connection.createStatement();
            statement.execute(query);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean updateGameState(Game game) throws NeedTransactionException {
        String query = "UPDATE Game set GameInfo=? WHERE GameID=?";
        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, myGson.toJson(game));
            statement.setString(2, game.getGameID());
            return statement.execute();
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Game getGameState(String gameID) throws NeedTransactionException {
        String query = "SELECT Gameinfo FROM Game WHERE GameID=?";
        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, gameID);
            ResultSet resultSet = statement.executeQuery();
            return myGson.fromJson(resultSet.getString("GameInfo"), Game.class);
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean removeGame(String gameID) throws NeedTransactionException {
        String query = "DELETE FROM GAME WHERE GameID=?";
        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, gameID);
            return statement.execute();
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean clear() throws NeedTransactionException {
        String query = "DELETE FROM GAME";
        try{
            Statement statement = connection.createStatement();
            return statement.execute(query);
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
