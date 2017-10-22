package clientModel;

import android.util.Log;

import modeling.Player;

/**
 * Created by tyler on 4/3/2017.
 * A special class to put player info in, which is used by the PlayerViewHolder */

public class PlayerColumn {
    private static final String TAG = "PlayerColumn";
    /**The type of filter*/
    private Player player;

    public PlayerColumn(Player player){
        this.player = player;
    }

    String getPlayerText() {
        return player.toString();
    }
    int getPlayerColor(){
        MyColor myColor = MyColor.unToString(player.getColor());
        if(myColor != null) {
            Log.d(TAG,"setting color in game status: " + myColor.toString());
            return myColor.getColor();
        }
        Log.d(TAG,"Error in getting color from player, giving default: ");
        return  MyColor.DARKGREY.getColor();
    }

    boolean getIfPlayerTurn(){
        return player.isMyTurn();
    }


}
