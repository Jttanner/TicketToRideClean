package presenters;

import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.Observable;
import java.util.Observer;

import MVP_coms_classes.CommandSuccessChecker;
import MVP_coms_classes.MVP_WaitingRoom;
import clientModel.CModel;
import commandData.StartGameCommandData;
import modeling.Game;
import result.CommandResult;
import result.RegisterResult;

import static android.content.ContentValues.TAG;

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
        if(arg instanceof Game){
            myView.get().updateWaitingRoom((Game)arg);
        }
    }

    public void startGame(StartGameCommandData startGameCommandData){
        try {
            //call the async task
            HttpTask httpTask = new HttpTask(this);
            httpTask.start(":8080/user/command", startGameCommandData);
        } catch (Exception e) {
            Log.d(TAG, "register method messed up: " + e.toString());
            e.printStackTrace();
        }
    }
}
