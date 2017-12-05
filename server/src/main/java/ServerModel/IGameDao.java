package ServerModel;

import modeling.Game;

/**
 * Created by tyler on 12/5/2017.
 */

interface IGameDao {
    /**Updates the Row associated with the given GameID
     * @param gameID The gameId
     * @param game The game to be saved*/
    boolean updateGameState(Game game);
    /**Gets the game object associated with the given game id
     * @param gameID The gameID*/
    Game getGameState(String gameID);
    /**Removes the game from the game table associated with the given gameID
     * @param gameID Gameid*/
    void removeGame(String gameID);

    boolean clear();
}
