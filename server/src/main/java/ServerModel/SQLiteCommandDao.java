package ServerModel;

import java.sql.Connection;
import java.util.List;

import commandData.Command;

/**
 * Created by jontt on 12/7/2017.
 */

public class SQLiteCommandDao implements ICommandDao {

    Connection connection;

    SQLiteCommandDao(Connection connection){
        this.connection = connection;
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
        return false;
    }
}
