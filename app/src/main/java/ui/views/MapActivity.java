package ui.views;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import MVP_coms_classes.MVP_Map;
import clientModel.CModel;
import modeling.Route;
import poller.Poller;
import presenters.MapPresenter;
import teamjapannumbahone.tickettoride.R;

public class MapActivity extends FragmentActivity implements MVP_Map.MapViewOps{
    private static final String TAG = "MapActivity";
    private RecyclerView mGameStatus;
    public MVP_Map.MapPresOps presenter;
    private Button chatroom;
    private Button gameHistory;
    private Button demo;
    private int count = 0;

    @Override
    protected void onDestroy() {
        //destroy poller
        Poller.getInstance().stopGetCommandsPoller();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        //RelativeLayout layout = (RelativeLayout) findViewById(R.id.activity_map);

        presenter = new MapPresenter(this);

        SlidingPaneLayout slidingPaneLayout = (SlidingPaneLayout) findViewById(R.id.activity_map);

        slidingPaneLayout.setPanelSlideListener(new SlidingPaneLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {

            }

            @Override
            public void onPanelOpened(View panel) {

            }

            @Override
            public void onPanelClosed(View panel) {

            }
        });
        gameHistory = (Button) findViewById(R.id.gameHistoryButton);
        gameHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();
                GameHistoryFragment fragment = new GameHistoryFragment();
                fragment.show(fm,"game_History_fragment");
            }
        });
        chatroom = (Button) findViewById(R.id.chatButton);
        chatroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();
                ChatFragment fragment = new ChatFragment();
                fragment.show(fm,"chat_fragment");

            }
        });

        demo = (Button) findViewById(R.id.demoButton);
        demo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int testCases = 1;
                switch(count){ //TODO: HERE ARE THE HARDCODED TEST CASES! ENJOY!
                    //YOU ROCK
                    case 0:
                        FragmentManager fm = getSupportFragmentManager();
                        Fragment fragment = fm.findFragmentById(R.id.activity_map);

                        if (fragment == null) {
                            fragment = new DestinationCardFragment();
                            FragmentTransaction transaction = fm.beginTransaction();
                            transaction.replace(R.id.activity_map, fragment);
                            transaction.commit();
                        }
                        CModel.getInstance().updateCurrGameHistoryList("NEW HISTORY");
                        CModel.getInstance().updateCurrGameHistoryList("HISTORY IN THE MAKING");
                        break;
                    case 1:
                        //TODO: FIX IT SO THAT IT WILL WORK WHEN WE PRESS DEMO.  RIGHT NOW ITS JUST IN THE ONDRAW
                        //DrawRouteAsync task = new DrawRouteAsync();
                        //task.execute();
                        CModel.getInstance().updateCurrGameHistoryList("NEW HISTORY");
                        break;
                    case 2:
                        CModel.getInstance().updateCurrGameHistoryList("HISTORY IN THE MAKING");
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                    case 8:
                        break;
                    case 9:
                        break;
                    case 10:
                        break;
                    case 11:
                        break;
                    case 12:
                        break;
                    case 13:
                        break;
                    case 14:
                        break;
                    case 15:
                        break;


                    default:
                        break;
                }
                //Change this counter when we add more test cases. Basically caps the number of cases we will use
                count++;
            }
        });

        setupView();
        //start poller
        //Poller.getInstance().getCommandList();
    }

    private class DrawRouteAsync extends AsyncTask<Void, Void, Integer>
    {
        MapBaseView mapBaseView;
        @Override
        protected Integer doInBackground(Void... params)
        {
            mapBaseView = (MapBaseView) findViewById(R.id.map_base_view);

            return 0;
        }
        @Override
        protected void onPostExecute(Integer integer)
        {
            super.onPostExecute(integer);
            mapBaseView.claimRoute(mapBaseView.LasVegas, mapBaseView.Omaha, "blue");
            mapBaseView.claimRoute(mapBaseView.Phoenix, mapBaseView.Denver, "red");
            mapBaseView.claimRoute(mapBaseView.LasVegas, mapBaseView.SaltLakeCity, "blue");
            mapBaseView.claimRoute(mapBaseView.NewYork, mapBaseView.Nashville, "green");
            mapBaseView.claimRoute(mapBaseView.LosAngeles, mapBaseView.SanFrancisco, "yellow");
            mapBaseView.claimRoute(mapBaseView.Nashville, mapBaseView.Omaha, "black");
        }
    }



    private void setupView() {
        Log.d(TAG,"setupView");
        /* Create the Presenter; Set the Presenter as a interface to limit communication*/
        presenter = new MapPresenter(this);
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
