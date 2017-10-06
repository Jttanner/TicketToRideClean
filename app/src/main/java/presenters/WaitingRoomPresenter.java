package presenters;

import java.util.Observable;
import java.util.Observer;

import MVP_coms_classes.CommandSuccessChecker;
import MVP_coms_classes.MVP_WaitingRoom;
import result.CommandResult;

/**
 * Created by korea on 10/6/2017.
 */

public class WaitingRoomPresenter implements MVP_WaitingRoom.RequiredPresenterOps, Observer, CommandSuccessChecker {
    @Override
    public void checkCommandSuccess(CommandResult r) {

    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
