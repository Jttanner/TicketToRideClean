package commandData;

import modeling.Game;

/**
 * Created by korea on 10/28/2017.
 */

public class DrawTrainCardFaceUpCommandData extends Command {
    //Data Members
    private String playerName;
    private String gameID;
    private String resourceCardID;
    private int position;

    //Constructors
    public DrawTrainCardFaceUpCommandData (String playerName, String gameID, String resourceCardID, int position) {
        setType("drawTrainCardFaceUp");
        this.playerName = playerName;
        this.gameID = gameID;
        this.resourceCardID = resourceCardID;
        this.position = position;
    }
    public DrawTrainCardFaceUpCommandData () {}

    //Getters and Setters
    public String getPlayerName() {
        return playerName;
    }
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    public String getGameID() {
        return gameID;
    }
    public void setGameID(String game) {
        this.gameID = game;
    }
    public String getResourceCardID() {
        return resourceCardID;
    }
    public void setResourceCardID(String resourceCardID) {
        this.resourceCardID = resourceCardID;
    }
    public int getPosition() {
        return position;
    }
    public void setPosition(int position) {
        this.position = position;
    }
}
