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

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (!(o instanceof City)) {
            return false;
        }
        City oCity = (City) o;
        //All instance variables must match to return true
        return this.getCityName().equals(oCity.getCityName());
    }

    /**If this city has been visited by the longest path calculator*/
    private boolean visited = false;


    /*
    Set<Route> routes = new HashSet<>();

    public void addRoute(Route route){
        routes.add(route);
    }*/

    public String getCityName() {
        return cityName;
    }

    /*public int getX_coord() {
        return coords.x;
    }

    public int getY_coord() {
        return coords.y;
    }*/

    /*public Set<Route> getRoutes() {
        return routes;
    }*/

    @Override
    public String toString() {
        return cityName;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

}
