package clientModel;

import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import teamjapannumbahone.tickettoride.R;


/**
 * Created by tyler on 4/3/2017.
 * Our View Holder for the status adapter
 */

public class PlayerViewHolder extends RecyclerView.ViewHolder {

    private static final String TAG = "PlayerViewHolder";
    private TextView mPlayerInfo;

    public TextView getmPlayerInfo(){
        return mPlayerInfo;
    }

    public PlayerViewHolder(LayoutInflater inflater, ViewGroup parent) {
        super(inflater.inflate(R.layout.player_list_view_holder, parent, false));
        mPlayerInfo = (TextView) itemView.findViewById(R.id.player_view);

    }

    public void bind(PlayerColumn row) {
        Log.d(TAG,"Setting player text: " + row.getPlayerText());
        Log.d(TAG,"Is players Turn: " + row.getIfPlayerTurn());
        mPlayerInfo.setText(row.getPlayerText().replaceAll("\\t","\t"));
        mPlayerInfo.setTextColor(row.getPlayerColor());
        //Set background if it is the players turn
        setBorder(row);

    }

    private void setBorder(PlayerColumn row) {
        // Initializing a ShapeDrawable
        ShapeDrawable sd = new ShapeDrawable();

        // Specify the shape of ShapeDrawable
        sd.setShape(new RectShape());

        // Specify the border color of shape to the player color
        if(row.getIfPlayerTurn()) {
            sd.getPaint().setColor(row.getPlayerColor());
        }
        else{
            //just make it black
            sd.getPaint().setColor(MyColor.BLACK.getColor());
        }

        // Set the border width
        sd.getPaint().setStrokeWidth(5f);

        // Specify the style is a Stroke
        sd.getPaint().setStyle(Paint.Style.STROKE);

        // Finally, add the drawable background to TextView
        mPlayerInfo.setBackground(sd);
    }

}
