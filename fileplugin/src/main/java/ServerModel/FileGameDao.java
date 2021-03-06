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
import java.util.ArrayList;
import java.util.List;

import modeling.Game;

//import java.nio.file.Files;
//import java.nio.file.Paths;

/**
 * Created by korea on 12/7/2017.
 */

public class FileGameDao implements IGameDao {

    private Gson gson = new Gson(); //Keep

    /*
    * When the ServerModel.FileGameDao is created the Folder holding the game texts files is created
    *
    */
    public FileGameDao() {
        File directory = new File("Game");
        boolean successful = directory.mkdir();
        if (successful) {
            System.out.println("Game Directory is created!");
        }
        else {
            System.out.println("Game Directory already exists");
        }
    }

    /*
    * Creates the game file, if it doesn't exist
    * Updates the game file with a new Json string
    */
    @Override
    public boolean updateGameState(Game game)  {
        FileWriter fileWriter;
        String gameToString;
        File gameFile;
        boolean result = false;
        //Create new txt file in Game
        gameFile = new File("Game/" + game.getGameID() + ".txt");
        try {
            if (gameFile.createNewFile()){
                System.out.println("UpdateGameState: File " + game.getGameID() + "is created!");
            }else{
                System.out.println("UpdateGameState: File " + game.getGameID() +  "already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Update file in Game Directory
        try {
            gameToString = gson.toJson(game);
            fileWriter = new FileWriter(gameFile);
            fileWriter.write(gameToString);
            fileWriter.close();

            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    /*
    * Gets the Json string from the game file and then converts it back to a game object
    *
    */
    @Override
    public Game getGameState(String gameID) {
        String gameFile;
        String line;
        Game game = null;
//        try {
//            fileName = new String(Files.readAllBytes(Paths.get("Game/" + gameID + ".txt")));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        gameFile = ("Game/" + gameID + ".txt");
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(gameFile);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            line = bufferedReader.readLine();

            // Always close files.
            bufferedReader.close();
            game = gson.fromJson(line, Game.class);
        }
        catch(FileNotFoundException ex) {
            System.out.println("GetGameState: Unable to open file '" + gameFile + "'");
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + gameFile + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
        return game;
    }
    /*
    * Delets the game file from the folder
    *
    */
    @Override
    public boolean removeGame(String gameID) {
        String gameFile;
        File directory;
        boolean result = false;
        //Removes the Game
        //            fileName = new String(Files.readAllBytes(Paths.get("Game/" + gameID + ".txt")));
        gameFile = "Game/" + gameID + ".txt";
        directory = new File(gameFile);
        result = directory.delete();
        if(result) {
            System.out.println("Successfully deleted game: " + gameID);
        }
        else {
            System.out.println(gameID + " does not exist or not deleted :o");
        }
        return result;
    }
    /*
    * Clears the game folder
    *
    */
    @Override
    public boolean clear() {
        String gameFile;
        File directory;
        boolean result = false;
        //fileName = new String(Files.readAllBytes(Paths.get("Game")));
        gameFile = "Game";
        directory = new File(gameFile);
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

    @Override
    public List<Game> getAllGames() {
        String gameFile;
        File directory;
        boolean result = false;
        gameFile = "Game";
        directory = new File(gameFile);
        String[]entries = directory.list();
        String line;
        List allGames = new ArrayList<>();
        if(entries != null) {
            //System.out.println("Files inside User ");
            for(String s: entries) {
                //System.out.println(s);
                //if(s.equals((name + ".txt"))) {
                try {
                    //s = s.substring(0, s.length() - 4);
                    File file = new File("Game/" + s);
                    // FileReader reads text files in the default encoding.
                    FileReader fileReader = new FileReader(file);

                    // Always wrap FileReader in BufferedReader.
                    BufferedReader bufferedReader = new BufferedReader(fileReader);

                    //Gets the game Json string
                    line = bufferedReader.readLine();
                    // Always close files.
                    bufferedReader.close();

                    Game game = gson.fromJson(line, Game.class);
                    allGames.add(game);
                }
                catch(FileNotFoundException ex) {
                    System.out.println("GetAllGames: Unable to open file " + s);
                }
                catch(IOException ex) {
                    System.out.println("Error reading file '" + "'");
                    // Or we could just do this:
                    // ex.printStackTrace();
                }
                //}
            }
        }


        return allGames;
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
