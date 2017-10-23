package modeling;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ahwang13 on 10/21/17.
 */

public class DestinationCardList {

    private List<DestinationCard> destinationCardList;
    private List<DestinationCard> discardPile;
    private CityList cityList = new CityList();
    private Map<String, City> cityMap = cityList.getCityListMap();

    public DestinationCardList(){
        destinationCardList.add(new DestinationCard(cityMap.get("Denver"), cityMap.get("ERl Paso"), 4));
        destinationCardList.add(new DestinationCard(cityMap.get("Kansas City"), cityMap.get("Houston"), 5));
        destinationCardList.add(new DestinationCard(cityMap.get("New York"), cityMap.get("Atlanta"), 6));
        destinationCardList.add(new DestinationCard(cityMap.get("Chicago"), cityMap.get("New Orleans"), 7));
        destinationCardList.add(new DestinationCard(cityMap.get("Calgary"), cityMap.get("Salt Lake City"), 7));
        destinationCardList.add(new DestinationCard(cityMap.get("Helena"), cityMap.get("Los Angeles"), 8));
        destinationCardList.add(new DestinationCard(cityMap.get("Duluth"), cityMap.get("Houston"), 8));
        destinationCardList.add(new DestinationCard(cityMap.get("Sault St. Marie"), cityMap.get("Nashville"), 8));
        destinationCardList.add(new DestinationCard(cityMap.get("Montreal"), cityMap.get("Atlanta"), 9));
        destinationCardList.add(new DestinationCard(cityMap.get("Sault St. Marie"), cityMap.get("Oklahoma City"), 9));
        destinationCardList.add(new DestinationCard(cityMap.get("Seattle"), cityMap.get("Los Angeles"), 9));
        destinationCardList.add(new DestinationCard(cityMap.get("Chicago"), cityMap.get("Santa Fe"), 9));
        destinationCardList.add(new DestinationCard(cityMap.get("Duluth"), cityMap.get("El Paso"), 10));
        destinationCardList.add(new DestinationCard(cityMap.get("Toronto"), cityMap.get("Miami"), 10));
        destinationCardList.add(new DestinationCard(cityMap.get("Portland"), cityMap.get("Phoenix"), 11));
        destinationCardList.add(new DestinationCard(cityMap.get("Dallas"), cityMap.get("New York"), 11));
        destinationCardList.add(new DestinationCard(cityMap.get("Denver"), cityMap.get("Pittsburgh"), 11));
        destinationCardList.add(new DestinationCard(cityMap.get("Winnipeg"), cityMap.get("Little Rock"), 11));
        destinationCardList.add(new DestinationCard(cityMap.get("Winnipeg"), cityMap.get("Houston"), 12));
        destinationCardList.add(new DestinationCard(cityMap.get("Boston"), cityMap.get("Miami"), 12));
        destinationCardList.add(new DestinationCard(cityMap.get("Vancouver"), cityMap.get("Santa Fe"), 13));
        destinationCardList.add(new DestinationCard(cityMap.get("Calgary"), cityMap.get("Phoenix"), 13));
        destinationCardList.add(new DestinationCard(cityMap.get("Montreal"), cityMap.get("New Orleans"), 13));
        destinationCardList.add(new DestinationCard(cityMap.get("Los Angeles"), cityMap.get("Chicago"), 16));
        destinationCardList.add(new DestinationCard(cityMap.get("San Francisco"), cityMap.get("Atlanta"), 17));
        destinationCardList.add(new DestinationCard(cityMap.get("Portland"), cityMap.get("Nashville"), 17));
        destinationCardList.add(new DestinationCard(cityMap.get("Vancouver"), cityMap.get("Montreal"), 20));
        destinationCardList.add(new DestinationCard(cityMap.get("Los Angeles"), cityMap.get("Miami"), 20));
        destinationCardList.add(new DestinationCard(cityMap.get("Los Angeles"), cityMap.get("New York"), 21));
        destinationCardList.add(new DestinationCard(cityMap.get("Seattle"), cityMap.get("New York"), 22));

    }

}
