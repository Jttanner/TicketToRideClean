package MVP_coms_classes;

import java.util.List;

import modeling.Game;

/**
 * Created by LabUser on 10/2/2017.
 */

public interface MVP_GameList {

    interface GameListActivityInterface{ //Presenter is pushing things to the view
        void UpdateList(List<Game> list);
        void JoinGameResult(Game game);
    }

    interface GameListPresenterInterface { //View is pushing things to the presenter
        void CreateGame(Game game);
        void JoinGame(Game game);

        void deleteObserver();
    }
}
