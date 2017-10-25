package presenters;

import java.lang.ref.WeakReference;
import java.util.Observable;
import java.util.Observer;

import MVP_coms_classes.MVP_GameList;
import clientModel.CModel;
import commandData.CreateGameCommandData;
import commandData.JoinGameCommandData;
import modeling.Game;
import modeling.GameList;
import modeling.Player;
import servercomms.ServerProxy;

/**
 * Created by LabUser on 10/2/2017.
 */

public class GameListPresenter implements MVP_GameList.GameListPresenterInterface,Observer {
    private WeakReference<MVP_GameList.GameListActivityInterface> myView;
    public GameListPresenter(){}

    public GameListPresenter(MVP_GameList.GameListActivityInterface view){
        myView = new WeakReference<>(view);
        CModel.getInstance().addObserver(this);
    }
    @Override
    public void CreateGame(Game game) {
        CreateGameCommandData command = new CreateGameCommandData(game);
        ServerProxy.getInstance().sendCommand(command);
    }


    @Override
    public void JoinGame(Game game) {
        //Check to see if the user is already in the game
        boolean userExist = false;
        String userName = CModel.getInstance().getMyUser().getUserName();
        Player userPlayer = game.getPlayer(userName);
        //if we didnt find the user, add him in the server
        if(userPlayer == null) {
            JoinGameCommandData data = new JoinGameCommandData(game.getGameID(),CModel.getInstance().getMyUser());
            ServerProxy.getInstance().sendCommand(data);
        }
        else {
            //otherwise lets go on to the next activity as what would of happened is that the user simply backed out of
            //the waiting room activity and is trying to get in. No need to talk to the server in this case
            myView.get().JoinGameResult(game);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        //For updating the game list we will have a gamelist sent
        if(arg instanceof GameList){
            GameList games = (GameList) arg;
            myView.get().UpdateList(games.getGames());
        }
        //For join game we will get a game sent to us
        else if(arg instanceof Game){
            myView.get().JoinGameResult((Game)arg);
        }
    }
}
