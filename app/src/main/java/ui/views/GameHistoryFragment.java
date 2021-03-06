package ui.views;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import Adapters.GameHistoryAdapter;
import MVP_coms_classes.MVP_GameHistory;
import clientModel.CModel;
import presenters.GameHistoryPresenter;
import teamjapannumbahone.tickettoride.R;


public class GameHistoryFragment extends DialogFragment implements MVP_GameHistory.GameHistoryViewOps {
    private RecyclerView recyclerView;
    private GameHistoryAdapter adapter;
    private MVP_GameHistory.GameHistoryPresOps presenter;

    public GameHistoryFragment(){
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //this.setCancelable(false);

//        presenter = new GameHistoryPresenter(this);

        View v = inflater.inflate(R.layout.fragment_game_history, container, false);
        getDialog().show();
        getDialog().getWindow().setLayout(1000,1000);
        //Inflate layout with recycler view
        recyclerView = (RecyclerView) v.findViewById(R.id.GameHistoryRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //Set up Adapter
        adapter = new GameHistoryAdapter(CModel.getInstance().getCurrGame().getGameHistoryList(), getContext());
        recyclerView.setAdapter(adapter);
        return v;
    }
//
//    @Override
//    public Context getAppContext() {
//        return null;
//    }
//
//    @Override
//    public Context getActivityContext() {
//        return null;
//    }

    @Override
    public void updateGameHistory(ArrayList<String> gameHistoryList) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new GameHistoryAdapter(gameHistoryList, getContext());
        recyclerView.setAdapter(adapter);
    }

}
