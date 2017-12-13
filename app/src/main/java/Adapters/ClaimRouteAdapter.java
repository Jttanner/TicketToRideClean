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
        String routeColor;
        public void setRouteColor(String routeColor){
            this.routeColor = routeColor;
        }
        public String getRouteColor(){
            return  routeColor;
        }
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
    View pageView;
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
        pageView = parent.getRootView();
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.getTextView().setText(routeEntries.get(position));
        holder.setRouteColor(routeObjectList.get(position).getTrainColorNeeded());
        holder.getTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //claim route logic
                //Same person can't build on both of a double route if there are less than 4 players
                //TODO: make sure we are using playersMax. if not use Players.size()
                final Route currRoute = routeObjectList.get(position);
                boolean canClaim = true;
                if(CModel.getInstance().getCurrGame().getPlayerMax() < 4){
                    //we know route is available.  Check to see if there is something already built by them on a double
                    //brute force method:
                    for (Map.Entry<String,Route> entry : CModel.getInstance().getCurrGame().getClaimedRouteList().getRoutesMap().entrySet()){
                        if (entry.getValue().getFirstCityName().equals(currRoute.getFirstCityName()) &&
                                entry.getValue().getSecondCityName().equals(currRoute.getSecondCityName()) &&
                                entry.getKey().equals(CModel.getInstance().getUserPlayer().getPlayerName())){
                            canClaim = false;
                            break;
                        }
                    }
                } else{
                    //canClaim = true; redundant
                }
                if (canClaim){
                    if (holder.getRouteColor().equals("Wild")){
                        //"Red", "Blue", "Orange", "White", "Yellow", "Purple", "Black", "Green"

                        pageView.findViewById(R.id.redChoiceButton).setEnabled(true);
                        pageView.findViewById(R.id.redChoiceButton).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                sendClaimRouteCommand(currRoute, "Red", true);
                            }
                        });
                        pageView.findViewById(R.id.blueButton).setEnabled(true);
                        pageView.findViewById(R.id.blueButton).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                sendClaimRouteCommand(currRoute, "Blue", true);
                            }
                        });
                        pageView.findViewById(R.id.orangeButton).setEnabled(true);
                        pageView.findViewById(R.id.orangeButton).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                sendClaimRouteCommand(currRoute, "Orange", true);
                            }
                        });
                        pageView.findViewById(R.id.whiteButton).setEnabled(true);
                        pageView.findViewById(R.id.whiteButton).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                sendClaimRouteCommand(currRoute, "White", true);
                            }
                        });
                        pageView.findViewById(R.id.yellowButton).setEnabled(true);
                        pageView.findViewById(R.id.yellowButton).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                sendClaimRouteCommand(currRoute, "Yellow", true);
                            }
                        });
                        pageView.findViewById(R.id.purpleButton).setEnabled(true);
                        pageView.findViewById(R.id.purpleButton).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                sendClaimRouteCommand(currRoute, "Purple", true);
                            }
                        });
                        pageView.findViewById(R.id.blackButton).setEnabled(true);
                        pageView.findViewById(R.id.blackButton).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                sendClaimRouteCommand(currRoute, "Black", true);
                            }
                        });
                        pageView.findViewById(R.id.greenButton).setEnabled(true);
                        pageView.findViewById(R.id.greenButton).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                sendClaimRouteCommand(currRoute, "Green", true);
                            }
                        });
                        pageView.findViewById(R.id.wildButton).setEnabled(true);
                        pageView.findViewById(R.id.wildButton).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                sendClaimRouteCommand(currRoute, "Wild", true);
                            }
                        });

                    } else{
                        sendClaimRouteCommand(currRoute, currRoute.getTrainColorNeeded());
                    }
                } else{
                    //cry and do nothing.  Failed claim that isn't cuaght here but is caught server side will also fail.
                }

            }
        });
    }

    private void sendClaimRouteCommand(Route currRoute, String color){
        callingFragment.getDialog().dismiss();
        CModel.getInstance().getCurrGameState().claimRoute(currRoute, color, false);
    }

    private void sendClaimRouteCommand(Route currRoute, String color, boolean isWild){
        callingFragment.getDialog().dismiss();
        CModel.getInstance().getCurrGameState().claimRoute(currRoute, color, isWild);
    }

    @Override
    public int getItemCount() {
        return routeEntries.size();
    }

}
