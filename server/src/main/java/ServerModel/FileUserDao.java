package ServerModel;

import java.io.File;

import modeling.User;

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
        return false;
    }

    @Override
    public User verifyUser(String name, String password) throws NeedTransactionException {
        return null;
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
