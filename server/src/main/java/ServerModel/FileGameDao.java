package ServerModel;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

import modeling.Game;

/**
 * Created by korea on 12/7/2017.
 */

public class FileGameDao implements IGameDao {

    private FileWriter fileWriter;
    private File directory;
    boolean result; //Keep
    String stringtoJson;
    Gson gson; //Keep
    String fileName;
    String line;

    public FileGameDao() {
        File dir = new File("Game");
        boolean successful = dir.mkdir();
        if (successful) {
            System.out.println("Game Directory is created!");
        }
        else {
            System.out.println("Game Directory already exists");
        }
    }
    @Override
    public boolean updateGameState(Game game) throws NeedTransactionException {
        //Create new txt file in Game
        directory = new File("User/" + game.getGameID() + ".txt");
        try {
            if (directory.createNewFile()){
                System.out.println("File" + game.getGameID() + "is created!");
            }else{
                System.out.println("File" + game.getGameID() +  "already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Update file in Game Directory
        try {
            stringtoJson = gson.toJson(game);
            fileWriter = new FileWriter(directory);
            fileWriter.write(stringtoJson);
            fileWriter.close();

            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Game getGameState(String gameID) throws NeedTransactionException {
        try {
            fileName = new String(Files.readAllBytes(Paths.get("Game/" + gameID + ".txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            line = bufferedReader.readLine();

            // Always close files.
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
        Game game = gson.fromJson(line, Game.class);
        return game;
    }

    @Override
    public boolean removeGame(String gameID) throws NeedTransactionException {
        try {
            fileName = new String(Files.readAllBytes(Paths.get("Game/" + gameID + ".txt")));
            directory = new File(fileName);
            result = directory.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean clear() throws NeedTransactionException {
        try {
            fileName = new String(Files.readAllBytes(Paths.get("Game")));
            directory = new File(fileName);
            result = directory.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /*
The readString method shows how to write a String to an OutputStream.
*/
    protected String readString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStreamReader sr = new InputStreamReader(is);
        char[] buf = new char[1024];
        int len;
        while ((len = sr.read(buf)) > 0) {
            sb.append(buf, 0, len);
        }
        return sb.toString();
    }
}
