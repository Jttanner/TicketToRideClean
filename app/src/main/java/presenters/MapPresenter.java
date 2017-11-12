package presenters;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import MVP_coms_classes.MVP_Map;
import clientModel.CModel;
import commandData.ChatCommandData;
import modeling.City;
import modeling.Game;
import modeling.Player;
import modeling.Route;
import modeling.RouteList;
import servercomms.ServerProxy;

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
        if (arg instanceof Game){
                myView.get().updateMap();
        }
        else if (((String) arg).equals("DrawResourceCard123")) {
            myView.get().ResourceCardOption();
        }
        else if (((String) arg).equals("DrawDestinationCard")) {
            myView.get().DestinationCardOption();
        }
        else if (((String) arg).equals("ClaimRoute")) {
            myView.get().ClaimRouteOption();
        }
    }

    public List<String> getCityConnections(String baseCityName){
        return CModel.getInstance().getUnclaimedRouteList().getCityRouteInfoStrings(baseCityName);
    }

}
