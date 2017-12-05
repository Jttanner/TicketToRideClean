package ServerModel;

import java.util.List;

import commandData.Command;
import modeling.Game;
import modeling.User;

/**
 * Created by tyler on 12/5/2017.
 */

public interface IPlugin {

    public String getPluginName();
    public User getUser(String playerName);
    public Game getGame(String gameID);
    public List<Command> getGameCommands(String gameID);
    public boolean saveUser();
    public boolean saveGame();
    public boolean saveGameCommands();
    public boolean clear();
}
