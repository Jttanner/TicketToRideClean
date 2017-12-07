package ServerModel;

import java.sql.Connection;

import modeling.Game;

/**
 * Created by jontt on 12/7/2017.
 */

public class SQLiteGameDao implements IGameDao {

    Connection connection;

    SQLiteGameDao(Connection connection){
        this.connection = connection;
    }

    @Override
    public boolean updateGameState(Game game) throws NeedTransactionException {
        return false;
    }

    @Override
    public Game getGameState(String gameID) throws NeedTransactionException {
        return null;
    }

    @Override
    public boolean removeGame(String gameID) throws NeedTransactionException {
        return false;
    }

    @Override
    public boolean clear() throws NeedTransactionException {
        return false;
    }
}
