package ui.views;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;

import MVP_coms_classes.MVP_Map;
import modeling.Route;
import poller.Poller;
import presenters.MapPresenter;
import teamjapannumbahone.tickettoride.R;

public class MapActivity extends Activity implements MVP_Map.MapViewOps{
    private static final String TAG = "MapActivity";
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
        //mGameStatus = (RecyclerView) findViewById(R.id.recycler_status);
        Log.d(TAG,"setupView");
        /* Create the Presenter; Set the Presenter as a interface to limit communication*/
        presenter = new MapPresenter(this);
        //start poller
        Poller.getInstance().updateGameList();
    }

    @Override
    public void updateMap() {

    }

    @Override
    public Context getAppContext() {
        return getApplicationContext();
    }


    @Override
    public Context getActivityContext() {
        return this;
    }

    @Override
    public void routeClaimed(Route r) {

    }
}
