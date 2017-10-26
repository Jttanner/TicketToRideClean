package Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import teamjapannumbahone.tickettoride.R;

/**
 * Created by korea on 10/25/2017.
 */

public class GameHistoryAdapter extends RecyclerView.Adapter<GameHistoryAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView singleGameHistory;
        public ViewHolder(View v) {
            super(v);
            singleGameHistory = (TextView) v.findViewById(R.id.gameHistoryViewHolder);
        }
        public TextView getTextView() {
            return singleGameHistory;
        }
    }

    private ArrayList<String> gameHistory;
    Context context;
    public GameHistoryAdapter(ArrayList<String> gameHistoryList, Context c) {
        this.gameHistory = gameHistoryList;
        this.context = c;
    }

    public GameHistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.game_history_view_holder, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(GameHistoryAdapter.ViewHolder holder, int position) {
        holder.getTextView().setText(gameHistory.get(position));
    }

    @Override
    public int getItemCount() {
        return gameHistory.size();
    }
}
