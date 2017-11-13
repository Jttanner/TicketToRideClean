package commandData;

/**
 * Created by korea on 10/28/2017.
 */

public class ClaimRouteCommandData extends Command {
    //Data Members
    private String startCity;
    private String endCity;
    private String gameName;
    private String playerName;
    private String routeColor;

    //Constructors
    public ClaimRouteCommandData(String gameName, String startCity, String endCity, String playerName, String routeColor) {
        setType("claimRoute");
        this.gameName = gameName;
        this.startCity = startCity;
        this.endCity = endCity;
        this.playerName = playerName;
        this.routeColor = routeColor;
    }
    public ClaimRouteCommandData () {
    }

    //Getters and Setters
    public String getPlayerName() {
        return playerName;
    }
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    public String getGameName() {
        return gameName;
    }
    public void setGameName(String gameName) {
        this.gameName = gameName;
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

    public String getRouteColor() {
        return routeColor;
    }

    public void setRouteColor(String routeColor) {
        this.routeColor = routeColor;
    }
}
