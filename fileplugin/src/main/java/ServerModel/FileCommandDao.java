package ServerModel;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import commandData.ChatCommandData;
import commandData.ClaimDestinationCardCommandData;
import commandData.ClaimInitialDestinationCardCommandData;
import commandData.ClaimRouteCommandData;
import commandData.Command;
import commandData.DrawDestinationCardCommandData;
import commandData.DrawTrainCardCommandData;
import commandData.EndTurnCommandData;
import commandData.StartGameCommandData;


/**
 * Created by korea on 12/7/2017.
 */

public class FileCommandDao implements ICommandDao {

    private Gson gson; //Keep

    public FileCommandDao () {

        File dir = new File("Command");
        boolean successful = dir.mkdir();
        if (successful) {
            System.out.println("Command Directory is created!");
        }
        else {
            System.out.println("Command Directory already exists");
        }
    }
    @Override
    public List<Command> getCommandList(String gameID)  {
        List<Command> listOfCommands = new ArrayList<>();
        String commandFile;
        String line;
        Command command = null;

        commandFile = ("Command/" + gameID + ".txt");
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(commandFile);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null){
                //line = bufferedReader.readLine();
                command = gson.fromJson(line, Command.class);
                switch (command.getType()) {
                    case "startGame":
                        StartGameCommandData startGameCommandData = gson.fromJson(line, StartGameCommandData.class);
                        listOfCommands.add(startGameCommandData);
                        break;
                    case "drawTrainCard":
                        DrawTrainCardCommandData drawTrainCardCommandData = gson.fromJson(line,DrawTrainCardCommandData.class);
                        listOfCommands.add(drawTrainCardCommandData);
                        break;
                    case "drawDestinationCards":
                        DrawDestinationCardCommandData drawDestinationCardCommandData = gson.fromJson(line, DrawDestinationCardCommandData.class);
                        listOfCommands.add(drawDestinationCardCommandData);
                        break;
                    case "claimInitialDestinationCards":
                        ClaimInitialDestinationCardCommandData claimInitialDestinationCardCommandData = gson.fromJson(line, ClaimInitialDestinationCardCommandData.class);
                        listOfCommands.add(claimInitialDestinationCardCommandData);
                        break;
                    case "claimDestinationCards":
                        ClaimDestinationCardCommandData claimDestinationCardCommandData = gson.fromJson(line, ClaimDestinationCardCommandData.class);
                        listOfCommands.add(claimDestinationCardCommandData);
                        break;
                    case "claimRoute":
                        ClaimRouteCommandData claimRouteCommandData = gson.fromJson(line, ClaimRouteCommandData.class);
                        listOfCommands.add(claimRouteCommandData);
                    case "endTurn":
                        EndTurnCommandData endTurnCommandData = gson.fromJson(line, EndTurnCommandData.class);
                        listOfCommands.add(endTurnCommandData);
                        break;
                    case "addChat":
                        ChatCommandData chatCommandData = gson.fromJson(line, ChatCommandData.class);
                        listOfCommands.add(chatCommandData);
                        break;
                    case "EndGame":
                        //return new EndGameCommand();
                    break;
                    default:
                        break;
                }
            }


            // Always close files.
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + gameID + "'");
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + gameID + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }

        return listOfCommands;
    }

    @Override
    public boolean addCommandsToGame(String gameID, Command command)  {
        FileWriter fileWriter;
        String commandToString;
        File commandFile;
        Gson myGson = new Gson();
        boolean result = false;
        String newLine = "\n";
        //Create new txt file in Game
        commandFile = new File("Command/" + gameID + ".txt");
        try {
            if (commandFile.createNewFile()){
                System.out.println("addCommandsToGame: File " + gameID + "is created!");
            }else{
                System.out.println("addCommandsToGame: File " + gameID +  "already exists.");
            }
        } catch (IOException e) {
            System.out.println("addCommandsToGame: File " + gameID +  "Something went wrong.");
            e.printStackTrace();
        }
        try {
            commandToString = "";
            switch (command.getType()){
                case "startGame":
                    commandToString = myGson.toJson(command, StartGameCommandData.class);
                    break;
                case "drawTrainCard":
                    commandToString = myGson.toJson(command, DrawTrainCardCommandData.class);
                    break;
                case "drawDestinationCards":
                    commandToString = myGson.toJson(command, DrawDestinationCardCommandData.class);
                    break;
                case "claimInitialDestinationCards":
                    commandToString = myGson.toJson(command, ClaimInitialDestinationCardCommandData.class);
                    break;
                case "claimDestinationCards":
                    commandToString = myGson.toJson(command, ClaimDestinationCardCommandData.class);
                    break;
                case "claimRoute":
                    commandToString = myGson.toJson(command, ClaimRouteCommandData.class);
                    break;
                case "endTurn":
                    commandToString = myGson.toJson(command, EndTurnCommandData.class);
                    break;
                case "addChat":
                    commandToString = myGson.toJson(command, ChatCommandData.class);
                    break;
                case "EndGame":
                    break;
                default:
                    break;
            }
            fileWriter = new FileWriter(commandFile, true);
            fileWriter.append(commandToString);
            fileWriter.append(newLine);
            fileWriter.close();
            result = true;
            System.out.println("Command successfully added to game " + gameID);
        } catch (IOException e) {
            System.out.println("Command not successfully added to game " + gameID);
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    @Override
    public boolean removeCommands(String gameID) {
        String gameFile;
        File directory;
        boolean result = false;
        //Removes the Game
        //            fileName = new String(Files.readAllBytes(Paths.get("Game/" + gameID + ".txt")));
        gameFile = "Command/" + gameID + ".txt";
        directory = new File(gameFile);
        result = directory.delete();

        return result;
    }

    @Override
    public boolean clear() {
        String commandFile;
        File directory;
        boolean result = false;
        //fileName = new String(Files.readAllBytes(Paths.get("Game")));
        commandFile = "Command";
        directory = new File(commandFile);
        String[]entries = directory.list();
        Boolean temp = true;
        if(entries != null) {
            for(String s: entries) {
                File currentFile = new File(directory.getPath(), s);
                result = currentFile.delete();
                if(!result) {
                    temp = false;
                }
            }
        }
        if(temp) {
            System.out.println("Clear successful");
        }
        else {
            System.out.println("Clear unsuccessful");
        }
        return result;
    }
}
