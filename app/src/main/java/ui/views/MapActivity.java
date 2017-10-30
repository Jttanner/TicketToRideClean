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
import android.widget.Toast;

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
    int counter = 0;


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

                MapBaseView mapBaseView = (MapBaseView) findViewById(R.id.map_base_view);

                switch(counter){ //TODO: HERE ARE THE HARDCODED TEST CASES! ENJOY!
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
                        break;
                    case 1:
                        CModel.getInstance().updateCurrGameHistoryList("NEW HISTORY");
                        break;
                    case 2:
                        CModel.getInstance().updateCurrGameHistoryList("HISTORY IN THE MAKING");
                        break;
                    case 3:
                        //TODO: FIX IT SO THAT IT WILL WORK WHEN WE PRESS DEMO.  RIGHT NOW ITS JUST IN THE ONDRAW
                        Toast.makeText(getApplicationContext(), "Blue Player claiming route from Las Vegas to Salt Lake City...", Toast.LENGTH_LONG).show();
                        mapBaseView.addClaimedRoute(mapBaseView.LasVegasPoint, "Las Vegas", mapBaseView.SLCPoint, "Salt Lake City", "blue", false, false);
                        break;
                    case 4:
                        Toast.makeText(getApplicationContext(), "Red Player claiming route from Kansas City to St. Louis...", Toast.LENGTH_LONG).show();
                        mapBaseView.addClaimedRoute(mapBaseView.KansasCityPoint, "Kansas City", mapBaseView.SaintLouisPoint, "St. Louis", "red", true, false);
                        break;
                    case 5:
                        Toast.makeText(getApplicationContext(), "Green Player claiming first available route from Chicago to Pittsburgh...", Toast.LENGTH_LONG).show();
                        mapBaseView.addClaimedRoute(mapBaseView.ChicagoPoint, "Chicago", mapBaseView.PittsburghPoint, "Pittsburgh", "green", true, false);
                        break;
                    case 6:
                        Toast.makeText(getApplicationContext(), "Yellow Player claiming second available route from Chicago to Pittsburgh...", Toast.LENGTH_LONG).show();
                        mapBaseView.addClaimedRoute(mapBaseView.ChicagoPoint, "Chicago", mapBaseView.PittsburghPoint, "Pittsburgh", "yellow", true, true);
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
                counter++;
            }
        });

        setupView();

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
