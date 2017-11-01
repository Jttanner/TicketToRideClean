package ui.views;

import android.content.Context;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.EdgeEffectCompat;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import MVP_coms_classes.MVP_Map;
import clientModel.CModel;
import commandData.ClaimRouteCommandData;
import commandData.DrawTrainCardDeckCommandData;
import commandData.DrawTrainCardFaceUpCommandData;
import modeling.DestinationCard;
import modeling.Player;
import modeling.Route;
import poller.Poller;
import presenters.MapPresenter;
import servercomms.ServerProxy;
import teamjapannumbahone.tickettoride.R;

public class MapActivity extends FragmentActivity implements MVP_Map.MapViewOps{
    private static final String TAG = "MapActivity";
    private RecyclerView mGameStatus;
    public MVP_Map.MapPresOps presenter;
    private Button destinationCard;
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


        destinationCard = (Button) findViewById(R.id.destinationCardButton);
        destinationCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();
                DestinationCardFragment fragment = new DestinationCardFragment();
                fragment.show(fm, "fragment_destinationcard");
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
                       /* FragmentManager fm = getSupportFragmentManager();
                        Fragment fragment = fm.findFragmentById(R.id.activity_map);

                        if (fragment == null) {
                            fragment = new DestinationCardFragment();
                            FragmentTransaction transaction = fm.beginTransaction();
                            transaction.replace(R.id.activity_map, fragment);
                            transaction.commit();
                        }*/
                        break;
                    case 1:
                        //COMMAND HISTORY
                        String color = "GREEN";
                        String color2 = "RED";
                        String gameID = CModel.getInstance().getCurrGame().getGameID();
                        String startCity = "Salt Lake City";
                        String endCity = "Seoul";

                        DrawTrainCardDeckCommandData X = new DrawTrainCardDeckCommandData(gameID, color);
                        ServerProxy.getInstance().sendCommand(X);

                        ClaimRouteCommandData Z = new ClaimRouteCommandData(gameID, startCity, endCity);
                        ServerProxy.getInstance().sendCommand(Z);

                        DrawTrainCardFaceUpCommandData Y = new DrawTrainCardFaceUpCommandData(gameID, color2);
                        ServerProxy.getInstance().sendCommand(Y);

                        CModel.getInstance().updateCurrGameHistoryList("NEW HISTORY", gameID);
                        //COMMAND HISTORY
                        break;
                    case 2:
                        CModel.getInstance().updateCurrGameHistoryList("HISTORY IN THE MAKING", CModel.getInstance().getCurrGame().getGameID());
                        break;
                    case 3:
                        //TODO: FIX IT SO THAT IT WILL WORK WHEN WE PRESS DEMO.  RIGHT NOW ITS JUST IN THE ONDRAW
                        Toast.makeText(getApplicationContext(), "Red Player claiming route from Las Vegas to Salt Lake City...", Toast.LENGTH_LONG).show();
                        CModel.getInstance().updateRoutes(CModel.getInstance().getCurrGame(), new Route("Las Vegas", "Salt Lake City", "purple", 5), CModel.getInstance().getUserPlayer());
                        // drawClaimedRoute("Las Vegas", "Salt Lake City", "blue", false, false);
                        //mapBaseView.addClaimedRoute(mapBaseView.LasVegasPoint, "Las Vegas", mapBaseView.SLCPoint, "Salt Lake City", "blue", false, false);
                        break;
                    case 4:
                        /*
                        I Make mock player objects to simulate drawing different colors for different players with our demo button.
                         */
                        Toast.makeText(getApplicationContext(), "Blue Player claiming route from Kansas City to St. Louis...", Toast.LENGTH_LONG).show();
                        Player mockBluePlayer = new Player("mockBluePlayer", "mockBluePlayer", "blue");
                        CModel.getInstance().updateRoutes(CModel.getInstance().getCurrGame(), new Route("Kansas City", "Saint Louis", "purple", 5, true), mockBluePlayer);
                        //drawClaimedRoute("Kansas City", "Saint Louis", "blue", true, false);
                        //mapBaseView.addClaimedRoute(mapBaseView.KansasCityPoint, "Kansas City", mapBaseView.SaintLouisPoint, "St. Louis", "red", true, false);
                        break;
                    case 5:
                        Player mockGreenPlayer = new Player("mockGreenPlayer", "mockGreenPlayer", "Green");
                        Toast.makeText(getApplicationContext(), "Green Player claiming first available route from Chicago to Pittsburgh...", Toast.LENGTH_LONG).show();
                        CModel.getInstance().updateRoutes(CModel.getInstance().getCurrGame(), new Route("Chicago", "Pittsburgh", "blue", 4, true), mockGreenPlayer);
                        //drawClaimedRoute("Chicago", "Pittsburgh", "green", true, false);
                        //mapBaseView.addClaimedRoute(mapBaseView.ChicagoPoint, "Chicago", mapBaseView.PittsburghPoint, "Pittsburgh", "green", true, false);
                        break;
                    case 6:
                        Player mockYellowPlayer = new Player("mockYellowPlayer", "mockYellowPlayer", "Yellow");
                        Toast.makeText(getApplicationContext(), "Yellow Player claiming second available route from Chicago to Pittsburgh...", Toast.LENGTH_LONG).show();
                        CModel.getInstance().updateRoutes(CModel.getInstance().getCurrGame(), new Route("Chicago", "Pittsburgh", "wild", 6, true), mockYellowPlayer);
                        //drawClaimedRoute("Chicago", "Pittsburgh", "yellow", true, true);
                        //mapBaseView.addClaimedRoute(mapBaseView.ChicagoPoint, "Chicago", mapBaseView.PittsburghPoint, "Pittsburgh", "yellow", true, true);
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

    public void drawClaimedRoute(String cityName1, String cityName2, String color, boolean isDoubleRoute, boolean hasOneDoubleClaimed){
        MapBaseView mapBaseView = (MapBaseView) findViewById(R.id.map_base_view);
        Map<String, Point> cities = mapBaseView.cityMap;
        mapBaseView.addClaimedRoute(cities.get(cityName1.toLowerCase()), cityName1.toLowerCase(), cities.get(cityName2.toLowerCase()), cityName2.toLowerCase(), color, isDoubleRoute, hasOneDoubleClaimed);
    }


    private void setupView() {
        Log.d(TAG,"setupView");
        /* Create the Presenter; Set the Presenter as a interface to limit communication*/
        presenter = new MapPresenter(this);
    }

    @Override
    public void updateMap() {
        Map<Route, Player> routeList = CModel.getInstance().getClaimedRouteList().getRoutesMap();

        for (Map.Entry<Route, Player> entry : routeList.entrySet())
        {
            Route route = entry.getKey();
            Player player = entry.getValue();
            drawClaimedRoute(route.getFirstCityName(), route.getSecondCityName(), player.getColor(), route.getIsDouble(), !route.getFirstOfDouble());
        }
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
