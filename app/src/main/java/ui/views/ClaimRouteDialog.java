package ui.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.v4.app.DialogFragment;

import MVP_coms_classes.MVP_ClaimRoute;
import presenters.ClaimRoutePresenter;
import teamjapannumbahone.tickettoride.R;


public class ClaimRouteDialog extends DialogFragment implements MVP_ClaimRoute.ClaimRouteMapViewOps {
    //private RecyclerView recyclerView;
    //private GameHistoryAdapter adapter;
    //private ArrayList<String> testString = new ArrayList<>();
    private MVP_ClaimRoute.ClaimRoutePresenterOps presenter;

    public ClaimRouteDialog(){
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        presenter = new ClaimRoutePresenter(this);

        View v = inflater.inflate(R.layout.fragment_game_history, container, false);
        getDialog().show();
        getDialog().getWindow().setLayout(1000,1000);
        //Inflate layout with recycler view
        //recyclerView = (RecyclerView) v.findViewById(R.id.GameHistoryRecycler);
        //recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //Set up Adapter
        //adapter = new GameHistoryAdapter(CModel.getInstance().getCurrGame().getGameHistoryList(), getContext());
        //adapter = new GameHistoryAdapter(testString, getContext());
        //recyclerView.setAdapter(adapter);
        return v;
    }

    @Override
    public void UpdateClaimRouteView() {

    }
}
