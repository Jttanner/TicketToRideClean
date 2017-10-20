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
        for(Player p: game.getPlayers()) {
            if(p.getUserName().equals(CModel.getInstance().getMyUser().getUserName())) {
                //Don't let them join
                userExist = true;
            }
        }
        if (userExist == false) {
            JoinGameCommandData data = new JoinGameCommandData(game.getGameID(),CModel.getInstance().getMyUser());
            ServerProxy.getInstance().sendCommand(data);
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
