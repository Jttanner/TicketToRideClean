package modeling;

import java.util.List;
import java.util.Map;

/**
 * Created by ahwang13 on 10/21/17.
 */

public class RouteList {

    private List<Route> routesAvailable;
    private Map<Route, Player> routesClaimed;

    public RouteList(){
        /*
        routesAvailable.add(new Route("Atlanta", "Raleigh", Color.WILD, 2));
        routesAvailable.add(new Route("Atlanta", "Raleigh", Color.WILD, 2));
        routesAvailable.add(new Route("Atlanta", "Charleston", Color.WILD, 2));
        routesAvailable.add(new Route("Atlanta", "Miami", Color.BLUE, 5));
        routesAvailable.add(new Route("Atlanta", "New Orleans", Color.YELLOW, 4));
        routesAvailable.add(new Route("Atlanta", "New Orleans", Color.ORANGE, 4));
        routesAvailable.add(new Route("Atlanta", "Nashville", Color.WILD, 1));
        routesAvailable.add(new Route("Boston", "Montreal", Color.WILD, 2));
        routesAvailable.add(new Route("Boston", "Montreal", Color.WILD, 2));
        routesAvailable.add(new Route("Boston", "New York", Color.YELLOW, 2));
        routesAvailable.add(new Route("Boston", "New York", Color.RED, 2));
        routesAvailable.add(new Route("Calgary", "Vancouver", Color.WILD, 3));
        routesAvailable.add(new Route("Calgary", "Winnipeg", Color.WHITE, 6));
        routesAvailable.add(new Route("Calgary", "Helena", Color.WILD, 4));
        routesAvailable.add(new Route("Calgary", "Seattle", Color.WILD, 4));
        routesAvailable.add(new Route("Charleston", "Raleigh", Color.WILD, 2));
        routesAvailable.add(new Route("Charleston", "Miami", Color.PURPLE, 4));
        routesAvailable.add(new Route("Chicago", "Pittsburgh", Color.ORANGE, 3));
        routesAvailable.add(new Route("Chicago", "Pittsburgh", Color.BLACK, 3));
        routesAvailable.add(new Route("Chicago", "Toronto", Color.WHITE, 4));
        routesAvailable.add(new Route("Chicago", "Duluth", Color.RED, 3));
        routesAvailable.add(new Route("Chicago", "Omaha", Color.BLUE, 4));
        routesAvailable.add(new Route("Chicago", "St. Louis", Color.GREEN, 2));
        routesAvailable.add(new Route("Chicago", "St. Louis", Color.WHITE, 2));
        routesAvailable.add(new Route("Dallas", "Little Rock", Color.WILD, 2));
        routesAvailable.add(new Route("Dallas", "Oklahoma City", Color.WILD, 2));
        routesAvailable.add(new Route("Dallas", "Oklahoma City", Color.WILD, 2));
        routesAvailable.add(new Route("Dallas", "El Paso", Color.RED, 4));
        routesAvailable.add(new Route("Dallas", "Houston", Color.WILD, 1));
        routesAvailable.add(new Route("Dallas", "Houston", Color.WILD, 1));
        routesAvailable.add(new Route("Denver", "Kansas City", Color.BLACK, 4));
        routesAvailable.add(new Route("Denver", "Kansas City", Color.ORANGE, 4));
        routesAvailable.add(new Route("Denver", "Omaha", Color.PURPLE, 4));
        routesAvailable.add(new Route("Denver", "Helena", Color.GREEN, 4));
        routesAvailable.add(new Route("Denver", "Salt Lake City", Color.RED, 3));
        routesAvailable.add(new Route("Denver", "Salt Lake City", Color.YELLOW, 3));
        routesAvailable.add(new Route("Denver", "Phoenix", Color.WHITE, 5));
        routesAvailable.add(new Route("Denver", "Santa Fe", Color.WILD, 2));
        routesAvailable.add(new Route("Denver", "Oklahoma City", Color.RED, 4));
        routesAvailable.add(new Route("Duluth", "Omaha", Color.WILD, 2));
        routesAvailable.add(new Route("Duluth", "Omaha", Color.WILD, 2));
        routesAvailable.add(new Route("Duluth", "Toronto", Color.PURPLE, 6));
        routesAvailable.add(new Route("Duluth", "Sault St. Marie", Color.WILD, 3));
        routesAvailable.add(new Route("Duluth", "Winnipeg", Color.BLACK, 4));
        routesAvailable.add(new Route("Duluth", "Helena", Color.ORANGE, 6));
        routesAvailable.add(new Route("El Paso", "Houston", Color.GREEN, 6));
        routesAvailable.add(new Route("El Paso", "Oklahoma City", Color.YELLOW, 5));
        routesAvailable.add(new Route("El Paso", "Santa Fe", Color.WILD, 2));
        routesAvailable.add(new Route("El Paso", "Phoenix", Color.WILD, 3));
        routesAvailable.add(new Route("El Paso", "Los Angeles", Color.BLACK, 6));
        routesAvailable.add(new Route("Helena", "Winnipeg", Color.BLUE, 4));
        routesAvailable.add(new Route("Helena", "Omaha", Color.RED, 5));
        routesAvailable.add(new Route("Helena", "Salt Lake City", Color.PURPLE, 3));
        routesAvailable.add(new Route("Helena", "Seattle", Color.YELLOW, 6));
        routesAvailable.add(new Route("Houston", "New Orleans", Color.WILD, 2));
        routesAvailable.add(new Route("Kansas City", "St. Louis", Color.BLUE, 2));
        routesAvailable.add(new Route("Kansas City", "St. Louis", Color.PURPLE, 2));
        routesAvailable.add(new Route("Kansas City", "Omaha", Color.WILD, 1));
        routesAvailable.add(new Route("Kansas City", "Omaha", Color.WILD, 1));
        routesAvailable.add(new Route("Kansas City", "Oklahoma City", Color.WILD, 2));
        routesAvailable.add(new Route("Kansas City", "Oklahoma City", Color.WILD, 2));
        routesAvailable.add(new Route("Las Vegas", "Salt Lake City", Color.ORANGE, 3));
        routesAvailable.add(new Route("Las Vegas", "Los Angeles", Color.WILD, 2));
        routesAvailable.add(new Route("Little Rock", "Nashville", Color.WHITE, 3));
        routesAvailable.add(new Route("Little Rock", "St. Louis", Color.WILD, 2));
        routesAvailable.add(new Route("Little Rock", "Oklahoma City", Color.WILD, 2));
        routesAvailable.add(new Route("Little Rock", "New Orleans", Color.GREEN, 3));
        routesAvailable.add(new Route("Los Angeles", "San Francisco", Color.PURPLE, 3));
        routesAvailable.add(new Route("Los Angeles", "San Francisco", Color.YELLOW, 3));
        routesAvailable.add(new Route("Los Angeles", "Phoenix", Color.WILD, 3));
        routesAvailable.add(new Route("Miami", "New Orleans", Color.RED, 6));
        routesAvailable.add(new Route("Montreal", "New York", Color.BLUE, 3));
        routesAvailable.add(new Route("Montreal", "Toronto", Color.WILD, 3));
        routesAvailable.add(new Route("Montreal", "Sault St. Marie", Color.BLACK, 5));
        routesAvailable.add(new Route("Nashville", "Raleigh", Color.BLACK, 3));
        routesAvailable.add(new Route("Nashville", "Pittsburgh", Color.YELLOW, 4));
        routesAvailable.add(new Route("Nashville", "St. Louis", Color.WILD, 2));
        routesAvailable.add(new Route("New York", "Washington", Color.BLACK, 2));
        routesAvailable.add(new Route("New York", "Washington", Color.ORANGE, 2));
        routesAvailable.add(new Route("New York", "Pittsburgh", Color.WHITE, 2));
        routesAvailable.add(new Route("New York", "Pittsburgh", Color.GREEN, 2));
        routesAvailable.add(new Route("Oklahoma City", "Santa Fe", Color.BLUE, 3));
        routesAvailable.add(new Route("Phoenix", "Santa Fe", Color.WILD, 3));
        routesAvailable.add(new Route("Pittsburgh", "Washington", Color.WILD, 2));
        routesAvailable.add(new Route("Pittsburgh", "Raleigh", Color.WILD, 2));
        routesAvailable.add(new Route("Pittsburgh", "St. Louis", Color.GREEN, 5));
        routesAvailable.add(new Route("Pittsburgh", "Toronto", Color.WILD, 2));
        routesAvailable.add(new Route("Portland", "Seattle", Color.WILD, 1));
        routesAvailable.add(new Route("Portland", "Seattle", Color.WILD, 1));
        routesAvailable.add(new Route("Portland", "Salt Lake City", Color.BLUE, 6));
        routesAvailable.add(new Route("Portland", "San Francisco", Color.GREEN, 5));
        routesAvailable.add(new Route("Portland", "San Francisco", Color.PURPLE, 5));
        routesAvailable.add(new Route("Raleigh", "Washington", Color.WILD, 2));
        routesAvailable.add(new Route("Raleigh", "Washington", Color.WILD, 2));
        routesAvailable.add(new Route("Salt Lake City", "San Francisco", Color.ORANGE, 5));
        routesAvailable.add(new Route("Salt Lake City", "San Francisco", Color.WHITE, 5));
        routesAvailable.add(new Route("Sault St. Marie", "Winnipeg", Color.WILD, 6));
        routesAvailable.add(new Route("Sault St. Marie", "Toronto", Color.WILD, 2));
        routesAvailable.add(new Route("Seattle", "Vancouver", Color.WILD, 1));
        routesAvailable.add(new Route("Seattle", "Vancouver", Color.WILD, 1));
        */
    }
}
