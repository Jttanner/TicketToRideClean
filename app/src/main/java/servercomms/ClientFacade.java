package servercomms;

import java.util.List;

import modeling.Game;

/**
 * Created by tyler on 9/26/2017.
 * This class takes requests from the Server proxy, such as updating the game list. These operations update the model, which will
 * in turn notify the presenters of any changes made.
 */
public class ClientFacade {
    private static ClientFacade ourInstance = new ClientFacade();

    public static ClientFacade getInstance() {
        return ourInstance;
    }

    private ClientFacade() {
    }

    public void updateGameList(List<Game> games){

    }
}