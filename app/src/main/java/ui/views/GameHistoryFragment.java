package ui.views;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import Adapters.GameHistoryAdapter;
import MVP_coms_classes.MVP_GameHistory;
import clientModel.CModel;
import presenters.GameHistoryPresenter;
import teamjapannumbahone.tickettoride.R;


public class GameHistoryFragment extends DialogFragment implements MVP_GameHistory.GameHistoryViewOps {
    private RecyclerView recyclerView;
    private GameHistoryAdapter adapter;
    private ArrayList<String> testString = new ArrayList<>();
    private MVP_GameHistory.GameHistoryPresOps presenter;

    public GameHistoryFragment(){
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        testString.add("Game History");
//        testString.add("Game History 2");
//        testString.add("Game History 3");
//        testString.add("Game History 4");
//        testString.add("Game History 5");
//        testString.add("Game History 6");
//        testString.add("Game History 7");
//        testString.add("Game History 88");
//        testString.add("Game History 9");
//        testString.add("Game History 10");
//        testString.add("Game History 11");
//        testString.add("Game History 12");

        this.setCancelable(false);

        presenter = new GameHistoryPresenter(this);

        View v = inflater.inflate(R.layout.fragment_game_history, container, false);
        getDialog().show();
        getDialog().getWindow().setLayout(1000,1000);
        //Inflate layout with recycler view
        recyclerView = (RecyclerView) v.findViewById(R.id.GameHistoryRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //Set up Adapter
        adapter = new GameHistoryAdapter(CModel.getInstance().getCurrGame().getGameHistoryList(), getContext());
        //adapter = new GameHistoryAdapter(testString, getContext());
        recyclerView.setAdapter(adapter);
        return v;
    }

    @Override
    public Context getAppContext() {
        return null;
    }

    @Override
    public Context getActivityContext() {
        return null;
    }

    @Override
    public void updateGameHistory(ArrayList<String> gameHistoryList) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new GameHistoryAdapter(gameHistoryList, getContext());
        recyclerView.setAdapter(adapter);
    }

}
