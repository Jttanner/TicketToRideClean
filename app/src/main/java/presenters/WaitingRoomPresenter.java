package presenters;

import android.util.Log;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.Observable;
import java.util.Observer;

import MVP_coms_classes.MVP_WaitingRoom;
import clientModel.CModel;
import commandData.StartGameCommandData;
import modeling.Game;
import modeling.GameList;
import servercomms.ServerProxy;

import static android.content.ContentValues.TAG;

/**
 * Created by korea on 10/6/2017.
 */

public class WaitingRoomPresenter implements MVP_WaitingRoom.RequiredPresenterOps, Observer {
    private WeakReference<MVP_WaitingRoom.RequiredViewOps> myView;

    public WaitingRoomPresenter(MVP_WaitingRoom.RequiredViewOps view) {
        myView = new WeakReference<>(view);
        CModel.getInstance().addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        //TODO if the currGames players change
        if(arg instanceof Game){
            myView.get().updateWaitingRoom((Game)arg);
        }
        else if(arg instanceof GameList){
            //Look for the current game in the list and update the waiting room with it
            for (Game g: ((GameList) arg).getGames()) {
                if(g.getGameID().equals(CModel.getInstance().getCurrGame().getGameID())){
                    myView.get().updateWaitingRoom(g);
                }
            }
        }
        else if(arg instanceof Boolean && ((Boolean) arg)){
            Toast.makeText(myView.get().getActivityContext(), "Start Game Success", Toast.LENGTH_SHORT).show();
            //TODO start the map activity
            //Intent intent = new Intent();
        }
    }

    @Override
    public void startGame(StartGameCommandData startGameCommandData){
        try {
            //call the async task
            ServerProxy.getInstance().sendCommand(startGameCommandData);
        } catch (Exception e) {
            Log.d(TAG, "hi my name is Kwan: " + e.toString());
            Log.d(TAG, "hi Kwan, my name is Jon: " + e.toString());
            e.printStackTrace();
        }
    }
}
