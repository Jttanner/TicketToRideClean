package presenters;

import android.util.Log;

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
    public void deleteObserver() {
        CModel.getInstance().deleteObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        //for when the game is set
        if(arg instanceof Game){
            myView.get().updateWaitingRoom((Game)arg);
        }
        //for when the poller updates the gamelist and we need to update the player list accordingly
        else if(arg instanceof GameList){
            //Look for the current game in the list and update the waiting room with it
            for (Game g: ((GameList) arg).getGames()) {
                if(g.getGameID().equals(CModel.getInstance().getCurrGame().getGameID())){
                    myView.get().updateWaitingRoom(g);
                }
            }
        }
        //if we got a boolean back and it was true
        else if(arg instanceof Boolean){
            if((Boolean)arg){
                myView.get().goToMapActivity();
            }
            else{
                myView.get().goToMapFailed();
            }

        }
    }
    /**Calls the server proxy to try and join the game*/
    @Override
    public void startGame(){
        try {
            //call the async task
            StartGameCommandData data = new StartGameCommandData(CModel.getInstance().getCurrGame());
            ServerProxy.getInstance().sendCommand(data);
        } catch (Exception e) {
            Log.d(TAG, "hi my name is Kwan: " + e.toString());
            Log.d(TAG, "hi Kwan, my name is Jon: " + e.toString());
            Log.d(TAG, "hi Jon, it is nice to meet you: " + e.toString());
            e.printStackTrace();
        }
    }
}
