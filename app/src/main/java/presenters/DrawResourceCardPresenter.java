package presenters;

import java.lang.ref.WeakReference;
import java.util.Observable;
import java.util.Observer;

import MVP_coms_classes.MVP_DrawResourceCard;
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
        if(myView != null) {
            if(arg instanceof String) {
                if(((String) arg).equals("UpdateFaceUpView")) {
                    myView.get().upDateFaceUp();
                }
                else if(((String) arg).equals("CloseResourceFragment")) {
                    myView.get().close();
                }
                else if(((String) arg).equals("ResourceCardButtonsOn")) {
                    myView.get().ButtonsOn();
                }
                else if(((String) arg).equals("ResourceCardButtonsOff")) {
                    myView.get().ButtonsOn();
                }
            }
        }
    }

    @Override
    public void drawCard(ResourceCard resourceCard) {
        //Once they choose a card, lock the screen
        if(myView != null) {
            myView.get().lock();
            CModel.getInstance().getCurrGameState().drawResourceCard(resourceCard);
        }
    }

    @Override
    public void shuffleDiscardPile() {

    }
}
