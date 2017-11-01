package modeling;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tyler on 10/22/2017.
 * The class that holds the deck of resource cards
 */

public class ResourceCardList {
    private Map<String, List<ResourceCard>> colorToCardMap = new HashMap<>();
    private List<ResourceCard> availableCards = new ArrayList<>();

    public ResourceCardList() {
        List<String> colorList = new ArrayList<>(Arrays.asList("Red", "Blue", "Orange", "White", "Yellow", "Purple", "Black", "Green"));
        //add the regular resource cards
        List<ResourceCard> resourceCards;
        for (String string : colorList) {
            resourceCards = new ArrayList<>();
            for (int i = 0; i < 12; i++) {
                ResourceCard card = new ResourceCard(string);
                //add cards to list
                resourceCards.add(card);
                availableCards.add(card);
            }
            //add card list to map
            colorToCardMap.put(string, resourceCards);
        }
        //add the wild cards
        resourceCards = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            ResourceCard card = new ResourceCard("Wild");
            resourceCards.add(card);
            availableCards.add(card);
        }
        colorToCardMap.put("Wild",resourceCards);
        //Shuffle the list
        Collections.shuffle(availableCards);
    }

    public Map<String, List<ResourceCard>> getColorToCardMap() {
        return colorToCardMap;
    }

    /**Returns an unmodifiable list of available resource cards to the caller
     * @return List<ResourceCard>*/
    public List<ResourceCard> getAvailableCards() {
        return Collections.unmodifiableList(availableCards);
    }

    public ResourceCard drawCard(){
        for (ResourceCard card : availableCards){
            availableCards.remove(card);
            return card;
        }
        return null;
    }

    /**Sets up each players starting cards*/
    void setUpPlayers(ArrayList<Player> players) {
        int start = 0;//start at index zero in the card list
        for (Player player : players) {

            //start them with 4 train cards
            int end = start+4;//makes sure every player gets four cards only
            //For a full 5 players we will end up going through 20 cards being assigned out
            for(int i = start; i < end; i++){
                //set this card as assigned to the particular player
                ResourceCard thisCard = availableCards.get(i);
                thisCard.setPlayer(player);
                //Now give the card to the player
                player.addResourceCard(availableCards.get(i));
                //remove this card from the resource card deck, now it is just held in the map
                availableCards.remove(thisCard);
            }

        }
    }
}
