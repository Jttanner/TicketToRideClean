package Adapters;

import android.app.ListActivity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import modeling.Player;
import teamjapannumbahone.tickettoride.R;

/**
 * Created by kwankyuk on 10/6/17.
 */

public class PlayerListAdapter extends BaseAdapter {
    private Context mContext;
    //private LayoutInflater mInflater;
    private ArrayList<Player> playerList;

    public PlayerListAdapter(Context context, ArrayList<Player> pList) {
        mContext = context;
        playerList = pList;
        //mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return playerList.size();
    }

    @Override
    public Object getItem(int i) {
        return playerList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.player_list_view_holder, viewGroup, false);
        }

        //Get the current player to be displayed
        Player currentPlayer = (Player)getItem(i);

        //
        TextView textView = (TextView) view.findViewById(R.id.player_view);

        //Sets the player info
        textView.setTextColor(currentPlayer.getColor());
        textView.setText(currentPlayer.getName());

        return view;
    }
}
