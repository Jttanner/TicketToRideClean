package MVP_coms_classes;

import android.content.Context;

import modeling.Route;

/**
 * Created by tyler on 10/19/2017.
 * This interface handles information transfers between tha mapview and it's presenter
 */

public interface MVP_Map {
    /**
     * Required View methods available to Presenter.
     * A passive layer, responsible to show data
     * and receive user interactions
     */
    interface MapViewOps{
        /***/
        void updateMap();
        /**Returns the application context*/
        Context getAppContext();
        /**The activity context of the view*/
        Context getActivityContext();
        /**Updates the route to be a certain player's*/
        void ResourceCardOption();
        void DestinationCardOption();
        void StartGameOver();
        void ClaimRouteOption();
    }
    /**
     * Operations offered to View to communicate with Presenter.
     * Processes user interactions, sends data requests to Model, etc.
     */
    interface MapPresOps{

        /**Will tell the model who just claimed a route*/
        //Not sure if needed if model will just take care of it,void claimRoute(Route r);
//        void UpdateChat(String s);
    }
}
