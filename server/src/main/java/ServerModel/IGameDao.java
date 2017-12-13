package ServerModel;

import java.util.List;

import modeling.Game;

/**
 * Created by tyler on 12/5/2017.
 */

interface IGameDao {
    /**Updates the Row associated with the given GameID
     * @param game The game to be saved*/
    boolean updateGameState(Game game);
    /**Gets the game object associated with the given game id
     * @param gameID The gameID*/
    Game getGameState(String gameID);
    /**Removes the game from the game table associated with the given gameID
     * @param gameID Gameid*/
    boolean removeGame(String gameID);

    boolean clear() ;

    List<Game> getAllGames();
}
