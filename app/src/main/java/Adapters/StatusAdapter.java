package Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import clientModel.CModel;
import clientModel.PlayerColumn;
import clientModel.PlayerViewHolder;
import modeling.Player;
import teamjapannumbahone.tickettoride.R;

/**
 * Created by tyler on 4/3/2017.
 * Our adapter for Game Status
 */

public class StatusAdapter extends RecyclerView.Adapter<PlayerViewHolder> {
    private ArrayList<PlayerColumn> playerColumns;

    private View pageView;

    public StatusAdapter(Context context, ArrayList<PlayerColumn> playerColumns) {
        super();
        this.playerColumns = playerColumns;
    }

    @Override
    public PlayerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        pageView = parent.getRootView();
        return new PlayerViewHolder(layoutInflater, parent);
    }

    @Override
    public void onBindViewHolder(PlayerViewHolder holder, int position) {
        holder.bind(playerColumns.get(position));

        String currPlayer = "";

        for (Player p: CModel.getInstance().getCurrGame().getPlayers()){
            if (p.isMyTurn()){
                currPlayer = p.getPlayerName();
            }
        }

        ((TextView) pageView.findViewById(R.id.current_player_turn)).setText("Current Turn: " + currPlayer);
    }

    @Override
    public int getItemCount() {
        return playerColumns.size();
    }

    public void setPlayerColumns(ArrayList<PlayerColumn> playerColumns) {
        this.playerColumns = playerColumns;
    }
}
