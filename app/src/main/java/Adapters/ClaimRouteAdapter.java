package Adapters;

import android.support.v7.widget.RecyclerView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import teamjapannumbahone.tickettoride.R;


/**
 * Created by jontt on 11/11/2017.
 */

public class ClaimRouteAdapter extends RecyclerView.Adapter<ClaimRouteAdapter.ViewHolder> {


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView routeTextEntry;
        public ViewHolder(View v) {
            super(v);
            routeTextEntry = (TextView) v.findViewById(R.id.claim_route_view_holder_text);
        }
        public TextView getTextView() {
            return routeTextEntry;
        }
    }

    private List<String> routeEntries;
    Context context;
    public ClaimRouteAdapter(List<String> routeEntries, Context c) {
        this.routeEntries = routeEntries;
        this.context = c;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.claim_route_view_holder, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.getTextView().setText(routeEntries.get(position));
    }

    @Override
    public int getItemCount() {
        return routeEntries.size();
    }

}
