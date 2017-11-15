package clientModel;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import modeling.City;
import modeling.Player;
import modeling.Route;

/**
 * Created by tyler on 11/14/2017.
 */

public class LongestRouteCalc {

    private static final String TAG = "LongestRouteCalc";
    /**
     * The player list passed into us
     */
    private List<Player> playerList;
    /**The current route list we are working on*/
    private List<Route> routeList;

    public LongestRouteCalc(List<Player> players) {
        this.playerList = players;
    }

    /**
     * Returns the player with the longest path
     * Needs the route list passed into the constructor not to be null to work
     *
     * @return Player The player with the longest Route
     **/
    public Player findLongestRoute() {
        Player playerWithPath = null;
        int longestPath = 0;
        //goes through every player, seeing who has the longest route
        for (Player player : playerList) {
            Log.d(TAG, "Checking Longest route for Player: " + player.getPlayerName());
            routeList = player.getRoutes();
            //get the i'th players longest path
            int path = initLongestPath();

            if (path > longestPath) {
                String tagStr = "Player with longest path now: " + player.getPlayerName() + " with path of length " + path +
                        " it was " + playerWithPath + " with " + longestPath;
                Log.d(TAG, tagStr);
                playerWithPath = player;
                longestPath = path;
            }
        }
        return playerWithPath;
    }

    /**
     * Helper method which starts the process of getting the longest path for a particular player
     * We must start at every route to ensure we are returning the correct longest path
     */
    private int initLongestPath() {
       // return getLongestPath(routeList.get(0));
        int max = 0;
        for(Route r : routeList) {
            int dist = getLongestPath(r);
            if(dist > max){
                max = dist;
            }
        }
        return max;
    }

    /**
     * Returns the distance of this players routes
     *
     * @param start     The route we are first going to check
     */
    private int getLongestPath(Route start) {
        int max = 0;
        //set this city as visited,then get the next connecting route
        start.getFirstCity().setVisited(true);
        List<Route> nextRoutes = getNextCity(start.getSecondCity());
        //if there is a route where our "second" city is another's "first" city and it is not already visited
        if (nextRoutes.size() > 0  && !start.getSecondCity().isVisited()) {
            //int dist = start.getDistance() + getLongestPath(nextRoute);
            //if (dist > max)
             //   max = dist;
        }
        start.getFirstCity().setVisited(false);
        return max;
    }
    /**Gets the next route where the current "second" city passed in is next routes "first" city
     * @param city The "second" city passed in*/
    private List<Route> getNextCity(City city) {
        List<Route> routes = new ArrayList<>();
        for (Route r : routeList) {
            if (r.getFirstCity().equals(city)) {
                routes.add(r);
            }
        }
        return routes;
    }
}
