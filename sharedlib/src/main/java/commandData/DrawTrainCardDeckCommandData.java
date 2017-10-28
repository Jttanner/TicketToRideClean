package commandData;

import modeling.Game;

/**
 * Created by korea on 10/28/2017.
 */

public class DrawTrainCardDeckCommandData extends Command {
    //Data Members
    private Game game;
    private String resourceCardColor;

    //Constructor
    public DrawTrainCardDeckCommandData (Game game, String resourceCardColor) {
        setType("drawTrainCardDeck");
        this.game = game;
        this.resourceCardColor = resourceCardColor;
    }
    public DrawTrainCardDeckCommandData () {}

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
