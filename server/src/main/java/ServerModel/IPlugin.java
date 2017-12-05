package ServerModel;

import java.util.List;

import commandData.Command;
import modeling.Game;
import modeling.User;

/**
 * Created by tyler on 12/5/2017.
 */

public interface IPlugin {

    String getPluginName();
    User getUser(String playerName);
    Game getGame(String gameID);
    List<Command> getGameCommands(String gameID);
    boolean saveUser();
    boolean saveGame();
    boolean saveGameCommands();
    boolean clear();
    String getPManager();
}
