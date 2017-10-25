package clientCommands;

import clientModel.CModel;

/**
 * Created by tyler on 10/24/2017.
 */

public class StartGameClient implements ClientCommand {
    @Override
    public void execute() {
        CModel.getInstance().toggleGameHasStarted();
    }
}
