package presenters;

import java.lang.ref.WeakReference;
import java.util.Observable;
import java.util.Observer;

import MVP_coms_classes.CommandSuccessChecker;
import MVP_coms_classes.MVP_Login;
import MVP_coms_classes.MVP_WaitingRoom;
import clientModel.CModel;
import modeling.Game;
import result.CommandResult;

/**
 * Created by korea on 10/6/2017.
 */

public class WaitingRoomPresenter implements MVP_WaitingRoom.RequiredPresenterOps, Observer, CommandSuccessChecker {
    private WeakReference<MVP_WaitingRoom.RequiredViewOps> myView;

    public WaitingRoomPresenter(MVP_WaitingRoom.RequiredViewOps view) {
        myView = new WeakReference<>(view);
        CModel.getInstance().addObserver(this);
    }
    @Override
    public void checkCommandSuccess(CommandResult r) {

    }

    @Override
    public void update(Observable o, Object arg) {
        myView.get().updateWaitingRoom((Game)arg);
    }
}
