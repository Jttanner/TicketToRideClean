package commandData;

import modeling.Game;

/**
 * Created by korea on 10/28/2017.
 */

public class DrawTrainCardDeckCommandData extends Command {

    //Data Members
    private String game;
    private String resourceCardColor;

    private String playerName;
    //Constructor
    public DrawTrainCardDeckCommandData (String game, String resourceCardColor, String playerName) {
        setType("drawTrainCardDeck");
        this.game = game;
        this.resourceCardColor = resourceCardColor;
        this.playerName = playerName;
    }
    public DrawTrainCardDeckCommandData () {}

    //Getters and Setters
    public String getPlayerName() {
        return playerName;
    }
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
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
