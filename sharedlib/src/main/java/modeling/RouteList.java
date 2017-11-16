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
    private Map<Route, Player> routesClaimed = new HashMap<>();

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

    public Map<Route, Player> getRoutesMap(){
        return  routesClaimed;
    }

    public void addAvailableRoute(Route route){
        routesAvailable.add(route);
    }

    public void removeAvailableRoute(Route route){
        routesAvailable.remove(route);
    }

    public void addClaimedRoute(Route route, Player player){
        routesClaimed.put(route, player);
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
            routesAvailable.add(new Route("Atlanta", "Miami", "blue", 5));
            routesAvailable.add(new Route("Atlanta", "New Orleans", "yellow", 4, true));
            routesAvailable.add(new Route("Atlanta", "New Orleans", "orange", 4, true));
            routesAvailable.add(new Route("Atlanta", "Nashville", "Wild", 1));
            routesAvailable.add(new Route("Boston", "Montreal", "Wild", 2, true));
            routesAvailable.add(new Route("Boston", "Montreal", "Wild", 2, true));
            routesAvailable.add(new Route("Boston", "New York", "yellow", 2, true));
            routesAvailable.add(new Route("Boston", "New York", "red", 2, true));
            routesAvailable.add(new Route("Calgary", "Vancouver", "Wild", 3));
            routesAvailable.add(new Route("Calgary", "Winnipeg", "white", 6));
            routesAvailable.add(new Route("Calgary", "Helena", "Wild", 4));
            routesAvailable.add(new Route("Calgary", "Seattle", "Wild", 4));
            routesAvailable.add(new Route("Charleston", "Raleigh", "Wild", 2));
            routesAvailable.add(new Route("Charleston", "Miami", "purple", 4));
            routesAvailable.add(new Route("Chicago", "Pittsburgh", "orange", 3, true));
            routesAvailable.add(new Route("Chicago", "Pittsburgh", "black", 3, true));
            routesAvailable.add(new Route("Chicago", "Toronto", "white", 4));
            routesAvailable.add(new Route("Chicago", "Duluth", "red", 3));
            routesAvailable.add(new Route("Chicago", "Omaha", "blue", 4));
            routesAvailable.add(new Route("Chicago", "St. Louis", "green", 2, true));
            routesAvailable.add(new Route("Chicago", "St. Louis", "white", 2, true));
            routesAvailable.add(new Route("Dallas", "Little Rock", "Wild", 2));
            routesAvailable.add(new Route("Dallas", "Oklahoma City", "Wild", 2, true));
            routesAvailable.add(new Route("Dallas", "Oklahoma City", "Wild", 2, true));
            routesAvailable.add(new Route("Dallas", "El Paso", "red", 4));
            routesAvailable.add(new Route("Dallas", "Houston", "Wild", 1, true));
            routesAvailable.add(new Route("Dallas", "Houston", "Wild", 1, true));
            routesAvailable.add(new Route("Denver", "Kansas City", "black", 4, true));
            routesAvailable.add(new Route("Denver", "Kansas City", "orange", 4, true));
            routesAvailable.add(new Route("Denver", "Omaha", "purple", 4));
            routesAvailable.add(new Route("Denver", "Helena", "green", 4));
            routesAvailable.add(new Route("Denver", "Salt Lake City", "red", 3, true));
            routesAvailable.add(new Route("Denver", "Salt Lake City", "yellow", 3, true));
            routesAvailable.add(new Route("Denver", "Phoenix", "white", 5));
            routesAvailable.add(new Route("Denver", "Santa Fe", "Wild", 2));
            routesAvailable.add(new Route("Denver", "Oklahoma City", "red", 4));
            routesAvailable.add(new Route("Duluth", "Omaha", "Wild", 2, true));
            routesAvailable.add(new Route("Duluth", "Omaha", "Wild", 2, true));
            routesAvailable.add(new Route("Duluth", "Toronto", "purple", 6));
            routesAvailable.add(new Route("Duluth", "Sault St. Marie", "Wild", 3));
            routesAvailable.add(new Route("Duluth", "Winnipeg", "black", 4));
            routesAvailable.add(new Route("Duluth", "Helena", "orange", 6));
            routesAvailable.add(new Route("El Paso", "Houston", "green", 6));
            routesAvailable.add(new Route("El Paso", "Oklahoma City", "yellow", 5));
            routesAvailable.add(new Route("El Paso", "Santa Fe", "Wild", 2));
            routesAvailable.add(new Route("El Paso", "Phoenix", "Wild", 3));
            routesAvailable.add(new Route("El Paso", "Los Angeles", "black", 6));
            routesAvailable.add(new Route("Helena", "Winnipeg", "blue", 4));
            routesAvailable.add(new Route("Helena", "Omaha", "red", 5));
            routesAvailable.add(new Route("Helena", "Salt Lake City", "purple", 3));
            routesAvailable.add(new Route("Helena", "Seattle", "yellow", 6));
            routesAvailable.add(new Route("Houston", "New Orleans", "Wild", 2));
            routesAvailable.add(new Route("Kansas City", "St. Louis", "blue", 2, true));
            routesAvailable.add(new Route("Kansas City", "St. Louis", "purple", 2, true));
            routesAvailable.add(new Route("Kansas City", "Omaha", "Wild", 1, true));
            routesAvailable.add(new Route("Kansas City", "Omaha", "Wild", 1, true));
            routesAvailable.add(new Route("Kansas City", "Oklahoma City", "Wild", 2, true));
            routesAvailable.add(new Route("Kansas City", "Oklahoma City", "Wild", 2, true));
            routesAvailable.add(new Route("Las Vegas", "Salt Lake City", "orange", 3));
            routesAvailable.add(new Route("Las Vegas", "Los Angeles", "Wild", 2));
            routesAvailable.add(new Route("Little Rock", "Nashville", "white", 3));
            routesAvailable.add(new Route("Little Rock", "St. Louis", "Wild", 2));
            routesAvailable.add(new Route("Little Rock", "Oklahoma City", "Wild", 2));
            routesAvailable.add(new Route("Little Rock", "New Orleans", "green", 3));
            routesAvailable.add(new Route("Los Angeles", "San Francisco", "purple", 3, true));
            routesAvailable.add(new Route("Los Angeles", "San Francisco", "yellow", 3, true));
            routesAvailable.add(new Route("Los Angeles", "Phoenix", "Wild", 3));
            routesAvailable.add(new Route("Miami", "New Orleans", "red", 6));
            routesAvailable.add(new Route("Montreal", "New York", "blue", 3));
            routesAvailable.add(new Route("Montreal", "Toronto", "Wild", 3));
            routesAvailable.add(new Route("Montreal", "Sault St. Marie", "black", 5));
            routesAvailable.add(new Route("Nashville", "Raleigh", "black", 3));
            routesAvailable.add(new Route("Nashville", "Pittsburgh", "yellow", 4));
            routesAvailable.add(new Route("Nashville", "St. Louis", "Wild", 2));
            routesAvailable.add(new Route("New York", "Washington", "black", 2, true));
            routesAvailable.add(new Route("New York", "Washington", "orange", 2, true));
            routesAvailable.add(new Route("New York", "Pittsburgh", "white", 2, true));
            routesAvailable.add(new Route("New York", "Pittsburgh", "green", 2, true));
            routesAvailable.add(new Route("Oklahoma City", "Santa Fe", "blue", 3));
            routesAvailable.add(new Route("Phoenix", "Santa Fe", "Wild", 3));
            routesAvailable.add(new Route("Pittsburgh", "Washington", "Wild", 2));
            routesAvailable.add(new Route("Pittsburgh", "Raleigh", "Wild", 2));
            routesAvailable.add(new Route("Pittsburgh", "St. Louis", "green", 5));
            routesAvailable.add(new Route("Pittsburgh", "Toronto", "Wild", 2));
            routesAvailable.add(new Route("Portland", "Seattle", "Wild", 1, true));
            routesAvailable.add(new Route("Portland", "Seattle", "Wild", 1, true));
            routesAvailable.add(new Route("Portland", "Salt Lake City", "blue", 6));
            routesAvailable.add(new Route("Portland", "San Francisco", "green", 5, true));
            routesAvailable.add(new Route("Portland", "San Francisco", "purple", 5, true));
            routesAvailable.add(new Route("Raleigh", "Washington", "Wild", 2, true));
            routesAvailable.add(new Route("Raleigh", "Washington", "Wild", 2, true));
            routesAvailable.add(new Route("Salt Lake City", "San Francisco", "orange", 5, true));
            routesAvailable.add(new Route("Salt Lake City", "San Francisco", "white", 5, true));
            routesAvailable.add(new Route("Sault St. Marie", "Winnipeg", "Wild", 6));
            routesAvailable.add(new Route("Sault St. Marie", "Toronto", "Wild", 2));
            routesAvailable.add(new Route("Seattle", "Vancouver", "Wild", 1, true));
            routesAvailable.add(new Route("Seattle", "Vancouver", "Wild", 1, true));
            //setDoubles();
        }

    }
}
