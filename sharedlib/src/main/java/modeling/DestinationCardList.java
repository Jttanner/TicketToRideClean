package modeling;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahwang13 on 10/21/17.
 */

public class DestinationCardList {

    private List<DestinationCard> destinationCardList;
    private List<DestinationCard> discardPile;

    public DestinationCardList(){
        destinationCardList = new ArrayList<>();


        destinationCardList.add(new DestinationCard(new City("Denver"), new City("El Paso"), 4));
        destinationCardList.add(new DestinationCard(new City("Kansas City"), new City("Houston"), 5));
        destinationCardList.add(new DestinationCard(new City("New York"), new City("Atlanta"), 6));
        destinationCardList.add(new DestinationCard(new City("Chicago"), new City("New Orleans"), 7));
        destinationCardList.add(new DestinationCard(new City("Calgary"), new City("Salt Lake City"), 7));
        destinationCardList.add(new DestinationCard(new City("Helena"), new City("Los Angeles"), 8));
        destinationCardList.add(new DestinationCard(new City("Duluth"), new City("Houston"), 8));
        destinationCardList.add(new DestinationCard(new City("Sault St. Marie"), new City("Nashville"), 8));
        destinationCardList.add(new DestinationCard(new City("Montreal"), new City("Atlanta"), 9));
        destinationCardList.add(new DestinationCard(new City("Sault St. Marie"), new City("Oklahoma City"), 9));
        destinationCardList.add(new DestinationCard(new City("Seattle"), new City("Los Angeles"), 9));
        destinationCardList.add(new DestinationCard(new City("Chicago"), new City("Santa Fe"), 9));
        destinationCardList.add(new DestinationCard(new City("Duluth"), new City("El Paso"), 10));
        destinationCardList.add(new DestinationCard(new City("Toronto"), new City("Miami"), 10));
        destinationCardList.add(new DestinationCard(new City("Portland"), new City("Phoenix"), 11));
        destinationCardList.add(new DestinationCard(new City("Dallas"), new City("New York"), 11));
        destinationCardList.add(new DestinationCard(new City("Denver"), new City("Pittsburgh"), 11));
        destinationCardList.add(new DestinationCard(new City("Winnipeg"), new City("Little Rock"), 11));
        destinationCardList.add(new DestinationCard(new City("Winnipeg"), new City("Houston"), 12));
        destinationCardList.add(new DestinationCard(new City("Boston"), new City("Miami"), 12));
        destinationCardList.add(new DestinationCard(new City("Vancouver"), new City("Santa Fe"), 13));
        destinationCardList.add(new DestinationCard(new City("Calgary"), new City("Phoenix"), 13));
        destinationCardList.add(new DestinationCard(new City("Montreal"), new City("New Orleans"), 13));
        destinationCardList.add(new DestinationCard(new City("Los Angeles"), new City("Chicago"), 16));
        destinationCardList.add(new DestinationCard(new City("San Francisco"), new City("Atlanta"), 17));
        destinationCardList.add(new DestinationCard(new City("Portland"), new City("Nashville"), 17));
        destinationCardList.add(new DestinationCard(new City("Vancouver"), new City("Montreal"), 20));
        destinationCardList.add(new DestinationCard(new City("Los Angeles"), new City("Miami"), 20));
        destinationCardList.add(new DestinationCard(new City("Los Angeles"), new City("New York"), 21));
        destinationCardList.add(new DestinationCard(new City("Seattle"), new City("New York"), 22));

    }

}
