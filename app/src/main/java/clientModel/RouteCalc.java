package clientModel;

import java.util.ArrayList;
import java.util.List;

import modeling.City;
import modeling.DestinationCard;
import modeling.Player;
import modeling.Route;

/**
 * Created by tyler on 11/14/2017.
 */

public class RouteCalc {

    private static final String TAG = "RouteCalc";
    /**
     * The current route list we are working on
     */
    private List<Route> routeList;

    public RouteCalc() {
    }

    /**
     * Returns the player with the longest path
     * Needs the route list passed into the constructor not to be null to work
     *
     * @return Player The player with the longest Route
     **/
    public Player findLongestRoute(List<Player> playerList) {
        Player playerWithPath = null;
        int longestPath = 0;
        //goes through every player, seeing who has the longest route
        for (Player player : playerList) {
            //Log.d(TAG, "Checking Longest route for Player: " + player.getPlayerName());
            this.setRouteList(player.getRoutes());
            //get the i'th players longest path
            int path = initLongestPath();

            if (path > longestPath) {
                /*String tagStr = "Player with longest path now: " + player.getPlayerName() + " with path of length " + path +
                        " it was " + playerWithPath + " with " + longestPath;
                Log.d(TAG, tagStr);*/
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
            //System.out.println("Starting with " + r.toString() + "\n");
            int dist = getLongestPath(r);
            //System.out.println("This routes distance is: " + dist + "\n");
            if (dist > max) {
                max = dist;
            }
            //have to reset the routes as not visited every time we start else algorithm will not work
            resetRouteList();
        }
        //System.out.println("This tree's distance: " + max);
        return max;
    }

    /**
     * Returns the distance of this players routes
     *
     * @param start The route we are first going to check
     */
    private int getLongestPath(Route start) {
        //System.out.println("Node: " + start.toString());
        int max = 0, dist = 0;
        //set this city as visited,then get the next connecting route
        start.setVisited(true);
        List<Route> nextRoutes = GetNextRoutes(start);
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
        //if we hit a leaf, lets return its value
        if (nextRoutes.size() == 0) {
            //System.out.println("ACTUALLY A LEAF: " + start.toString());
            return start.getDistance();
        }
        start.setVisited(false);
        return max;
    }

    /**
     * Gets the next route(s) where the current "second" city passed in is next route(s) "first" city
     *
     * @param start The "second" city passed in
     * @return List<Route> A list of child routes where passed in city is their "first" city
     */
    private List<Route> GetNextRoutes(Route start) {
        List<Route> routes = new ArrayList<>();
        for (Route r : routeList) {
            if (!r.isVisited() && (sharesCity(start, r))) {
                routes.add(r);
            }
        }
        //Log.d(TAG,"city.getCityName() " + "has " + routes.size() + " routes as children");
        return routes;
    }

    private boolean sharesCity(Route start, Route r) {
        return start.getFirstCity().equals(r.getFirstCity())
                || start.getFirstCity().equals(r.getSecondCity())
                || start.getSecondCity().equals(r.getFirstCity())
                || start.getSecondCity().equals(r.getSecondCity());


    }

    /**
     * Resets every route in the list as not visited
     */
    private void resetRouteList() {
        for (Route r : routeList) {
            r.setVisited(false);
        }
    }

    private void setRouteList(List<Route> routeList) {
        this.routeList = routeList;
    }

    /**
     * Checks if a given destination card was completed by the player
     *
     * @param card The destination card in question
     * @param list A List of all the player's Route's
     * @return Boolean Whether it is complete or not
     */
    public boolean isDestinationCardComplete(DestinationCard card, List<Route> list) {
        City city1 = card.getFirst();
        City city2 = card.getSecond();
        List<Route> startingRoute = new ArrayList<>();
        for (Route route : list) {
            if (city1.getCityName().equals(route.getFirstCityName())
                    || city1.getCityName().equals(route.getSecondCityName())) {
                startingRoute.add(route);
            }
        }
        //set the route list we are using
        this.setRouteList(list);
        for (Route route : startingRoute) {
            if (recursion(route, city2,false)) {
                resetRouteList();
                return true;
            }
        }
        resetRouteList();
        return false;

    }

    private boolean recursion(Route route, City city2,boolean isFound) {
        route.setVisited(true);
        List<Route> nextRoutes = GetNextRoutes(route);
        //if there is a route where our "second" city is another's "first" city
        //go through every child node
        for (Route each : nextRoutes) {
            //if not visited
            if (!each.isVisited() ){
                //if true pass along the true statement
                //also just a note but simplifying the if statement below will cause the code to not work
                if (each.getFirstCityName().equals(city2.getCityName())
                        || each.getSecondCityName().equals(city2.getCityName())) {
                    isFound = true;
                }
                else {
                    isFound = recursion(each, city2,isFound);
                }
            }
        }
        //if we hit a leaf, lets return its value
        route.setVisited(false);
        return isFound;
    }
}
