package ui.views;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
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
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import MVP_coms_classes.MVP_Map;
import clientModel.CModel;
import modeling.DestinationCard;
import modeling.Player;
import modeling.ResourceCard;
import modeling.Route;
import poller.Poller;
import presenters.MapPresenter;
import teamjapannumbahone.tickettoride.R;

public class MapActivity extends FragmentActivity implements MVP_Map.MapViewOps{
    private static final String TAG = "MapActivity";
    private RecyclerView mGameStatus;
    public MVP_Map.MapPresOps presenter;
    private Button destinationCard;
    private Button chatroom;
    private Button gameHistory;
    private Button demo;

    private ImageButton firstDrawableTrainCard;
    private ImageButton secondDrawableTrainCard;
    private ImageButton thirdDrawableTrainCard;
    private ImageButton fourthDrawableTrainCard;
    private ImageButton fifthDrawableTrainCard;
    private ImageButton drawableDeck;


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

        firstDrawableTrainCard = (ImageButton) findViewById(R.id.first_drawable_card);

        firstDrawableTrainCard.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

            }
        });

        firstDrawableTrainCard.setImageResource(R.drawable.blacktrain);

        secondDrawableTrainCard = (ImageButton) findViewById(R.id.second_drawable_card);

        secondDrawableTrainCard.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

            }
        });

        secondDrawableTrainCard.setImageResource(R.drawable.bluetrain);

        thirdDrawableTrainCard = (ImageButton) findViewById(R.id.third_drawable_card);

        thirdDrawableTrainCard.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

            }
        });

        thirdDrawableTrainCard.setImageResource(R.drawable.orangetrain);

        fourthDrawableTrainCard = (ImageButton) findViewById(R.id.fourth_drawable_card);

        fourthDrawableTrainCard.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

            }
        });

        fourthDrawableTrainCard.setImageResource(R.drawable.purpletrain);

        fifthDrawableTrainCard = (ImageButton) findViewById(R.id.fifth_drawable_card);

        fifthDrawableTrainCard.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

            }
        });

        fifthDrawableTrainCard.setImageResource(R.drawable.wildtrain);

        drawableDeck = (ImageButton) findViewById(R.id.drawable_deck);

        drawableDeck.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

            }
        });

        drawableDeck.setImageResource(R.drawable.backcard);


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
                        CModel.getInstance().updateCurrGameHistoryList("NEW HISTORY");
                        break;
                    case 2:
                        CModel.getInstance().updateCurrGameHistoryList("HISTORY IN THE MAKING");
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

    public void initializeFaceUpCards(){
        for (int i = 0; i < 5; ++i){
            ResourceCard card = CModel.getInstance().getCurrGame().getResourceCardList().drawCard();
            int drawableCardIndex = 0;
            switch (i){
                case 0:
                    drawableCardIndex = R.id.first_drawable_card;
                    break;
                case 1:
                    drawableCardIndex = R.id.second_drawable_card;
                    break;
                case 2:
                    drawableCardIndex = R.id.third_drawable_card;
                    break;
                case 3:
                    drawableCardIndex = R.id.fourth_drawable_card;
                    break;
                case 4:
                    drawableCardIndex = R.id.fifth_drawable_card;
                    break;
                default:
                    break;
            }
            ((ImageButton) findViewById(drawableCardIndex)).setImageResource(getTrainColorPictureID(card.getMyColor()));
        }
    }

    private int getTrainColorPictureID(String color){
        switch (color.toLowerCase()){
            case "black":
                return R.drawable.blacktrain;
        }
        return 0;
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
