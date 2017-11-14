package presenters;

import java.lang.ref.WeakReference;
import java.util.Observable;
import java.util.Observer;

import MVP_coms_classes.MVP_DrawResourceCard;
import clientCommands.DrawTrainCardFaceUp;
import clientModel.CModel;
import modeling.ResourceCard;

/**
 * Created by korea on 11/8/2017.
 */

public class DrawResourceCardPresenter implements MVP_DrawResourceCard.DrawResourceCardPresOps, Observer {
    private WeakReference<MVP_DrawResourceCard.DrawResourceCardViewOps> myView;

    public DrawResourceCardPresenter(MVP_DrawResourceCard.DrawResourceCardViewOps view) {
        myView = new WeakReference<>(view);
        CModel.getInstance().addObserver(this);

    }
    @Override
    public void update(Observable o, Object arg) {
        if(((String) arg).equals("UpdateFaceUpView")) {
           if(myView != null) {
               //myView.get().upDateFaceUp();
               myView.get().close();
               CModel.getInstance().drawResourceCard();
           }
        }

    }

    @Override
    public void drawCard(int position) {
        CModel.getInstance().getCurrGameState().drawResourceCard(position);
    }

    @Override
    public void shuffleDiscardPile() {

    }
}
