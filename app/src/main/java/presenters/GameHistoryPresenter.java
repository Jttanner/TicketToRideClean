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
        if(((String) arg).equals("UpdateGameHistory")) {
            //Call the view and update it with a new game history list
            myView.get().updateGameHistory(CModel.getInstance().getCurrGame().getGameHistoryList());
        }
    }

//    @Override
//    public void exitView() {
//
//    }
}
