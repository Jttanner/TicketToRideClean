package clientCommands;

import java.util.List;

import clientModel.CModel;
import clientModel.MyTurn;
import clientModel.NotMyTurn;
import commandData.ClaimInitialDestinationCardCommandData;
import modeling.DestinationCard;
import modeling.Player;

/**
 * Created by ahwang13 on 11/21/17.
 */

public class InitializeTurns implements ClientCommand {

    String playerName;
    //int numberOfCards;
    String gameID;
    List<DestinationCard> cardsClaimed;

    public InitializeTurns(ClaimInitialDestinationCardCommandData data) {
        this.playerName = data.getPlayerID();
        this.gameID = data.getGameID();
        this.cardsClaimed = data.getClaimDestinationCards();
    }

    @Override
    public void execute() {
        CModel cModel = CModel.getInstance();
        cModel.updatePlayerStatsView();

        CModel.getInstance().updateCurrGameHistoryList(this.toString(),CModel.getInstance().getCurrGame().getGameID());

        //if (cModel.getCurrGame().getPlayers().size() != cModel.getCurrGame().getPlayersHaveSelectedInitialDestCards()) {
            for (int i = 0; i < cardsClaimed.size(); i++) {
                if(cardsClaimed.get(i).isClaimed() == true) {
                    CModel.getInstance().getCurrGame().getPlayer(playerName).addDestinationCard(cardsClaimed.get(i));
                    CModel.getInstance().getCurrGame().getDestinationCardList().removeDestinationCard(cardsClaimed.get(i));
                }
                CModel.getInstance().updatePlayerStatsView();
            }
            cModel.getCurrGame().incrementPlayersHaveSelectedInitialDestCards();
        //}
        if (cModel.getCurrGame().getPlayers().size() == cModel.getCurrGame().getPlayersHaveSelectedInitialDestCards()) {
            for (Player player : cModel.getCurrGame().getPlayers()) {
                if (player.getUserName().equals(cModel.getUserPlayer().getUserName())) {
                    if (player.isMyTurn()) {
                        cModel.setCurrGameState(new MyTurn());
                    }
                    else {
                        cModel.setCurrGameState(new NotMyTurn());
                    }
                }
            }
        }
    }
}
