package ServerModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Hwang on 10/23/2017.
 */

public class CityList {
    private List<City> cities = new ArrayList<>();
    private Map<String, City> cityListMap = new HashMap();

    private void fillMap(){
        for (City city : cities) {
            cityListMap.put(city.getCityName(), city);
        }
    }

    public CityList() {
        cities.add(new City("Atlanta"));
        cities.add(new City("Boston"));
        cities.add(new City("Calgary"));
        cities.add(new City("Charleston"));
        cities.add(new City("Chicago"));
        cities.add(new City("Dallas"));
        cities.add(new City("Denver"));
        cities.add(new City("Duluth"));
        cities.add(new City("El Paso"));
        cities.add(new City("Helena"));
        cities.add(new City("Houston"));
        cities.add(new City("Kansas ServerModel.City"));
        cities.add(new City("Las Vegas"));
        cities.add(new City("Little Rock"));
        cities.add(new City("Los Angeles"));
        cities.add(new City("Miami"));
        cities.add(new City("Montreal"));
        cities.add(new City("Nashville"));
        cities.add(new City("New Orleans"));
        cities.add(new City("New York ServerModel.City"));
        cities.add(new City("Oklahoma ServerModel.City"));
        cities.add(new City("Omaha"));
        cities.add(new City("Phoenix"));
        cities.add(new City("Pittsburgh"));
        cities.add(new City("Portland"));
        cities.add(new City("Raleigh"));
        cities.add(new City("St. Louis"));
        cities.add(new City("Salt Lake ServerModel.City"));
        cities.add(new City("San Francisco"));
        cities.add(new City("Santa Fe"));
        cities.add(new City("Sault St. Marie"));
        cities.add(new City("Seattle"));
        cities.add(new City("Toronto"));
        cities.add(new City("Vancouver"));
        cities.add(new City("Washington"));
        cities.add(new City("Winnipeg"));

        fillMap();
    }

    public Map<String, City> getCityListMap() {
        return cityListMap;
    }

    public List<City> getCities() {
        return cities;
    }
}
