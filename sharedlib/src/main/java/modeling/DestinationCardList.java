package modeling;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahwang13 on 10/21/17.
 */

public class DestinationCardList {

    private List<DestinationCard> destinationCardList;

    public DestinationCardList(){
        destinationCardList = new ArrayList<>();

        destinationCardList.add(new DestinationCard("Denver", "El Paso", 4));
        destinationCardList.add(new DestinationCard("Kansas City", "Houston", 5));
        destinationCardList.add(new DestinationCard("New York", "Atlanta", 6));
        destinationCardList.add(new DestinationCard("Chicago", "New Orleans", 7));
        destinationCardList.add(new DestinationCard("Calgary", "Salt Lake City", 7));
        destinationCardList.add(new DestinationCard("Helena", "Los Angeles", 8));
        destinationCardList.add(new DestinationCard("Duluth", "Houston", 8));
        destinationCardList.add(new DestinationCard("Sault St. Marie", "Nashville", 8));
        destinationCardList.add(new DestinationCard("Montreal", "Atlanta", 9));
        destinationCardList.add(new DestinationCard("Sault St. Marie", "Oklahoma City", 9));
        destinationCardList.add(new DestinationCard("Seattle", "Los Angeles", 9));
        destinationCardList.add(new DestinationCard("Chicago", "Santa Fe", 9));
        destinationCardList.add(new DestinationCard("Duluth", "El Paso", 10));
        destinationCardList.add(new DestinationCard("Toronto", "Miami", 10));
        destinationCardList.add(new DestinationCard("Portland", "Phoenix", 11));
        destinationCardList.add(new DestinationCard("Dallas", "New York", 11));
        destinationCardList.add(new DestinationCard("Denver", "Pittsburgh", 11));
        destinationCardList.add(new DestinationCard("Winnipeg", "Little Rock", 11));
        destinationCardList.add(new DestinationCard("Winnipeg", "Houston", 12));
        destinationCardList.add(new DestinationCard("Boston", "Miami", 12));
        destinationCardList.add(new DestinationCard("Vancouver", "Santa Fe", 13));
        destinationCardList.add(new DestinationCard("Calgary", "Phoenix", 13));
        destinationCardList.add(new DestinationCard("Montreal", "New Orleans", 13));
        destinationCardList.add(new DestinationCard("Los Angeles", "Chicago", 16));
        destinationCardList.add(new DestinationCard("San Francisco", "Atlanta", 17));
        destinationCardList.add(new DestinationCard("Portland", "Nashville", 17));
        destinationCardList.add(new DestinationCard("Vancouver", "Montreal", 20));
        destinationCardList.add(new DestinationCard("Los Angeles", "Miami", 20));
        destinationCardList.add(new DestinationCard("Los Angeles", "New York", 21));
        destinationCardList.add(new DestinationCard("Seattle", "New York", 22));

    }

}
