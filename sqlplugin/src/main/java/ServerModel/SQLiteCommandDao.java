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

import commandData.*;

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
                "GameID text not null, \n" +
                "CommandInfo blob not null \n" +
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
        System.out.println("test");
        String query = "SELECT CommandInfo FROM Command WHERE GameID=?";
        try{
            connection = DriverManager.getConnection(connectionString);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, gameID);
            ResultSet resultSet = statement.executeQuery();
            List<Command> commands = new ArrayList<>();
            while(resultSet.next()){
                String jsonInfo = resultSet.getString("CommandInfo");
                Command checkType = myGson.fromJson(jsonInfo, Command.class);
                switch (checkType.getType()){
                    case "startGame":
                        StartGameCommandData startGameCommandData = myGson.fromJson(jsonInfo, StartGameCommandData.class);
                        commands.add(startGameCommandData);
                        break;
                    case "drawTrainCard":
                        DrawTrainCardCommandData drawTrainCardCommandData = myGson.fromJson(jsonInfo, DrawTrainCardCommandData.class);
                        commands.add(drawTrainCardCommandData);
                        break;
                    case "drawDestinationCards":
                        DrawDestinationCardCommandData drawDestinationCardCommandData = myGson.fromJson(jsonInfo, DrawDestinationCardCommandData.class);
                        commands.add(drawDestinationCardCommandData);
                        break;
                    case "claimInitialDestinationCards":
                        ClaimInitialDestinationCardCommandData claimInitialDestinationCardCommandData = myGson.fromJson(jsonInfo, ClaimInitialDestinationCardCommandData.class);
                        commands.add(claimInitialDestinationCardCommandData);
                        break;
                    case "claimDestinationCards":
                        ClaimDestinationCardCommandData claimDestinationCardCommandData = myGson.fromJson(jsonInfo, ClaimDestinationCardCommandData.class);
                        commands.add(claimDestinationCardCommandData);
                        break;
                    case "claimRoute":
                        ClaimRouteCommandData claimRouteCommandData = myGson.fromJson(jsonInfo, ClaimRouteCommandData.class);
                        commands.add(claimRouteCommandData);
                        break;
                    case "endTurn":
                        EndTurnCommandData endTurnCommandData = myGson.fromJson(jsonInfo, EndTurnCommandData.class);
                        commands.add(endTurnCommandData);
                        break;
                    case "addChat":
                        ChatCommandData chatCommandData = myGson.fromJson(jsonInfo, ChatCommandData.class);
                        commands.add(chatCommandData);
                        break;
                    case "EndGame":
                        break;
                }
            }
            connection.close();
            return commands;
        }catch (SQLException e){
            //e.printStackTrace();

            System.out.println("FAILED TO SAVE COMMAND SQL");
            return null;
        }
        catch (Exception e){
            System.out.println("WHATS GOING ON?");
            //e.printStackTrace();
            return  null;
        }
    }

    @Override
    public boolean addCommandsToGame(String gameID, Command command) {
        String query = "INSERT INTO COMMAND VALUES(?, ?)";
        try{
            String specificCommandJson = "";
            switch (command.getType()){
                case "startGame":
                    specificCommandJson = myGson.toJson(command, StartGameCommandData.class);
                    break;
                case "drawTrainCard":
                    specificCommandJson = myGson.toJson(command, DrawTrainCardCommandData.class);
                    break;
                case "drawDestinationCards":
                    specificCommandJson = myGson.toJson(command, DrawDestinationCardCommandData.class);
                    break;
                case "claimInitialDestinationCards":
                    specificCommandJson = myGson.toJson(command, ClaimInitialDestinationCardCommandData.class);
                    break;
                case "claimDestinationCards":
                    specificCommandJson = myGson.toJson(command, ClaimDestinationCardCommandData.class);
                    break;
                case "claimRoute":
                    specificCommandJson = myGson.toJson(command, ClaimRouteCommandData.class);
                    break;
                case "endTurn":
                    specificCommandJson = myGson.toJson(command, EndTurnCommandData.class);
                    break;
                case "addChat":
                    specificCommandJson = myGson.toJson(command, ChatCommandData.class);
                    break;
                case "EndGame":
                    break;
                default:
                    break;
            }
            connection = DriverManager.getConnection(connectionString);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, gameID);
            statement.setString(2, specificCommandJson);
            statement.execute();
            connection.close();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }catch (Exception e){
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
