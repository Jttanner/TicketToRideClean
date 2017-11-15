package modeling;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tyler on 10/17/2017.
 */

public class Route {
    public Route(City first, City second, String trainColorNeeded, int distance){
        firstCity = first;
        secondCity = second;
        /*twoCities.add(first);
        twoCities.add(second);*/
        this.distance = distance;
        this.trainColorNeeded = trainColorNeeded;
    }

    public Route(String firstCityName, String secondCityName, String trainColorNeeded, int distance){
        firstCity = new City(firstCityName);
        secondCity = new City(secondCityName);
        /*this.firstCityName = firstCityName;
        this.secondCityName = secondCityName;*/
        this.trainColorNeeded = trainColorNeeded;
        this.distance = distance;
    }

    public Route(String firstCityName, String secondCityName, String trainColorNeeded, int distance, boolean isDouble){
        firstCity = new City(firstCityName);
        secondCity = new City(secondCityName);
        /*this.firstCityName = firstCityName;
        this.secondCityName = secondCityName;*/
        this.trainColorNeeded = trainColorNeeded;
        this.distance = distance;
        this.isDouble = isDouble;
    }

    /*String firstCityName;
    String secondCityName;*/

    boolean firstOfDouble = true;


    public void setFirstOfDouble(boolean firstOfDouble){
        this.firstOfDouble = firstOfDouble;
    }

    public boolean getFirstOfDouble(){
        return  firstOfDouble;
    }

    public String getFirstCityName() {
        return firstCity.getCityName();
    }

    public String getSecondCityName() {
        return secondCity.getCityName();
    }

    /*private List<City> twoCities = new ArrayList<>();*/
    private int distance;
    private boolean claimed = false;
    private boolean isDouble = false;
    private String trainColorNeeded;
    private Player owner = null;

    private City firstCity;

    private City secondCity;

    public City getFirstCity() {
        return firstCity;
    }

    public City getSecondCity() {
        return secondCity;
    }

    public void setIsDouble(){
        isDouble = true;
    }

    public boolean getIsDouble(){
        return isDouble;
    }

    /*//TODO: It is less good to pass the list directly.  should we change it?
    public List<City> getTwoCities(){
        return  twoCities;
    }*/

    public int getPointValue(){
        int points = 0;
        switch(distance) {
            case 1:
                points = 1;
                break;
            case 2:
                points = 2;
                break;
            case 3:
                points = 4;
                break;
            case 4:
                points = 7;
                break;
            case 5:
                points = 10;
                break;
            case 6:
                points = 15;
                break;
        }
        return points;
    }

    boolean isClaimed(){
        return  claimed;
    }

    boolean setOwner(Player player){
        owner = player;
        return true;
    }

    Player getOwner(){
        return owner;
    }

    boolean claimRoute(){
        if (!claimed){
            claimed = true;
            return  true;
        } else{
            return false;
        }
    }

    public String getTrainColorNeeded(){
        return  trainColorNeeded;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public boolean equals(Object object) {
        Route route = null;
        if (object instanceof Route){
            route = (Route) object;
        } else{
            return  false;
        }
        if (this.getFirstCityName().toLowerCase().equals(route.getFirstCityName().toLowerCase()) && this.getSecondCityName().toLowerCase().equals(route.getSecondCityName().toLowerCase())){
            return true;
        } else if (this.getFirstCityName().toLowerCase().equals(route.getSecondCityName().toLowerCase()) && this.getSecondCityName().toLowerCase().equals(route.getFirstCityName().toLowerCase())){
            return true;
        } else{
            return false;
        }
    }



}
