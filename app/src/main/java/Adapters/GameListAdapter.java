package Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.TextView;

import java.util.List;

import modeling.Game;
import modeling.Player;
import teamjapannumbahone.tickettoride.R;

/**
 * Created by LabUser on 10/2/2017.
 */

public class GameListAdapter extends RecyclerView.Adapter<GameListAdapter.ViewHolder> {

    //the view holder for each row of the recycler view
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView id;
        public TextView name;
        public TextView players;
        public TextView inGame;

        public ViewHolder(View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.GameListAdapterGameNumber);
            name = (TextView) itemView.findViewById(R.id.GameListAdapterGameName);
            players = (TextView) itemView.findViewById(R.id.GameListAdapterGamePlayers);
            inGame = (TextView) itemView.findViewById(R.id.GameListAdapterGameInGame);
        }
    }
    //game list member variable
    List<Game> list;
    //constructor
    public GameListAdapter(List<Game> l){
        list = l;
    }


    @Override
    public GameListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.gamelist_holder,parent,false);
        GameListAdapter.ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GameListAdapter.ViewHolder holder, int position) {
        Game currentGame = list.get(position);
        holder.id.setText(position);
        holder.name.setText(currentGame.getGameName());
        String players = "";

        for(Player each : currentGame.getPlayers()){
            players += each.getName();
        }
        holder.players.setText(players);
        holder.inGame.setText(currentGame.getPlayers().size() + "/" + currentGame.getPlayerMax());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
