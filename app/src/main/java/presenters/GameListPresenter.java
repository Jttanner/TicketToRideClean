package presenters;

import java.lang.ref.WeakReference;

import MVP_coms_classes.CommandSuccessChecker;
import MVP_coms_classes.MVP_GameList;
import commandData.CreateGameCommandData;
import modeling.Game;
import result.CommandResult;

/**
 * Created by LabUser on 10/2/2017.
 */

public class GameListPresenter implements MVP_GameList.GameListPresenterInterface, CommandSuccessChecker {
    private WeakReference<MVP_GameList.GameListActivityInterface> myView;
    public GameListPresenter(){}

    public GameListPresenter(MVP_GameList.GameListActivityInterface view){
        myView = new WeakReference<>(view);
    }
    @Override
    public void CreateGame(Game game) {
        CreateGameCommandData command = new CreateGameCommandData();
        command.setType("createGame");
        command.setData(game);

        HttpTask httpTask = new HttpTask(this);
        httpTask.start(":8080/user/command",command);


    }


    @Override
    public void JoinGame(int index) {

    }

    @Override
    public void checkCommandSuccess(CommandResult r) {
        //TODO check the success of any given command and do something with it

    }


    public static void initiazlizePoller() {
    }
}
