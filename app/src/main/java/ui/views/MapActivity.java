package ui.views;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import Adapters.StatusAdapter;
import MVP_coms_classes.MVP_Map;
import modeling.Game;
import modeling.Route;
import poller.Poller;
import presenters.MapPresenter;
import teamjapannumbahone.tickettoride.R;

public class MapActivity extends FragmentActivity implements MVP_Map.MapViewOps{
    private static final String TAG = "MapActivity";
    private RecyclerView mGameStatus;
    private StatusAdapter statusAdapter;
    private MVP_Map.MapPresOps presenter;
    private Button chatroom;

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

        setupMVP();
        chatroom = (Button) findViewById(R.id.chatButton);
        chatroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();
                ChatFragment fragment = new ChatFragment();
                fragment.show(fm,"chat_fragment");
            }
        });
        //mGameStatus = (RecyclerView) findViewById(R.id.recycler_status);
        //change how we get the players
        /*ArrayList<PlayerColumns> playerText;//CModel.getInstance().getCurrGame().getPlayers();
        StatusAdapter status = new StatusAdapter(this,playerText);

        mGameStatus.setLayoutManager(new LinearLayoutManager(this));
        mGameStatus.setAdapter(status);*/

        //MapBaseView view = new MapBaseView(this);
        //layout.addView(view);

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
