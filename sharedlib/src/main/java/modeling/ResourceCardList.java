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
        }
        //add the wild cards
        resourceCards = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
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

        //Maybe not use this
        //setUpFaceUpPile();
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
//        for (ResourceCard card : availableCards){
//            availableCards.remove(card);
//            return card;
//        }
        return null;
    }
    //When our available cards is empty, we need to reshuffle the discard pile.
    public void shuffleDiscardPile() {
        if(availableCards.size() == 0 && discardPile.size() != 0) {
            Collections.shuffle(discardPile);
            for(ResourceCard card: discardPile) {
                availableCards.add(card);
            }
        }
    }
    //When a player claims a route, we need to put the used cards into the discard pile
    public void usedCards(ArrayList<ResourceCard> usedCardsList) {
        for(ResourceCard card: usedCardsList) {
            discardPile.add(card);
        }
    }
//    public void setCardInFaceUpPile(ResourceCard card, int position) {
//        faceUpPile[position] = card;
//    }
//    public ResourceCard getFaceUpCard(int position) {
//        return faceUpPile[position];
//    }
//    public ResourceCard findCardByID(String ID) {
//        ResourceCard result = new ResourceCard("STRING SHOULD NEVER HAVE THIS, MUAHAHAHAHA...SOMETHING WENT WRONG.");
//        for(ResourceCard card: availableCards) {
//            if(card.getCardID().equals(ID)) {
//                result = card;
//                break;
//            }
//        }
//        return result;
//    }
//    public void upDateFaceUpPile(int position) {
//        faceUpPile[position] = drawCard();
//    }
//    When the game is created and the resource cards is initialized, it will automatically take the
//    top 5 cards and put them in the face up pile
//    public void setUpFaceUpPile() {
//        for(int i = 0; i < 5; ++i) {
//            faceUpPile[i] = drawCard();
//        }
//    }
    /**Sets up each players starting cards*/
    void setUpPlayers(ArrayList<Player> players) {
        int start = 0;//start at index zero in the card list
        for (Player player : players) {

            //start them with 4 train cards
            //int end = start+4;//makes sure every player gets four cards only
            //For a full 5 players we will end up going through 20 cards being assigned out
//            for(int i = start; i < end; i++){
            for(int i = start; i < 4; i++){
                //set this card as assigned to the particular player
                ResourceCard thisCard = availableCards.remove(0);
                thisCard.setPlayerID(player.getPlayerName());
                //Now give the card to the player
                player.addResourceCard(availableCards.get(i));
                //remove this card from the resource card deck, now it is just held in the map

            }

        }
    }
}
