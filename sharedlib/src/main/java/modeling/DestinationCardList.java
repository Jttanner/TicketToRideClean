package modeling;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Accesses the list of available Destination Cards to be drawn from the deck. Also allows for the selection of random cards from the deck
 * and the replacement of cards into the deck that the player doesn't want.
 * Created by Austin Hwang on 10/21/17.
 */

public class DestinationCardList {

    private List<DestinationCard> destinationCardList = new ArrayList<>();
    private List<DestinationCard> discardPile = new ArrayList<>();
    private CityList cityList = new CityList();
    private Map<String, City> cityMap = cityList.getCityListMap();

    /**
     * Initializes the destination card deck by placing all possible destination cards in the list.
     */
    public DestinationCardList(){
        destinationCardList.add(new DestinationCard(cityMap.get("Denver"), cityMap.get("El Paso"), 4));
        destinationCardList.add(new DestinationCard(cityMap.get("Kansas City"), cityMap.get("Houston"), 5));
        destinationCardList.add(new DestinationCard(cityMap.get("New York City"), cityMap.get("Atlanta"), 6));
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
        destinationCardList.add(new DestinationCard(cityMap.get("Dallas"), cityMap.get("New York City"), 11));
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
        destinationCardList.add(new DestinationCard(cityMap.get("Los Angeles"), cityMap.get("New York City"), 21));
        destinationCardList.add(new DestinationCard(cityMap.get("Seattle"), cityMap.get("New York City"), 22));

    }


    /**
     * Takes the already initialized list of Destination Cards and draws 3 random cards from the deck
     * @return A list of 3 destination cards from the deck.
     */
//    List<DestinationCard> getDestinationCards() {
//        return destinationCardList.get3Cards();
//    }

    public void addDestinationCardBackToDeck (DestinationCard card) {
        destinationCardList.add(card);
    }

    public void removeDestinationCards (List<DestinationCard> cards) {
        for (int i = 0; i < cards.size(); i++) {
            for (int j = 0; j < destinationCardList.size(); j++) {
                if(cards.get(i).getDestinationCardString().equals(destinationCardList.get(j).getDestinationCardString())) {
                    destinationCardList.remove(j);
                }
            }
        }
    }

    public void removeDestinationCard (DestinationCard card) {
        for (int i = 0; i < destinationCardList.size(); i++) {
            if (card.getDestinationCardString().equals(destinationCardList.get(i).getDestinationCardString())) {
                destinationCardList.remove(i);
            }
        }
    }

//    public List<DestinationCard> distributeUsedDestinationCards(ClaimDestinationCardCommandData commandData) {
//        List<DestinationCard> claimedCards = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            boolean isClaimed = commandData.getClaimDestinationCards().get(i).isClaimed();
//            if (isClaimed) {
//                claimedCards.add(commandData.getClaimDestinationCards().get(i));
//            } else {
//                destinationCardList.getDestinationCardList().add(commandData.getClaimDestinationCards().get(i));
//            }
//        }
//        String playerID = commandData.getPlayerID();
//        Game currGame = gameList.findGame(commandData.getGameID());
//        Player currPlayer = currGame.getPlayer(playerID);
//        currPlayer.addDestinationCard(claimedCards);
//        return claimedCards;
//    }

    public List<DestinationCard> get3Cards() {
        List<DestinationCard> threeCards = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            DestinationCard card = get1RandomCard();
            threeCards.add(card);
        }
        return threeCards;
    }

    /**
     * Takes the already initialized list of Destination Cards and draws 1 random card from the deck
     * @param What's being passed in
     * @pre What has to be true
     * @post A random Destination Card from the deck.
     * @return DestinationCard
     */
    public DestinationCard get1RandomCard() {
        //TODO: Make the check for if destinationCardList is empty or almost empty
        if (destinationCardList.size() == 0) {
            return null;
        }
        int min = 0;
        int max = destinationCardList.size() - 1;
        int randomIndex = randInt(min, max);
        DestinationCard saved = destinationCardList.get(randomIndex);
        destinationCardList.remove(randomIndex);
        System.out.println("Current size of the deck: " + destinationCardList.size());
        return saved;
    }

    /**
     *
     * @param min The minimum number for the random generator
     * @param max The maximum number for the random generator
     * @return A random number between the min and max.
     */
    public static int randInt(int min, int max) {
        Random rand = new Random();

        int randomNum = rand.nextInt((max-min) + 1) + min;

        return randomNum;
    }

    public List<DestinationCard> getDestinationCardList() {
        return destinationCardList;
    }
}
