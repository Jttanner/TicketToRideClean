package ui.views;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.RelativeLayout;

import Adapters.StatusAdapter;
import MVP_coms_classes.MVP_Map;
import modeling.Game;
import modeling.Route;
import poller.Poller;
import presenters.MapPresenter;
import teamjapannumbahone.tickettoride.R;

public class MapActivity extends Activity implements MVP_Map.MapViewOps{
    private static final String TAG = "MapActivity";
    private RecyclerView mGameStatus;
    private StatusAdapter statusAdapter;
    private MVP_Map.MapPresOps presenter;

    @Override
    protected void onDestroy() {
        //destroy poller
        Poller.getInstance().stopPoller();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.activity_map);
        mGameStatus = (RecyclerView) findViewById(R.id.recycler_status);
        setupView();
        //start poller
        Poller.getInstance().updateGameList();
    }

    private void setupView() {
        Log.d(TAG,"setupView");
        /* Create the Presenter; Set the Presenter as a interface to limit communication*/
        presenter = new MapPresenter(this);
        statusAdapter = new StatusAdapter(this,presenter.getPlayerColumns());
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mGameStatus.setLayoutManager(manager);
        mGameStatus.setAdapter(statusAdapter);
    }

    @Override
    public void updateMap() {

    }

    @Override
    public Context getAppContext() {
        return getApplicationContext();
    }

    @Override
    public void updatePlayerStats(Game g) {
        statusAdapter.setPlayerColumns(presenter.getPlayerColumns());
        statusAdapter.notifyDataSetChanged();
    }

    @Override
    public Context getActivityContext() {
        return this;
    }

    @Override
    public void routeClaimed(Route r) {

    }
}
