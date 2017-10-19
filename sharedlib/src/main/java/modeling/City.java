package modeling;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by jontt on 10/19/2017.
 */

public class City {
    public City(String cityName, int x_coord, int y_coord){
        this.cityName = cityName;
        this.x_coord = x_coord;
        this.y_coord = y_coord;
    }

    String cityName;
    int x_coord;
    int y_coord;


    //TODO: double check type for this collection.  is Set okay?
    Set<Route> routes = new HashSet<>();

    public void addRoute(Route route){
        routes.add(route);
    }

}
