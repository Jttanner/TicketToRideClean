package clientCommands;

import clientModel.CModel;
import modeling.Game;

/**
 * Created by ACL1 on 11/20/2017.
 */

public class EndGameCommand implements ClientCommand {
    @Override
    public void execute() {
       CModel.getInstance().EndGame();
    }
}
