package modeling;

/**
 * Created by tyler on 10/17/2017.
 * The class that represents resource cards
 */

public class ResourceCard {
    /**Our color string*/
    private String myColor;
    /**Whether this card is face
     * up or not*/
    private boolean isFaceUp;
    /**The owner of this card*/
    private Player player;

    public ResourceCard(String myColor) {
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

    public void setPlayer(Player player) {
        this.player = player;
    }
}
