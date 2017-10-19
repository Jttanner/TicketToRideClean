package ui.views;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;

import MVP_coms_classes.MVP_Map;
import modeling.Route;
import teamjapannumbahone.tickettoride.R;

public class MapActivity extends AppCompatActivity implements MVP_Map.MapViewOps{
    private RecyclerView mGameStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.activity_map);

        //mGameStatus = (RecyclerView) findViewById(R.id.recycler_status);
        //change how we get the players
        /*ArrayList<PlayerColumns> playerText;//CModel.getInstance().getCurrGame().getPlayers();
        StatusAdapter status = new StatusAdapter(this,playerText);

        mGameStatus.setLayoutManager(new LinearLayoutManager(this));
        mGameStatus.setAdapter(status);*/

        //MapBaseView view = new MapBaseView(this);
        //layout.addView(view);

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
