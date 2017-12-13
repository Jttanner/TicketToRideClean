import java.util.List;

/**
 * Created by tyler on 12/5/2017.
 */

interface ICommandDao {
    List<Command> getCommandList(String gameID);

    boolean addCommandsToGame(String gameID, Command command);

    boolean removeCommands(String gameID);

    boolean clear();
}
