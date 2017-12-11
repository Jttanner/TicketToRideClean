package ServerModel;

import java.io.File;
import java.util.List;

import commandData.Command;

/**
 * Created by korea on 12/7/2017.
 */

public class FileCommandDao implements ICommandDao {

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

        return null;
    }

    @Override
    public boolean addCommandsToGame(String gameID, List<Command> command) throws NeedTransactionException {

        return false;
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
