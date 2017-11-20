package Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import modeling.Player;
import teamjapannumbahone.tickettoride.R;

/**
 * Created by ACL1 on 11/13/2017.
 */

public class GameOverListAdapter  extends RecyclerView.Adapter<GameOverListAdapter.ViewHolder> {

    List<Player> list;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public ViewHolder(View view){
            super(view);
            textView = (TextView) view.findViewById(R.id.player_view);
        }
    }


    public GameOverListAdapter(List<Player> players){
        list = players;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.player_list_view_holder,parent,false);
        GameOverListAdapter.ViewHolder viewHolder = new GameOverListAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Player currentPlayer = list.get(position);
        String display = currentPlayer.getPlayerName() + " with " + currentPlayer.getPoints() + " points";
        holder.textView.setText(display);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
