package presenters;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import MVP_coms_classes.MVP_DestCard;
import clientModel.CModel;
import commandData.DrawDestinationCardCommandData;
import modeling.DestinationCard;
import modeling.Game;
import modeling.Player;
import servercomms.ServerProxy;

/**
 * Created by ahwang13 on 10/23/17.
 */

public class DestinationCardPresenter implements MVP_DestCard.MapPresOps,Observer {
    private WeakReference<MVP_DestCard.MapViewOps> myView;

    public DestinationCardPresenter(MVP_DestCard.MapViewOps view){
        myView = new WeakReference<>(view);
        CModel.getInstance().addObserver(this);
    }

    @Override
    public void getDestinationCards(Game game, Player player) {

        //if we didnt find the user, add him in the server
        DrawDestinationCardCommandData data = new DrawDestinationCardCommandData(game.getGameID(), player);
        ServerProxy.getInstance().sendCommand(data);
    }


    @Override
    public void update(Observable o, Object arg) {
        //For updating the game list we will have a gamelist sent
        if(arg instanceof DestinationCard){
            List<DestinationCard> destinationCards = (List<DestinationCard>) arg;
            myView.get().giveChosenCards(destinationCards);
        }
    }

    /*
    @Override
    public void CreateGame(Game game) {
        CreateGameCommandData command = new CreateGameCommandData(game);
        ServerProxy.getInstance().sendCommand(command);
    }*/

    /*
    @Override
    public void pickDestCards(List<DestinationCard> cards) {

        ServerProxy.getInstance().sendCommand(data);
    }
    */

    /*
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
    */
}

