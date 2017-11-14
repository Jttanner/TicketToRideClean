package presenters;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import MVP_coms_classes.MVP_GameHistory;
import clientModel.CModel;
import modeling.History;

/**
 * Created by korea on 10/27/2017.
 */

public class GameHistoryPresenter implements Observer {
    private WeakReference<MVP_GameHistory.GameHistoryViewOps> myView;

    public GameHistoryPresenter(MVP_GameHistory.GameHistoryViewOps view) {
        myView = new WeakReference<>(view);
        CModel.getInstance().addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        //Might not need this observable
        //When a command is executed the currGame will have the history added on.
        //When the user opens up the fragment it will automatically pull the latest history
        //So, the view won't open by itself, only when the user opens up the history fragment

//        if(arg instanceof String) {
//            if(((String) arg).equals("UpdateGameHistory")) {
//                //Call the view and update it with a new game history list
//                //If that fragment is not up, then don't do anything
//                if(myView != null) {
//                    myView.get().updateGameHistory(CModel.getInstance().getCurrGame().getGameHistoryList());
//                }
//                else{
//                    //does that mean we have to keep calling it?
//                    //
//                }
//
//            }
//        }
    }

//    @Override
//    public void exitView() {
//
//    }
}
