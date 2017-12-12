package ServerModel;

import java.util.List;

import commandData.Command;

/**
 * Created by tyler on 12/5/2017.
 */

interface ICommandDao {
    List<Command> getCommandList(String gameID) throws NeedTransactionException;;

    boolean addCommandsToGame(String gameID, Command command) throws NeedTransactionException;;

    boolean removeCommands(String gameID) throws NeedTransactionException;

    boolean clear() throws NeedTransactionException;
}
