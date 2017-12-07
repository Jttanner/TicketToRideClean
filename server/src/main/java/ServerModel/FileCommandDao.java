package ServerModel;

import java.util.List;

import commandData.Command;

/**
 * Created by korea on 12/7/2017.
 */

public class FileCommandDao implements ICommandDao {
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
        return false;
    }
}
