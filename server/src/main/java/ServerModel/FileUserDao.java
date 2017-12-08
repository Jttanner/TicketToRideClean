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
        return false;
    }
}
