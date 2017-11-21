package clientCommands;

import java.util.List;

import clientModel.CModel;
import clientModel.MyTurn;
import clientModel.NotMyTurn;
import clientModel.StartGame;
import modeling.Player;

/**
 * Created by tyler on 10/24/2017.
 */

public class StartGameClient implements ClientCommand {
    @Override
    public void execute() {
        CModel cModel = CModel.getInstance();
        cModel.toggleGameHasStarted();
        cModel.setCurrGameState(new StartGame());
        //StartGame startGame = new StartGame();
        //CModel.getInstance().setCurrGameState(startGame);
        //startGame.getDestCard();
        //startGame.claimDestCard();

        /*
        for(Player player : cModel.getCurrGame().getPlayers()){

           if(player.getUserName().equals(cModel.getUserPlayer().getUserName())){
                if(player.isMyTurn()){
                    cModel.setCurrGameState(new MyTurn());
                }
                else{
                    cModel.setCurrGameState(new NotMyTurn());
                }
           }
        }*/
    }
}
