package clientCommands;

import java.util.List;

import clientModel.CModel;
import clientModel.EndMyTurn;
import commandData.ClaimDestinationCardCommandData;
import modeling.DestinationCard;

/**
 * Created by korea on 10/27/2017.
 */

public class ClaimDestinationCards implements ClientCommand {
    //Need Player Name
    //How many he decided to keep
    String playerName;
    //int numberOfCards;
    String gameID;
    List<DestinationCard> cardsClaimed;

    public ClaimDestinationCards(ClaimDestinationCardCommandData data) {
        this.playerName = data.getPlayerID();
        this.gameID = data.getGameID();
        this.cardsClaimed = data.getClaimDestinationCards();
    }

    @Override
    public void execute() {

        CModel.getInstance().updateCurrGameHistoryList(this.toString(),CModel.getInstance().getCurrGame().getGameID());
        for (int i = 0; i < cardsClaimed.size(); i++) {
            if(cardsClaimed.get(i).isClaimed()) {
                CModel.getInstance().getCurrGame().getPlayer(playerName).addDestinationCard(cardsClaimed.get(i));
                CModel.getInstance().getCurrGame().getDestinationCardList().removeDestinationCard(cardsClaimed.get(i));
            }
        }
        CModel.getInstance().updatePlayerStatsView();


        CModel.getInstance().setCurrGameState(new EndMyTurn());
        //ends my turn
        CModel.getInstance().getCurrGameState().endTurn();
        //CModel.getInstance().updatePlayerStatsView();

    }

    public int cardsTaken() {
        int counter = 0;
        for (int i = 0; i < cardsClaimed.size(); i++) {
            if (cardsClaimed.get(i).isClaimed() == true) {
                counter++;
            }
        }
        return counter;
    }

    @Override
    public String toString() {
        int numberOfCards = cardsTaken();
        return  playerName + " took " + numberOfCards + " destination cards";
    }
}
