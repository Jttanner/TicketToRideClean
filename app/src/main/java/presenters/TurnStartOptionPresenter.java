package presenters;

import java.lang.ref.WeakReference;
import java.util.Observable;
import java.util.Observer;

import MVP_coms_classes.MVP_TurnStartOption;
import clientModel.CModel;

/**
 * Created by korea on 11/8/2017.
 */

public class TurnStartOptionPresenter implements MVP_TurnStartOption.TurnStartOptionPresOps, Observer{
    private WeakReference<MVP_TurnStartOption.TurnStartOptionViewOps> myView;

    public TurnStartOptionPresenter(MVP_TurnStartOption.TurnStartOptionViewOps view) {
        myView = new WeakReference<>(view);
        CModel.getInstance().addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {

    }


    @Override
    public void ResourceCardOption() {
        CModel.getInstance().DrawResourceCard();
    }

    @Override
    public void DestinationCardOption() {
        CModel.getInstance().DrawDestinationCard();
    }

    @Override
    public void ClaimRouteOption() {
        CModel.getInstance().ClaimRoute();
    }

}
