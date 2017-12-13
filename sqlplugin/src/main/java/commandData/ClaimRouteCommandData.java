package commandData;

/**
 * Created by korea on 10/28/2017.
 */

public class ClaimRouteCommandData extends Command {
    //Data Members
    private String startCity;
    private String endCity;
    private String gameID;
    private String playerName;
    private String routeColor;
    private String originalTrackColor;
    private boolean isWild;
    private int distance;

    //Constructors
    public ClaimRouteCommandData(String gameID, String startCity, String endCity, String playerName, String routeColor, int distance, boolean isWild) {
        setType("claimRoute");
        this.gameID = gameID;
        this.startCity = startCity;
        this.endCity = endCity;
        this.playerName = playerName;
        this.routeColor = routeColor;
        this.distance = distance;
        this.isWild = isWild;
    }

    public ClaimRouteCommandData () {
    }



    //Getters and Setters

    public String getOriginalTrackColor() {
        return originalTrackColor;
    }

    public void setOriginalTrackColor(String originalTrackColor) {
        this.originalTrackColor = originalTrackColor;
    }

    public String getPlayerName() {
        return playerName;
    }
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    public String getGameID() {
        return gameID;
    }
    public void setGameID(String gameID) {
        this.gameID = gameID;
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

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public boolean isWild() {
        return isWild;
    }

    public void setWild(boolean wild) {
        isWild = wild;
    }
}
