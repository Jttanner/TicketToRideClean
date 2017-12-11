package ServerModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import modeling.Game;
import modeling.User;
import modeling.UserInfo;

/**
 * Created by korea on 12/7/2017.
 */

public class FileUserDao implements IUserDao {

    public FileUserDao () {
        File dir = new File("User");
        boolean successful = dir.mkdir();
        if (successful) {
            System.out.println("User Directory is created!");
        }
        else {
            System.out.println("User Directory already exists");
        }
    }
    @Override
    public boolean registerUser(String userName, String password) throws NeedTransactionException {
        FileWriter fileWriter;
        File gameFile;
        boolean result = false;
        //Create new txt file in Game
        gameFile = new File("User/" + userName + ".txt");
        try {
            if (gameFile.createNewFile()){
                System.out.println("File" + userName + "is created!");
            }else{
                System.out.println("File" + userName +  "already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Update file in Game Directory
        try {
            fileWriter = new FileWriter(userName);
            fileWriter.write(password);
            fileWriter.close();

            result = true;
        } catch (IOException e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    //Will verifyUser return a user or a boolean?
    @Override
    public User verifyUser(String name, String password) throws NeedTransactionException {
        String userFile;
        File directory;
        boolean result = false;
        userFile = "User";
        directory = new File(userFile);
        String[]entries = directory.list();
        String line;
        if(entries != null) {
            for(String s: entries) {
                if(s.equals(name)) {
                    try {
                        // FileReader reads text files in the default encoding.
                        FileReader fileReader = new FileReader(name);

                        // Always wrap FileReader in BufferedReader.
                        BufferedReader bufferedReader = new BufferedReader(fileReader);

                        line = bufferedReader.readLine();
                        if(line.equals(password)) {
                            result = true;
                        }
                        // Always close files.
                        bufferedReader.close();
                        //game = gson.fromJson(line, Game.class);
                    }
                    catch(FileNotFoundException ex) {
                        System.out.println("Unable to open file '" + name + "'");
                    }
                    catch(IOException ex) {
                        System.out.println("Error reading file '" + name + "'");
                        // Or we could just do this:
                        // ex.printStackTrace();
                    }
                }
            }
        }

        //return result;
        UserInfo info = new UserInfo("name", "pass");
        User temp = new User(info);
        return temp;
    }


    @Override
    public boolean clear() throws NeedTransactionException {
        String userFile;
        File directory;
        boolean result = false;
        //fileName = new String(Files.readAllBytes(Paths.get("Game")));
        userFile = "User";
        directory = new File(userFile);
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
