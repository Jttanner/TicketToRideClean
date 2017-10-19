package modeling;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tyler on 10/17/2017.
 */

public class Route {
    public Route(City first, City second, int distance, String trainColorNeeded){
        twoCities.add(first);
        twoCities.add(second);
        this.distance = distance;
        this.trainColorNeeded = trainColorNeeded;
    }

    List<City> twoCities = new ArrayList<>();
    int distance;
    boolean claimed = false;
    String trainColorNeeded;
    Player owner = null;

    //TODO: It is less good to pass the list directly.  should we change it?
    List<City> getTwoCities(){
        return  twoCities;
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

    String getTrainColorNeeded(){
        return  trainColorNeeded;
    }

}
