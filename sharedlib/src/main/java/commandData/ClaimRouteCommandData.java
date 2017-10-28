package commandData;

import modeling.Game;
import modeling.Player;

/**
 * Created by korea on 10/28/2017.
 */

public class ClaimRouteCommandData extends Command {
    //Data Members
    private String startCity;
    private String endCity;
    private Game game;
    private Player player;

    //Constructors
    public ClaimRouteCommandData(Game game, String startCity, String endCity) {
        setType("claimRoute");
        this.game = game;
        this.startCity = startCity;
        this.endCity = endCity;
    }
    public ClaimRouteCommandData () {
    }

    //Getters and Setters
    public Game getGame() {
        return game;
    }
    public void setGame(Game game) {
        this.game = game;
    }
    public String getStartCity() {
        return startCity;
    }
    public void setStartCity(String startCity) {
        this.startCity = startCity;
    }
    public String getEndCity() {
        return endCity;
    }
    public void setEndCity(String endCity) {
        this.endCity = endCity;
    }
}
