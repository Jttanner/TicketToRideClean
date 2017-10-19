package modeling;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by jontt on 10/19/2017.
 */

public class City {
    public City(String cityName){
        this.cityName = cityName;
        //this.coords = coords;
    }

    private String cityName;
    //private Point coords;


    //TODO: double check type for this collection.  is Set okay?
    Set<Route> routes = new HashSet<>();

    public void addRoute(Route route){
        routes.add(route);
    }

    public String getCityName() {
        return cityName;
    }

    /*public int getX_coord() {
        return coords.x;
    }

    public int getY_coord() {
        return coords.y;
    }*/

    public Set<Route> getRoutes() {
        return routes;
    }

    @Override
    public String toString() {
        return cityName;
    }

}
