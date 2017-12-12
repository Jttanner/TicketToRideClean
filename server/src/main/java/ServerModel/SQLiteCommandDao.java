package ServerModel;

import com.google.gson.Gson;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import commandData.Command;
import modeling.Game;
import result.Result;

/**
 * Created by jontt on 12/7/2017.
 */

public class SQLiteCommandDao implements ICommandDao {

    String connectionString;

    Connection connection;

    Gson myGson = new Gson();

    SQLiteCommandDao(String connectionString){
        this.connectionString = connectionString;
        createTableIfNotExists();
    }

    private void createTableIfNotExists(){
        String query = "create table if not exists Command \n" +
                "( \n" +
                "GameID text unique not null, \n" +
                "CommandInfo text not null \n" +
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
    public List<Command> getCommandList(String gameID) throws NeedTransactionException {
        String query = "SELECT CommandInfo FROM Command WHERE GameID=?";
        try{
            connection = DriverManager.getConnection(connectionString);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, gameID);
            ResultSet resultSet = statement.executeQuery();
            List<Command> commands = new ArrayList<>();
            while(resultSet.next()){
                //TODO: Does JSON need the concrete class? Otherwise do I make a switch statement? or some kind of inheritance function for command?
                commands.add(myGson.fromJson(resultSet.getString("CommandInfo"), Command.class));
            }
            connection.close();
            return commands;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean addCommandsToGame(String gameID, List<Command> commands) throws NeedTransactionException {
        String query = "INSERT INTO COMMAND VALUES";
        try{
            connection = DriverManager.getConnection(connectionString);
            for (int i = 0; i < commands.size(); ++i){
                query += "(?, ?)";
                if (i == commands.size()-1){
                    query += ";";
                } else{
                    query += ",";
                }
            }
            int j = 0;
            PreparedStatement statement = connection.prepareStatement(query);
            for (Command command : commands){
                statement.setString(j++, gameID);
                statement.setString(j++, myGson.toJson(command));
            }
            boolean success =statement.execute();
            connection.close();
            return  success;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean clear() throws NeedTransactionException {
        String query = "DELETE FROM Command";
        try{
            connection = DriverManager.getConnection(connectionString);
            Statement statement = connection.createStatement();
            boolean success = statement.execute(query);
            connection.close();
            return success;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
