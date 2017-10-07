package presenters;

import MVP_coms_classes.CommandSuccessChecker;
import MVP_coms_classes.MVP_GameList;
import commandData.Command;
import modeling.Game;
import poller.Poller;
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

    public static void initiazlizePoller(){
        String myIpUrl = "http://192.168.0.7:8080/user/command";
        Poller poller = new Poller(myIpUrl);
        poller.updateGameList();
    }

    @Override
    public void JoinGame(int index) {

    }

    @Override
    public void checkCommandSuccess(CommandResult r) {
        //TODO check the success of any given command and do something with it
    }
}
