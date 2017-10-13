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
import servercomms.ServerProxy;

/**
 * Created by LabUser on 10/2/2017.
 */

public class GameListPresenter implements MVP_GameList.GameListPresenterInterface,Observer {
    private WeakReference<MVP_GameList.GameListActivityInterface> myView;
    private Game createdGame;
    public GameListPresenter(){}

    public GameListPresenter(MVP_GameList.GameListActivityInterface view){
        myView = new WeakReference<>(view);
        CModel.getInstance().addObserver(this);
    }
    @Override
    public void CreateGame(Game game) {
        CreateGameCommandData command = new CreateGameCommandData(game);
        createdGame = game;
        ServerProxy.getInstance().sendCommand(command);
    }


    @Override
    public void JoinGame(Game game) {
        //CModel.getInstance().setCurrGame(game);
        createdGame = game;
        JoinGameCommandData data = new JoinGameCommandData(game.getGameID(),CModel.getInstance().getMyUser());
        ServerProxy.getInstance().sendCommand(data);


    }

    /*@Override
    public void checkCommandSuccess(CommandResult r) {
        if(r != null && r.isSuccess()) {
            switch (r.getType()) {
                case "createGame":
                    CModel.getInstance().addGame(createdGame);
                    CModel.getInstance().setCurrGame(createdGame);
                    this.JoinGame(createdGame);
                    break;
                case "joinGame":
                    myView.get().JoinGameResult(createdGame);
                    break;
                default:
                    break;
            }
        }*/

        //TODO check the success of any given command and do something with it

    //}

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
