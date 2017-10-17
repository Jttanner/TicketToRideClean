package ui.views;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import teamjapannumbahone.tickettoride.R;


/**
 * Created by tyler on 4/3/2017.
 * Our View Holder
 */

public class PlayerViewHolder extends RecyclerView.ViewHolder {

    private TextView mPlayerInfo;

    public PlayerViewHolder(LayoutInflater inflater, ViewGroup parent) {
        super(inflater.inflate(R.layout.player_list_view_holder, parent, false));
        mPlayerInfo = (TextView) itemView.findViewById(R.id.player_view);

    }

    public void bind(PlayerColumns row) {
        mPlayerInfo.setText(row.getPlayerText());
    }
}
