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

import commandData.Command;
import modeling.Game;

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
    public List<Command> getCommandList(String gameID) throws NeedTransactionException {
        List<Command> listOfCommands = new ArrayList<Command>();
        String commandFile;
        String line;
        Command command = null;
//        try {
//            fileName = new String(Files.readAllBytes(Paths.get("Game/" + gameID + ".txt")));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        commandFile = ("Command/" + gameID + ".txt");
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(commandFile);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null){
                //line = bufferedReader.readLine();
                command = gson.fromJson(line, Command.class);
                listOfCommands.add(command);
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
    public boolean addCommandsToGame(String gameID, List<Command> command) throws NeedTransactionException {
        FileWriter fileWriter;
        String gameToString;
        File commandFile;
        boolean result = false;
        //Create new txt file in Game
        commandFile = new File("Command/" + gameID + ".txt");
        try {
            if (commandFile.createNewFile()){
                System.out.println("File" + gameID + "is created!");
            }else{
                System.out.println("File" + gameID +  "already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Update file in Game Directory
        try {
            for(Command c: command) {
                gameToString = gson.toJson(c);
                fileWriter = new FileWriter(commandFile, true);
                fileWriter.append(gameToString);
                fileWriter.close();
            }

            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean removeCommands(String gameID) throws NeedTransactionException {
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
    public boolean clear() throws NeedTransactionException {
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
