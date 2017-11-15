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
    /**
     * The current route list we are working on
     */
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
        int max = 0;
        //Go through every route as the starting route, seeing which one gives us the longest path
        for (Route r : routeList) {
            int dist = getLongestPath(r);
            if (dist > max) {
                max = dist;
            }
            //have to reset the routes as not visited every time we start else algorithm will not work
            resetRouteList();
        }
        return max;
    }

    /**
     * Returns the distance of this players routes
     *
     * @param start The route we are first going to check
     */
    private int getLongestPath(Route start) {
        int max = 0, dist = 0;
        //set this city as visited,then get the next connecting route
        start.setVisited(true);
        List<Route> nextRoutes = GetNextRoutes(start.getSecondCity());
        //if there is a route where our "second" city is another's "first" city
        //go through every child node
        for (Route nextRoute : nextRoutes) {
            if (!nextRoute.isVisited()) {
                dist = start.getDistance() + getLongestPath(nextRoute);
                if (dist > max) {
                    max = dist;
                }
            }
        }
        // }
        start.setVisited(false);
        return max;
    }

    /**
     * Gets the next route(s) where the current "second" city passed in is next route(s) "first" city
     *
     * @param city The "second" city passed in
     * @return List<Route> A list of child routes where passed in city is their "first" city
     */
    private List<Route> GetNextRoutes(City city) {
        List<Route> routes = new ArrayList<>();
        for (Route r : routeList) {
            if (r.getFirstCity().equals(city)) {
                routes.add(r);
            }
        }
        return routes;
    }

    /**
     * Resets every route in the list as not visited
     */
    private void resetRouteList() {
        for (Route r : routeList) {
            r.setVisited(false);
        }
    }
}
