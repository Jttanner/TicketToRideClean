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
    public List<Command> getCommandList(String gameID) {
        String query = "SELECT CommandInfo FROM Command WHERE GameID=?";
        try{
            connection = DriverManager.getConnection(connectionString);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, gameID);
            ResultSet resultSet = statement.executeQuery();
            List<Command> commands = new ArrayList<>();
            while(resultSet.next()){
                String jsonInfo = resultSet.getString("CommandInfo");
                Command  checkType = myGson.fromJson(jsonInfo, Command.class);
                switch (checkType.getType()){

                }
                commands.add(myGson.fromJson(resultSet.getString("CommandInfo"), Command.class));
            }
            connection.close();
            return commands;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        catch (Exception e){
            return  null;
        }
    }

    @Override
    public boolean addCommandsToGame(String gameID, Command command) {
        String query = "INSERT INTO COMMAND VALUES(?, ?)";
        try{
            connection = DriverManager.getConnection(connectionString);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(0, gameID);
            statement.setString(1, myGson.toJson(command));
            statement.execute();
            connection.close();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeCommands(String gameID) {
        String query = "DELETE FROM Command WHERE GameID=?";
        try{
            connection = DriverManager.getConnection(connectionString);
            Statement statement = connection.createStatement();
            statement.execute(query);
            connection.close();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean clear() {
        String query = "DELETE FROM Command";
        try{
            connection = DriverManager.getConnection(connectionString);
            Statement statement = connection.createStatement();
            statement.execute(query);
            connection.close();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
