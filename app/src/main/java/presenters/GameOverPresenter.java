package presenters;

import java.util.Observable;
import java.util.Observer;

import MVP_coms_classes.MVP_GameOver;

/**
 * Created by ACL1 on 11/13/2017.
 */

public class GameOverPresenter implements MVP_GameOver.GameOverPresenter,Observer {



    @Override
    public void ExitGame() {
        //start another activity
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
