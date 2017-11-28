package modeling;

import com.sun.org.apache.regexp.internal.RE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by tyler on 10/22/2017.
 * The class that holds the deck of resource cards
 */

public class ResourceCardList {
    private List<ResourceCard> availableCards = new ArrayList<>();
    private List<ResourceCard> discardPile = new ArrayList<>();
    private ResourceCard[] faceUpPile = new ResourceCard[5];
    private int count;

    public ResourceCardList() {
        count = 0;
        List<String> colorList = new ArrayList<>(Arrays.asList("Red", "Blue", "Orange", "White", "Yellow", "Purple", "Black", "Green"));
        //add the regular resource cards
        List<ResourceCard> resourceCards;
        for (String string : colorList) {
            resourceCards = new ArrayList<>();
            for (int i = 0; i < 1; i++) { //12
            //for (int i = 0; i < 2; i++) {
                ResourceCard card = new ResourceCard(string);
                //add cards to list
                resourceCards.add(card);
                availableCards.add(card);
            }
            //add card list to map
        }
        //add the wild cards
        resourceCards = new ArrayList<>();
        for (int i = 0; i < 8; i++) { //14
            ResourceCard card = new ResourceCard("Wild");
            resourceCards.add(card);
            availableCards.add(card);
        }
        //Sets Unique ID to each card
        for(int i = 0; i < availableCards.size(); ++i) {
            availableCards.get(i).setCardID(i + "");
        }
        //Shuffle the list
        Collections.shuffle(availableCards);
    }

    /**Returns an unmodifiable list of available resource cards to the caller
     * @return List<ResourceCard>*/
    public List<ResourceCard> getAvailableCards() {
        return Collections.unmodifiableList(availableCards);
    }
    //Player draws a card
    public ResourceCard drawCard(String cardID){
        if(availableCards.size() > 0) {
            for(ResourceCard card: availableCards) {
                if(card.getCardID().equals(cardID)){
                    availableCards.remove(card);
                    return card;
                }
            }
            String hi = "";
        }
        return null;
    }

    //When a player claims a route, we need to put the used cards into the discard pile
    public void usedCards(ArrayList<ResourceCard> usedCardsList) {
//        for(ResourceCard card: usedCardsList) {
//            discardPile.add(card);
//        }
        //Just re add the used cards to the bottom of the deck
        for(ResourceCard card: usedCardsList) {
            availableCards.add(card);
        }
    }
    /**Sets up each players starting cards*/
    void setUpPlayers(ArrayList<Player> players) {
        if(count < 2) {
            //forTestingLimitCards();
            for (Player player : players) {
                for(int i = 0; i < 4; i++){
                    //set this card as assigned to the particular player
                    ResourceCard thisCard = availableCards.remove(0);
                    thisCard.setPlayerID(player.getPlayerName());
                    //Now give the card to the player
                    player.addResourceCard(thisCard);
                }
            }
        }
    }
    void forTestingLimitCards() {
        for(int i = 0; i < 90; ++i) {
            availableCards.remove(i);
        }
        availableCards.size();
    }
    public List<ResourceCard> getDiscardPile() {
        return discardPile;
    }

    public void setDiscardPile(List<ResourceCard> discardPile) {
        this.discardPile = discardPile;
    }

    //Ghetto fix so that we don't initialize user multiple times. not too sure why that is happening
    public void incrementCount() {
        count++;
    }
}
