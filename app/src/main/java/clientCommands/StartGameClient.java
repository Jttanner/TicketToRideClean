package clientCommands;

import java.util.List;

import clientModel.CModel;
import modeling.Player;

/**
 * Created by tyler on 10/24/2017.
 */

public class StartGameClient implements ClientCommand {
    @Override
    public void execute() {
        CModel cModel = CModel.getInstance();
        cModel.toggleGameHasStarted();
        /*List<Player> playerList = cModel.getCurrGame().getPlayers();
        for(Player player : playerList){

           if(cModel.getUserPlayer().isMyTurn()){

           }
        }*/
    }
}
