package presenters;

import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import MVP_coms_classes.MVP_PlayerStats;
import clientModel.CModel;
import clientModel.MyColor;
import clientModel.PlayerColumn;
import modeling.Game;
import modeling.GameList;
import modeling.Player;

/**
 * Created by tyler on 10/20/2017.
 * The presenter for the player stats view on the map
 */

public class PlayerStatsPresenter implements MVP_PlayerStats.PresOps,Observer{
    private WeakReference<MVP_PlayerStats.ViewOps> myView;



    public PlayerStatsPresenter(MVP_PlayerStats.ViewOps view) {
        myView = new WeakReference<>(view);
        CModel.getInstance().addObserver(this);
    }
    /**This method sets the textviews of certain colors of traincars with the number the user's palyer object has
     * @param colorNumMap Map which holds colors as keys and textviews as values*/
    @Override
    public void setCardNumbers(Map<MyColor, TextView> colorNumMap) {
        Player user = CModel.getInstance().getUserPlayer();
        //For every type of color
        for (MyColor color: colorNumMap.keySet()) {
            //grab the num of how many cards of this color the user has
            int numOfThisColor = user.getResourceColorList(color.toString()).size();
            myView.get().setMyTextView(color,numOfThisColor);

        }

    }

    @Override
    public ArrayList<PlayerColumn> getPlayerColumns() {
        List<Player> players = CModel.getInstance().getCurrGame().getPlayers();

        ArrayList<PlayerColumn> columns = new ArrayList<>();
        //create strings out of the stats of each player for the recycler
        for(Player player : players){
            columns.add(new PlayerColumn(player));
        }
        return columns;
    }

    /**This will update the map view according to appropriate changes in the model*/
    @Override
    public void update(Observable o, Object arg) {
        //TODO do we really need to poll for the whole gamelist here? why not just the currentGame?
        if(arg instanceof GameList){
            for (Game g:((GameList) arg).getGames()) {
                //if the same game
                //TODO check to see if the status stuff is actually different before redoing the whole recycler view
                if (CModel.getInstance().getCurrGame().getGameID().equals(g.getGameID())){
                    myView.get().updatePlayerStats();
                }
            }
        }
        else if(arg instanceof  Game){
            if (CModel.getInstance().getCurrGame().getGameID().equals(((Game) arg).getGameID())){
                myView.get().updatePlayerStats();
            }
        }
        //ex, game won,player attributes changed(routeClaimed),
    }
}
