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

   /* public void updateGameList(GameList games){
        CModel.getInstance().setAllGames(games);
    }*/

    public void checkTypeOfCommand(CommandResult result) {
        //This if means we have created a game(and we are joining)
        if(result.getData() instanceof Game){
            if(((Game) result.getData()).canJoinGame()) {
                CModel.getInstance().setCurrGame((Game)result.getData());
            }
        }
        else if(result.getType().equals("getGameList")){
            CModel.getInstance().setAllGames((GameList) result.getData());
        }
        else if(result.getType().equals("startGame")) {
            CModel.getInstance().toggleGameHasStarted();
            //When do we check if there is at least 2 players? The client will never be able to start the game until 2 players
            //We don't need to worry about that logic here
            //This else if does nothing. The server should send the start game command to the command manager
            //Each client's poller should check the client manager to see when the game started.

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