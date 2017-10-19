package ui.views;

/**
 * Created by tyler on 4/3/2017.
 * A special class to put player info in, which is used by the PlayerViewHolder */

public class PlayerColumns {
    /**The type of filter*/
    private String playerString;

    public PlayerColumns(String playerString){
        this.playerString = playerString;
    }

    public String getPlayerText() {
        return playerString;
    }
}
