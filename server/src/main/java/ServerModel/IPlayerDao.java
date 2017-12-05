package ServerModel;

import java.util.List;

import modeling.Player;

/**
 * Created by tyler on 12/5/2017.
 */

interface IPlayerDao {

    boolean updatePlayer(Player player) throws NeedTransactionException;;

    Player getPlayer(String playerName) throws NeedTransactionException;;

    boolean savePlayers(List<Player> players) throws NeedTransactionException;;

    boolean clear() throws NeedTransactionException;;
}
