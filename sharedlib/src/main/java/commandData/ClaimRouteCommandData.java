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
    private String game;
    private Player player;

    //Constructors
    public ClaimRouteCommandData(String game, String startCity, String endCity) {
        setType("claimRoute");
        this.game = game;
        this.startCity = startCity;
        this.endCity = endCity;
    }
    public ClaimRouteCommandData () {
    }

    //Getters and Setters

    public String getGame() {
        return game;
    }
    public void setGame(String game) {
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
