package presenters;

import java.lang.ref.WeakReference;
import java.util.Observable;
import java.util.Observer;

import MVP_coms_classes.MVP_Map;
import clientModel.CModel;

/**
 * Created by tyler on 10/19/2017.
 */

public class MapPresenter implements MVP_Map.MapPresOps, Observer {

    private WeakReference<MVP_Map.MapViewOps> myView;



    public MapPresenter(MVP_Map.MapViewOps view) {
        myView = new WeakReference<>(view);
        CModel.getInstance().addObserver(this);
    }
    /*@Override
    public void claimRoute(Route r) {

    }*/
    /**This will update the map view according to appropriate changes in the model*/
    @Override
    public void update(Observable o, Object arg) {
        //ex, game won,player attributes changed(routeClaimed),
    }
}
