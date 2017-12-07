package ServerModel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import modeling.DestinationCard;
import modeling.Player;
import modeling.Route;
import modeling.TrainCarList;

/**
 * Created by jontt on 12/7/2017.
 */

public class SQLitePlayerDao implements  IPlayerDao {

    Connection connection;

    Gson myGson = new Gson();

    public SQLitePlayerDao(Connection connection){
        this.connection = connection;
        createTableIfNotExists();
    }

    private void createTableIfNotExists(){
        String query = "create table if not exists Player \n" +
                "( \n" +
                "PlayerName text unique not null, \n" +
                "ResourceCard text not null, \n" +
                "Route text not null, \n" +
                "DestinationCard text not null, \n" +
                "Train text not null, \n" +
                "Point text not null, \n" +
                ");";
        try{
            Statement statement = connection.createStatement();
            statement.execute(query);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    @Override
    public boolean updatePlayer(Player player) throws NeedTransactionException {
        String query = "UPDATE tblPlayers SET PlayerName=? ResourceCard=? Route=? DestinationCard=? Train=? Point)=? +";
        try{
            PreparedStatement queryStatement = connection.prepareStatement(query);
            queryStatement.setString(1, player.getUserName());
            queryStatement.setString(2, myGson.toJson(player.getResourceCards()));
            queryStatement.setString(3, myGson.toJson(player.getRoutes()));
            queryStatement.setString(4, myGson.toJson(player.getDestinationCards()));
            queryStatement.setString(5, myGson.toJson(player.getTrainCarList()));
            queryStatement.setString(6, myGson.toJson(player.getPoints()));
            return queryStatement.execute();
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Player getPlayer(String playerName) throws NeedTransactionException {
        String query = "SELECT * FROM tblPlayers WHERE Playername=?";
        try{
            PreparedStatement queryStatement = connection.prepareStatement(query);
            queryStatement.setString(1, playerName);
            ResultSet resultSet = queryStatement.executeQuery();
            Player foundPlayer = new Player(resultSet.getString("PlayerName"), myGson.fromJson(resultSet.getString("ResourceCard"), HashMap.class),
                    Arrays.asList(myGson.fromJson(resultSet.getString("Route"), Route[].class)),
                    Arrays.asList(myGson.fromJson(resultSet.getString("DestinationCard"), DestinationCard[].class)),
                    myGson.fromJson(resultSet.getString("Train"), TrainCarList.class),
                    Integer.parseInt(myGson.fromJson(resultSet.getString("Point"), String.class))
            );
            return foundPlayer;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    //TODO: Is this supposed to update the database, add players to the database, or do both?
    @Override
    public boolean savePlayers(List<Player> players) throws NeedTransactionException {
        try{
            PreparedStatement statement = setupSaveQuery(players);
            return statement.execute();
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    private PreparedStatement setupSaveQuery(List<Player> players) throws SQLException{
        String query = "INSERT INTO tblPlayers(param, param, param) VALUES";
        PreparedStatement statement = connection.prepareStatement(query);
        for (int i = 0; i < players.size(); ++i){
            query += "(?, ?, ?)"; //... potentially more params
            if (i == players.size() - 1){
                query += ";";
            } else{
                query += ",";
            }
        }
        int paramIndex = 0;
        for (Player p: players){
            statement.setString(paramIndex++, p.getUserName());
            statement.setString(paramIndex++, p.getPlayerName());
            statement.setString(paramIndex++, p.getColor());
            //potentially more params
        }
        return statement;
    }


    @Override
    public boolean clear() throws NeedTransactionException {
        String query = "DELETE * FROM tblPlayers";
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
