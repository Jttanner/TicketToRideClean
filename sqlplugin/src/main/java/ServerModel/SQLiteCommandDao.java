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
        String query = "create table if not exists ServerModel.Command \n" +
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
        String query = "SELECT CommandInfo FROM ServerModel.Command WHERE GameID=?";
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
        String query = "DELETE FROM ServerModel.Command WHERE GameID=?";
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
        String query = "DELETE FROM ServerModel.Command";
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
