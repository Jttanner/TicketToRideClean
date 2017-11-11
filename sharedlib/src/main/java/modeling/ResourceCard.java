package modeling;

import java.util.UUID;

/**
 * Created by tyler on 10/17/2017.
 * The class that represents resource cards
 */

public class ResourceCard {
    /**Our color string*/
    private String myColor;
    private String cardID;

    /**Whether this card is face
     * up or not*/
    private boolean isFaceUp;
    /**The owner of this card*/
    private Player player;

    ResourceCard(String myColor) {
        this.myColor = myColor;
        this.cardID = UUID.randomUUID().toString();
    }
    public String getCardID() {
        return cardID;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }
    public ResourceCard(String myColor, boolean yay) {
        this.myColor = myColor;
    }

    public String getMyColor() {
        return myColor;
    }

    public boolean isFaceUp() {
        return isFaceUp;
    }

    public void setFaceUp(boolean faceUp) {
        isFaceUp = faceUp;
    }

    public void setMyColor(String myColor) {
        this.myColor = myColor;
    }

    public Player getPlayer() {
        return player;
    }
    /**Sets the player who owns this card*/
    void setPlayer(Player player) {
        this.player = player;
    }
}
