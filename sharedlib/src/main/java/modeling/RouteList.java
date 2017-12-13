package modeling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ahwang13 on 10/21/17.
 */

public class RouteList {

    private List<Route> routesAvailable = new ArrayList<>();
   // private Map<Route, Player> routesClaimed = new HashMap<>();
    private Map<String, Route> routesClaimed = new HashMap<>();
    public Route getAvailableRoute(String startCity, String endCity, String routeColor){
        for (Route route : routesAvailable){
            if ((route.getFirstCityName().equals(startCity) && route.getSecondCityName().equals(endCity) ||
                    route.getSecondCityName().equals(startCity) && route.getFirstCityName().equals(endCity)) &&
                    route.getTrainColorNeeded().equals(routeColor)){
                return route;
            }
        }
        return null;
    }

    public Map<String, Route> getRoutesMap(){
        return  routesClaimed;
    }

    public void addAvailableRoute(Route route){
        routesAvailable.add(route);
    }

    public void removeAvailableRoute(Route route){
        routesAvailable.remove(route);
    }

    public void addClaimedRoute(String PlayerID, Route route){
        routesClaimed.put(PlayerID,route);
    }

    public int getAvailableRouteSize(){
        return routesAvailable.size();
    }

    public void removeClaimedRoute(Route route){
        routesClaimed.remove(route);
    }

    public List<Route> getRoutesFromCity(String cityName){
        List<Route> connections = new ArrayList<>();
        for (Route route : routesAvailable){
            if (route.getFirstCityName().equals(cityName) || route.getSecondCityName().equals(cityName)){
                connections.add(route);
            }
        }
        return connections;
    }

    public List<String> getCityRouteInfoStrings(String baseCityName){
        List<String> cityConnectionsInfo = new ArrayList<>();
        for (Route route : getRoutesFromCity(baseCityName)){
            StringBuilder cityInfo = new StringBuilder();
            if (route.getFirstCityName().equals(baseCityName)){
                cityInfo.append(route.getFirstCityName());
                cityInfo.append(" to ");
                cityInfo.append(route.getSecondCityName());
            } else { //will always equal the second, otherwise it won't be in the list returned to us.
                cityInfo.append(route.getSecondCityName());
                cityInfo.append(" to ");
                cityInfo.append(route.getFirstCityName());
            }
            cityInfo.append(" \nDistance: ");
            cityInfo.append(route.getDistance());
            cityInfo.append(" Color: ");
            cityInfo.append(route.getTrainColorNeeded());
            cityConnectionsInfo.add(cityInfo.toString());
        }
        return cityConnectionsInfo;
    }

    private void setDoubles(){
        Route lastRoute = null;
        for (Route route : routesAvailable){
            if (lastRoute == null){
                //first is duplicate so its okay
                lastRoute = route;
            } else{
                if ((lastRoute.getFirstCityName().equals(route.getFirstCityName()) && lastRoute.getSecondCityName().equals(route.getSecondCityName())) ||
                        (lastRoute.getSecondCityName().equals(route.getFirstCityName()) && lastRoute.getFirstCityName().equals(route.getSecondCityName())) ){
                    route.setIsDouble();
                }
                lastRoute = route;
            }
        }
    }

    public RouteList(boolean initialize){
        if (initialize){
            routesAvailable.add(new Route("Atlanta", "Raleigh", "Wild", 2, true));
            routesAvailable.add(new Route("Atlanta", "Raleigh", "Wild", 2, true));
            routesAvailable.add(new Route("Atlanta", "Charleston", "Wild", 2));
            routesAvailable.add(new Route("Atlanta", "Miami", "Blue", 5));
            routesAvailable.add(new Route("Atlanta", "New Orleans", "Yellow", 4, true));
            routesAvailable.add(new Route("Atlanta", "New Orleans", "Orange", 4, true));
            routesAvailable.add(new Route("Atlanta", "Nashville", "Wild", 1));
            routesAvailable.add(new Route("Boston", "Montreal", "Wild", 2, true));
            routesAvailable.add(new Route("Boston", "Montreal", "Wild", 2, true));
            routesAvailable.add(new Route("Boston", "New York", "Yellow", 2, true));
            routesAvailable.add(new Route("Boston", "New York", "Red", 2, true));
            routesAvailable.add(new Route("Calgary", "Vancouver", "Wild", 3));
            routesAvailable.add(new Route("Calgary", "Winnipeg", "White", 6));
            routesAvailable.add(new Route("Calgary", "Helena", "Wild", 4));
            routesAvailable.add(new Route("Calgary", "Seattle", "Wild", 4));
            routesAvailable.add(new Route("Charleston", "Raleigh", "Wild", 2));
            routesAvailable.add(new Route("Charleston", "Miami", "Purple", 4));
            routesAvailable.add(new Route("Chicago", "Pittsburgh", "Orange", 3, true));
            routesAvailable.add(new Route("Chicago", "Pittsburgh", "Black", 3, true));
            routesAvailable.add(new Route("Chicago", "Toronto", "White", 4));
            routesAvailable.add(new Route("Chicago", "Duluth", "Red", 3));
            routesAvailable.add(new Route("Chicago", "Omaha", "Blue", 4));
            routesAvailable.add(new Route("Chicago", "St. Louis", "Green", 2, true));
            routesAvailable.add(new Route("Chicago", "St. Louis", "White", 2, true));
            routesAvailable.add(new Route("Dallas", "Little Rock", "Wild", 2));
            routesAvailable.add(new Route("Dallas", "Oklahoma City", "Wild", 2, true));
            routesAvailable.add(new Route("Dallas", "Oklahoma City", "Wild", 2, true));
            routesAvailable.add(new Route("Dallas", "El Paso", "Red", 4));
            routesAvailable.add(new Route("Dallas", "Houston", "Wild", 1, true));
            routesAvailable.add(new Route("Dallas", "Houston", "Wild", 1, true));
            routesAvailable.add(new Route("Denver", "Kansas City", "Black", 4, true));
            routesAvailable.add(new Route("Denver", "Kansas City", "Orange", 4, true));
            routesAvailable.add(new Route("Denver", "Omaha", "Purple", 4));
            routesAvailable.add(new Route("Denver", "Helena", "Green", 4));
            routesAvailable.add(new Route("Denver", "Salt Lake City", "Red", 3, true));
            routesAvailable.add(new Route("Denver", "Salt Lake City", "Yellow", 3, true));
            routesAvailable.add(new Route("Denver", "Phoenix", "White", 5));
            routesAvailable.add(new Route("Denver", "Santa Fe", "Wild", 2));
            routesAvailable.add(new Route("Denver", "Oklahoma City", "Red", 4));
            routesAvailable.add(new Route("Duluth", "Omaha", "Wild", 2, true));
            routesAvailable.add(new Route("Duluth", "Omaha", "Wild", 2, true));
            routesAvailable.add(new Route("Duluth", "Toronto", "Purple", 6));
            routesAvailable.add(new Route("Duluth", "Sault St. Marie", "Wild", 3));
            routesAvailable.add(new Route("Duluth", "Winnipeg", "Black", 4));
            routesAvailable.add(new Route("Duluth", "Helena", "Orange", 6));
            routesAvailable.add(new Route("El Paso", "Houston", "Green", 6));
            routesAvailable.add(new Route("El Paso", "Oklahoma City", "Yellow", 5));
            routesAvailable.add(new Route("El Paso", "Santa Fe", "Wild", 2));
            routesAvailable.add(new Route("El Paso", "Phoenix", "Wild", 3));
            routesAvailable.add(new Route("El Paso", "Los Angeles", "Black", 6));
            routesAvailable.add(new Route("Helena", "Winnipeg", "Blue", 4));
            routesAvailable.add(new Route("Helena", "Omaha", "Red", 5));
            routesAvailable.add(new Route("Helena", "Salt Lake City", "Purple", 3));
            routesAvailable.add(new Route("Helena", "Seattle", "Yellow", 6));
            routesAvailable.add(new Route("Houston", "New Orleans", "Wild", 2));
            routesAvailable.add(new Route("Kansas City", "St. Louis", "Blue", 2, true));
            routesAvailable.add(new Route("Kansas City", "St. Louis", "Purple", 2, true));
            routesAvailable.add(new Route("Kansas City", "Omaha", "Wild", 1, true));
            routesAvailable.add(new Route("Kansas City", "Omaha", "Wild", 1, true));
            routesAvailable.add(new Route("Kansas City", "Oklahoma City", "Wild", 2, true));
            routesAvailable.add(new Route("Kansas City", "Oklahoma City", "Wild", 2, true));
            routesAvailable.add(new Route("Las Vegas", "Salt Lake City", "Orange", 3));
            routesAvailable.add(new Route("Las Vegas", "Los Angeles", "Wild", 2));
            routesAvailable.add(new Route("Little Rock", "Nashville", "White", 3));
            routesAvailable.add(new Route("Little Rock", "St. Louis", "Wild", 2));
            routesAvailable.add(new Route("Little Rock", "Oklahoma City", "Wild", 2));
            routesAvailable.add(new Route("Little Rock", "New Orleans", "Green", 3));
            routesAvailable.add(new Route("Los Angeles", "San Francisco", "Purple", 3, true));
            routesAvailable.add(new Route("Los Angeles", "San Francisco", "Yellow", 3, true));
            routesAvailable.add(new Route("Los Angeles", "Phoenix", "Wild", 3));
            routesAvailable.add(new Route("Miami", "New Orleans", "Red", 6));
            routesAvailable.add(new Route("Montreal", "New York", "Blue", 3));
            routesAvailable.add(new Route("Montreal", "Toronto", "Wild", 3));
            routesAvailable.add(new Route("Montreal", "Sault St. Marie", "Black", 5));
            routesAvailable.add(new Route("Nashville", "Raleigh", "Black", 3));
            routesAvailable.add(new Route("Nashville", "Pittsburgh", "Yellow", 4));
            routesAvailable.add(new Route("Nashville", "St. Louis", "Wild", 2));
            routesAvailable.add(new Route("New York", "Washington", "Black", 2, true));
            routesAvailable.add(new Route("New York", "Washington", "Orange", 2, true));
            routesAvailable.add(new Route("New York", "Pittsburgh", "White", 2, true));
            routesAvailable.add(new Route("New York", "Pittsburgh", "Green", 2, true));
            routesAvailable.add(new Route("Oklahoma City", "Santa Fe", "Blue", 3));
            routesAvailable.add(new Route("Phoenix", "Santa Fe", "Wild", 3));
            routesAvailable.add(new Route("Pittsburgh", "Washington", "Wild", 2));
            routesAvailable.add(new Route("Pittsburgh", "Raleigh", "Wild", 2));
            routesAvailable.add(new Route("Pittsburgh", "St. Louis", "Green", 5));
            routesAvailable.add(new Route("Pittsburgh", "Toronto", "Wild", 2));
            routesAvailable.add(new Route("Portland", "Seattle", "Wild", 1, true));
            routesAvailable.add(new Route("Portland", "Seattle", "Wild", 1, true));
            routesAvailable.add(new Route("Portland", "Salt Lake City", "Blue", 6));
            routesAvailable.add(new Route("Portland", "San Francisco", "Green", 5, true));
            routesAvailable.add(new Route("Portland", "San Francisco", "Purple", 5, true));
            routesAvailable.add(new Route("Raleigh", "Washington", "Wild", 2, true));
            routesAvailable.add(new Route("Raleigh", "Washington", "Wild", 2, true));
            routesAvailable.add(new Route("Salt Lake City", "San Francisco", "Orange", 5, true));
            routesAvailable.add(new Route("Salt Lake City", "San Francisco", "White", 5, true));
            routesAvailable.add(new Route("Sault St. Marie", "Winnipeg", "Wild", 6));
            routesAvailable.add(new Route("Sault St. Marie", "Toronto", "Wild", 2));
            routesAvailable.add(new Route("Seattle", "Vancouver", "Wild", 1, true));
            routesAvailable.add(new Route("Seattle", "Vancouver", "Wild", 1, true));
            //setDoubles();
        }

    }
}
