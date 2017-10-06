package presenters;

import MVP_coms_classes.CommandSuccessChecker;
import MVP_coms_classes.MVP_GameList;
import commandData.Command;
import modeling.Game;
import result.CommandResult;

/**
 * Created by LabUser on 10/2/2017.
 */

public class GameListPresenter implements MVP_GameList.GameListPresenterInterface, CommandSuccessChecker {
    @Override
    public void CreateGame(Game game) {
        Command command = new Command();
        command.setType("creategame");
        command.setData(game);


    }

    @Override
    public void JoinGame(int index) {

    }

    @Override
    public void checkCommandSuccess(CommandResult r) {
        //TODO check the success of any given command and do something with it
    }
}
