package presenters;

import java.lang.ref.WeakReference;
import java.util.Observable;
import java.util.Observer;

import MVP_coms_classes.MVP_GameList;
import clientModel.CModel;
import commandData.CreateGameCommandData;
import modeling.Game;
import result.GameList;
import servercomms.ClientFacade;
import servercomms.ServerProxy;

/**
 * Created by LabUser on 10/2/2017.
 */

public class GameListPresenter implements MVP_GameList.GameListPresenterInterface,Observer {
    private WeakReference<MVP_GameList.GameListActivityInterface> myView;
   // private Game createdGame;
    public GameListPresenter(){}

    public GameListPresenter(MVP_GameList.GameListActivityInterface view){
        myView = new WeakReference<>(view);
        CModel.getInstance().addObserver(this);
    }
    @Override
    public void CreateGame(Game game) {
        CreateGameCommandData command = new CreateGameCommandData();
        command.setType("createGame");
        command.setGameObject(game);
        //createdGame = game;
        ServerProxy.getInstance().CreateGame(command);
    }


    @Override
    public void JoinGame(Game game) {
        ClientFacade.getInstance().updateCurrGame(game);
    }

    /*@Override
    public void checkCommandSuccess(CommandResult r) {
        if(r != null && r.isSuccess()) {
            CModel.getInstance().addGame(createdGame);
            this.JoinGame(createdGame);
        }

        //TODO check the success of any given command and do something with it

    }*/


    public static void initiazlizePoller() {
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof GameList){
            GameList games = (GameList) arg;
            myView.get().UpdateList(games.getGames());
        }
        else if(arg instanceof Game){
            myView.get().JoinGameResult((Game)arg);
        }
    }
}
