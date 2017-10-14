package servercomms;

import android.util.Log;

import clientModel.CModel;
import modeling.Game;
import modeling.User;
import result.CommandResult;
import result.CreateGameCommandResult;
import result.GetGameCommandResult;
import result.JoinGameCommandResult;

/**
 * Created by tyler on 9/26/2017.
 * This class takes requests from the Server proxy, such as updating the game list. These operations updateWaitingRoom the model, which will
 * in turn notify the presenters of any changes made.
 */
class ClientFacade {
    private static final String TAG = "ClientFacade";
    private static ClientFacade ourInstance = new ClientFacade();

    public static ClientFacade getInstance() {
        return ourInstance;
    }

    private ClientFacade() {
    }

   /* public void updateGameList(GameList games){
        CModel.getInstance().setAllGames(games);
    }*/

    void checkTypeOfCommand(CommandResult result) {
        //This if means we have created a game(and we are joining)
       if(result.getData() instanceof CreateGameCommandResult){
           //add the game to the gamelist
           CModel.getInstance().addGame(((CreateGameCommandResult) result).getGame());
           //now join the game, if possible
            if((((CreateGameCommandResult) result).getGame().canJoinGame())) {
                CModel.getInstance().setCurrGame((Game)result.getData());
            }
        }
        else if(result instanceof GetGameCommandResult){
            CModel.getInstance().setAllGames(((GetGameCommandResult) result).getGameList());
        }
       //This if is for joinGame, and the result.getData is the GameID for the game we are joining
       else if(result instanceof JoinGameCommandResult){
           CModel.getInstance().setCurrGame(((JoinGameCommandResult) result).getGameId());
       }
//        else if(result.getType().equals("startGame")) {
//            CModel.getInstance().toggleGameHasStarted();
//            //When do we check if there is at least 2 players? The client will never be able to start the game until 2 players
//            //We don't need to worry about that logic here
//            //This else if does nothing. The server should send the start game command to the command manager
//            //Each client's poller should check the client manager to see when the game started.
//        }

       else{
           Log.d(TAG,"We got a different class then expected");
       }
    }

    void updateUser(User user) {
        CModel.getInstance().setMyUser(user);
    }

    /*public void updateCurrGame(Game game){
        //this if means we have joined a game
        if(game.canJoinGame()){
            CModel.getInstance().setCurrGame(game);
        }

    }*/

    /*public void updatePlayerList(PlayerList players){
        CModel.getInstance().setAllPlayers(players.getPlayers());
    }*/
}