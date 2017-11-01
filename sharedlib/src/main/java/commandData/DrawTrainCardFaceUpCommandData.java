package commandData;

import modeling.Game;

/**
 * Created by korea on 10/28/2017.
 */

public class DrawTrainCardFaceUpCommandData extends Command {
    //Data Members
    private String game;
    private String resourceCardColor;

    //Constructors
    public DrawTrainCardFaceUpCommandData (String game, String resourceCardColor) {
        setType("drawTrainCardFaceUp");
        this.game = game;
        this.resourceCardColor = resourceCardColor;
    }
    public DrawTrainCardFaceUpCommandData () {}

    //Getters and Setters
    public String getGame() {
        return game;
    }
    public void setGame(String game) {
        this.game = game;
    }
    public String getResourceCardColor() {
        return resourceCardColor;
    }
    public void setResourceCardColor(String resourceCardColor) {
        this.resourceCardColor = resourceCardColor;
    }
}
