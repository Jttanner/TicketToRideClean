package clientCommands;

import clientModel.CModel;
import commandData.DrawTrainCardDeckCommandData;
import modeling.Game;
import modeling.Player;
import modeling.ResourceCard;

/**
 * Created by korea on 10/27/2017.
 */

public class DrawTrainCardDeck implements ClientCommand {
    private String playerName;
    private String resourceCardColor;
    private String gameID;


    public DrawTrainCardDeck (DrawTrainCardDeckCommandData data) {
        resourceCardColor = data.getResourceCardColor();
        gameID = data.getGame();
        playerName = data.getPlayerName();

    }
    @Override
    public void execute() {
        //Find the right game
        Game updatedGame = null;
        for(Game tempGame: CModel.getInstance().getAllGames())
        {
            if(tempGame.getGameID().equals(gameID)){
                updatedGame = tempGame;
            }
        }
        //Create the resource card
        ResourceCard card = new ResourceCard(resourceCardColor, true);
        //Update the game
        updatedGame.getPlayer(playerName).addResourceCard(card);

        CModel.getInstance().updatePlayerStatsView(updatedGame);
        CModel.getInstance().updateCurrGameHistoryList(this.toString(), gameID);
    }

    @Override
    public String toString() {
        return playerName + " drew a resource card from the deck: " + resourceCardColor;
    }
}
