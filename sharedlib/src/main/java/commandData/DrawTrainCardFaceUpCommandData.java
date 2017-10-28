package commandData;

import modeling.Game;

/**
 * Created by korea on 10/28/2017.
 */

public class DrawTrainCardFaceUpCommandData extends Command {
    //Data Members
    private Game game;
    private String resourceCardColor;

    //Constructors
    public DrawTrainCardFaceUpCommandData (Game game, String resourceCardColor) {
        setType("drawTrainCardFaceUp");
        this.game = game;
        this.resourceCardColor = resourceCardColor;
    }
    public DrawTrainCardFaceUpCommandData () {}

    //Getters and Setters
    public Game getGame() {
        return game;
    }
    public void setGame(Game game) {
        this.game = game;
    }
    public String getResourceCardColor() {
        return resourceCardColor;
    }
    public void setResourceCardColor(String resourceCardColor) {
        this.resourceCardColor = resourceCardColor;
    }
}
