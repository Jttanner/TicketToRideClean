package Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import clientModel.PlayerColumn;
import clientModel.PlayerViewHolder;

/**
 * Created by tyler on 4/3/2017.
 * Our adapter for Game Status
 */

public class StatusAdapter extends RecyclerView.Adapter<PlayerViewHolder> {
    private ArrayList<PlayerColumn> playerColumns;
    public StatusAdapter(Context context, ArrayList<PlayerColumn> playerColumns) {
        super();
        this.playerColumns = playerColumns;
    }

    @Override
    public PlayerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new PlayerViewHolder(layoutInflater, parent);
    }

    @Override
    public void onBindViewHolder(PlayerViewHolder holder, int position) {
        holder.bind(playerColumns.get(position));
    }

    @Override
    public int getItemCount() {
        return playerColumns.size();
    }

    public void setPlayerColumns(ArrayList<PlayerColumn> playerColumns) {
        this.playerColumns = playerColumns;
    }
}
