package MVP_coms_classes;

import java.util.List;

import modeling.Game;

/**
 * Created by LabUser on 10/2/2017.
 */

public interface MVP_GameList {

    interface GameListActivityInterface{
        void UpdateList(List<Game> list);
        void JoinGameResult();
        void ToggleButton(boolean startGame, boolean joinGame);
    }

    interface GameListPresenterInterface {
        void CreateGame(Game game);
        void JoinGame(int index);
    }
}
