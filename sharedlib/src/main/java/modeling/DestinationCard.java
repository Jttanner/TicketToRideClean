package modeling;

/**
 * Created by tyler on 10/19/2017.
 */

public class DestinationCard {

    private City first;
    private City second;
    private int points;


    public DestinationCard(City first, City second, int points){
        this.first = first;
        this.second = second;
        this.points = points;
    }

    public String getDestinationCardString(){
        return first.getCityName() + " " + second.getCityName();
    }
}
