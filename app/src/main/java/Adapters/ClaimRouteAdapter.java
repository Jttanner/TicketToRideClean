package Adapters;

import android.support.v7.widget.RecyclerView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import clientModel.CModel;
import commandData.ClaimRouteCommandData;
import modeling.Player;
import modeling.Route;
import modeling.RouteList;
import servercomms.ServerProxy;
import teamjapannumbahone.tickettoride.R;
import ui.views.ClaimRouteFragment;


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

    private List<Route> routeObjectList;
    private List<String> routeEntries;
    Context context;
    ClaimRouteFragment callingFragment;
    public ClaimRouteAdapter(List<Route> routeObjectList, List<String> routeEntries, Context c, ClaimRouteFragment callingFragment) {
        this.routeObjectList = routeObjectList;
        this.routeEntries = routeEntries;
        this.context = c;
        this.callingFragment = callingFragment;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.claim_route_view_holder, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.getTextView().setText(routeEntries.get(position));
        holder.getTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //claim route logic
                //Same person can't build on both of a double route if there are less than 4 players
                //TODO: make sure we are using playersMax. if not use Players.size()
                Route currRoute = routeObjectList.get(position);
                boolean canClaim = true;
                if(CModel.getInstance().getCurrGame().getPlayerMax() < 4){
                    //we know route is available.  Check to see if there is something already built by them on a double
                    //brute force method:
                    for (Map.Entry<Route, Player> entry : CModel.getInstance().getCurrGame().getClaimedRouteList().getRoutesMap().entrySet()){
                        if (entry.getKey().getFirstCityName().equals(currRoute.getFirstCityName()) &&
                                entry.getKey().getSecondCityName().equals(currRoute.getSecondCityName()) &&
                                entry.getValue().getPlayerName().equals(CModel.getInstance().getUserPlayer().getPlayerName())){
                            canClaim = false;
                            break;
                        }
                    }
                } else{
                    //canClaim = true; redundant
                }
                if (canClaim){
                    callingFragment.getDialog().dismiss();
                    ClaimRouteCommandData claimRouteCommandData = new ClaimRouteCommandData(CModel.getInstance().getCurrGame().getGameID(),
                            currRoute.getFirstCityName(), currRoute.getSecondCityName(), CModel.getInstance().getUserPlayer().getPlayerName(),
                            currRoute.getTrainColorNeeded(), currRoute.getDistance());
                    ServerProxy.getInstance().sendCommand(claimRouteCommandData);
                } else{
                    //cry and do nothing.  Failed claim that isn't cuaght here but is caught server side will also fail.
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return routeEntries.size();
    }

}
