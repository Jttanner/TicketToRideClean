package presenters;

import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import MVP_coms_classes.MVP_Map;
import clientModel.CModel;
import commandData.ChatCommandData;
import modeling.Game;
import modeling.Player;
import modeling.Route;
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
            /*
            GameList games = (GameList) arg;
            myView.get().UpdateList(games.getGames());
             */
            //Game currGame = (Game) arg;
            //for (Map.Entry<Route, Player> entry : CModel.getInstance().getClaimedRouteList().getRoutesMap().entrySet()){
                myView.get().updateMap();
            //}
            //myView.get().updateMap();
        }
        //ex, game won,player attributes changed(routeClaimed),
    }

//    public void UpdateChat(String s){
//        ChatCommandData chatCommandData = new ChatCommandData(s);
//        ServerProxy.getInstance().sendCommand(chatCommandData);
//    }
}
