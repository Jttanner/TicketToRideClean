package servercomms;

import clientModel.CModel;
import modeling.Game;
import modeling.User;
import result.CommandResult;
import result.GameList;

/**
 * Created by tyler on 9/26/2017.
 * This class takes requests from the Server proxy, such as updating the game list. These operations updateWaitingRoom the model, which will
 * in turn notify the presenters of any changes made.
 */
public class ClientFacade {
    private static ClientFacade ourInstance = new ClientFacade();

    public static ClientFacade getInstance() {
        return ourInstance;
    }

    private ClientFacade() {
    }

    public void updateGameList(GameList games){
        CModel.getInstance().setAllGames(games.getGames());
    }

    public void checkTypeOfCommand(CommandResult result) {
        //This if means we have created a game(and we are joining)
        if(result.getData() instanceof Game){
            if(((Game) result.getData()).canJoinGame()) {
                CModel.getInstance().setCurrGame((Game)result.getData());
            }
        }
    }

    public void updateUser(User user) {
        CModel.getInstance().setMyUser(user);
    }

    public void updateCurrGame(Game game){
        //this if means we have joined a game
        if(game.canJoinGame()){
            CModel.getInstance().setCurrGame(game);
        }

    }
}