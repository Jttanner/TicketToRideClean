package modeling;

/**
 * Created by tyler on 10/19/2017.
 */

public class DestinationCard {

    private City first;
    private City second;
    private int points;
    private boolean claimed = false;


    public DestinationCard(City first, City second, int points){
        this.first = first;
        this.second = second;
        this.points = points;
    }

    public String getDestinationCardString(){
        return first.getCityName() + " " + second.getCityName();
    }

    public boolean isClaimed() {
        return claimed;
    }

    public void setClaimed(boolean claimed) {
        this.claimed = claimed;
    }
}
