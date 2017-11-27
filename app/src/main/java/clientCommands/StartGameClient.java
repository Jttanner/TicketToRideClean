package clientCommands;

import clientModel.CModel;
import clientModel.StartGame;

/**
 * Created by tyler on 10/24/2017.
 */

public class StartGameClient implements ClientCommand {
    @Override
    public void execute() {
        CModel cModel = CModel.getInstance();
        cModel.toggleGameHasStarted();
        cModel.setCurrGameState(new StartGame());
        System.out.println("Start game has started");
    }
}
