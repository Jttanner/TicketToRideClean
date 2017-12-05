package ServerModel;

import java.util.List;

import modeling.Player;

/**
 * Created by tyler on 12/5/2017.
 */

interface IPlayerDao {

    void updatePlayer(Player player);

    Player getPlayer(String playerName);

    boolean savePlayers(List<Player> players);

    boolean clear();
}
