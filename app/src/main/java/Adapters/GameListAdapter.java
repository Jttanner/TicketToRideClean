package Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import MVP_coms_classes.MVP_GameList;
import clientModel.CModel;
import modeling.Game;
import modeling.Player;
import teamjapannumbahone.tickettoride.R;

/**
 * Created by LabUser on 10/2/2017.
 */

public class GameListAdapter extends RecyclerView.Adapter<GameListAdapter.ViewHolder> {
    MVP_GameList.GameListPresenterInterface presenter;
    //the view holder for each row of the recycler view
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private Game selectedGame;
        private TextView id;
        private TextView name;
        private TextView players;
        private TextView inGame;
        MVP_GameList.GameListPresenterInterface presenter;

        public ViewHolder(View itemView,MVP_GameList.GameListPresenterInterface presenter) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.GameListAdapterGameNumber);
            name = (TextView) itemView.findViewById(R.id.GameListAdapterGameName);
            players = (TextView) itemView.findViewById(R.id.GameListAdapterGamePlayers);
            inGame = (TextView) itemView.findViewById(R.id.GameListAdapterGameInGame);
            this.presenter = presenter;
            setupListeners();
        }

        private void setupListeners() {
            id.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectGame();
                }
            });
            name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectGame();
                }
            });
            players.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectGame();
                }
            });
            inGame.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectGame();
                }
            });
        }

        private void selectGame() {
            for (Game g: CModel.getInstance().getAllGames()) {
                if(g.getGameName().equals(name.getText().toString())){
                    selectedGame = g;
                    presenter.JoinGame(selectedGame);
                }
            }
        }
    }


    //game list member variable
    private List<Game> list;
    //constructor
    public GameListAdapter(List<Game> l, MVP_GameList.GameListPresenterInterface presenter){
        this.presenter = presenter;
        this.list = l;
    }

    public void setList(List<Game> list) {
        this.list = list;
    }

    public List<Game> getGames(){
        return this.list;
    }


    @Override
    public GameListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.gamelist_holder,parent,false);
        GameListAdapter.ViewHolder viewHolder = new ViewHolder(v,presenter);
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
