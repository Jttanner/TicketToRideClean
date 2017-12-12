package clientCommands;

import clientModel.CModel;
import clientModel.MyTurn;
import clientModel.NotMyTurn;
import modeling.Game;
import modeling.Player;

/**
 * Created by ACL1 on 12/12/2017.
 */

public class ReStartClient implements ClientCommand {
    @Override
    public void execute() {
        CModel.getInstance().toggleGameHasStarted();
        Player user = CModel.getInstance().getUserPlayer();
        //find out which state it is
        Game game = CModel.getInstance().getCurrGame();
        for(Player player: game.getPlayers()){
            if(user.getPlayerName().equals(player.getPlayerName())){
                if(player.isMyTurn()){
                    CModel.getInstance().setCurrGameState(new MyTurn());
                }
                else
                    CModel.getInstance().setCurrGameState(new NotMyTurn());
            }
        }
        //update the ui
        CModel.getInstance().notifyObservers(game);
    }
}
