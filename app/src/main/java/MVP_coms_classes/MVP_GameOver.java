package MVP_coms_classes;

import java.util.List;

import modeling.Player;

/**
 * Created by ACL1 on 11/12/2017.
 */

public interface MVP_GameOver {
    interface GameOverActivity{
        void SetUpWinScreen (List<Player> players);
        void SetLongestRoute(List<Player> players);
    }
    interface GameOverPresenter{
        void ExitGame ();
    }
}
