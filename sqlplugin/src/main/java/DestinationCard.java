/**
 * Created by tyler on 10/19/2017.
 */

public class DestinationCard {

    private City first;
    private City second;
    private int points;
    private boolean claimed = false;


    public City getFirst() {
        return first;
    }

    public City getSecond() {
        return second;
    }

    public int getPoints() {
        return points;
    }

    public DestinationCard(City first, City second, int points){
        this.first = first;
        this.second = second;
        this.points = points;
    }

    public String getDestinationCardString(){
        return first.getCityName() + " " + second.getCityName() + " " + points;
    }

    public boolean isClaimed() {
        return claimed;
    }

    public void setClaimed(boolean claimed) {
        this.claimed = claimed;
    }
}
