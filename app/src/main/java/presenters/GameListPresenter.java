package presenters;

import java.util.Observable;
import java.util.Observer;

import MVP_coms_classes.MVP_GameList;
import commandData.Command;
import modeling.Game;

/**
 * Created by LabUser on 10/2/2017.
 */

public class GameListPresenter implements MVP_GameList.GameListPresenterInterface {
    @Override
    public void CreateGame(Game game) {
        Command command = new Command();
        command.setType("creategame");
        command.setData(game);


    }

    @Override
    public void JoinGame(int index) {

    }

}
