package ui.views;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import MVP_coms_classes.MVP_Map;
import clientModel.CModel;
import modeling.Player;
import modeling.ResourceCard;
import modeling.Route;
import poller.Poller;
import presenters.MapPresenter;
import teamjapannumbahone.tickettoride.R;

/**
 * Activity which is the base for the game itself.  Holds the gameboard as the main view, with a sliding drawer for
 * to hold data relevant to the game for each player. Other views relevant to the game stem from this.
 */
public class MapActivity extends FragmentActivity implements MVP_Map.MapViewOps, Observer{
    private static final String TAG = "MapActivity";
    private RecyclerView mGameStatus;
    public MVP_Map.MapPresOps presenter;
//    private Button destinationCardButton;
    private Button chatroom;
    private Button gameHistory;
//    private Button drawResourceCardButton;
//    private Button claimRouteButton;
    private Button turnStartOption;

    private ImageButton drawableDeck;


    /**
     * If we leave this activity, we want to stop the poller which polls the command list.
     * @pre Our instance of Poller is running a GetCommandsPoll
     * @post Our instance of Poller is no longer running a GetCommandsPoll
     */
    @Override
    protected void onDestroy() {
        Poller.getInstance().stopGetCommandsPoller();
        super.onDestroy();
    }

    /**
     * Initializes the game by setting up the view for the game.
     * Also sets up demo button functions which updates the views appopriately.
     *
     * @param savedInstanceState could be used to save a state, not implemented yet.
     * @pre Game has just started
     * @post Game and view for the Game are both completely initialized
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        //RelativeLayout layout = (RelativeLayout) findViewById(R.id.activity_map);

        changeTurnDisplay();


        ((TextView) findViewById(R.id.num_cards_in_deck)).setText("Number of Cards in Deck: 136");

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


        turnStartOption = (Button) findViewById(R.id.turnStartOptionButton);
        turnStartOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();
                TurnStartOptionFragment fragment = new TurnStartOptionFragment();
                fragment.show(fm, "start");
            }
        });
        setupView();


    }

    /**
     * Updates the TextView which states whose turn it currently is
     * @pre List<Player> in current Game object is not null
     * @pre only one Player in List<Player> in current game has isMyTurn = true
     * @post the Player whos isMyTurn = true now = false, the player after him has isMyTurn = true.
     *               (If it was the last Player on the list's turn, the Player at the beginning of the list sets isMyTurn = true)
     */
    public void changeTurnDisplay(){
        for (Player player : CModel.getInstance().getCurrGame().getPlayers()){
            if (player.isMyTurn()){
                ((TextView) findViewById(R.id.current_turn)).setText("Current Turn: " + player.getPlayerName());
            }
        }
    }

    /**
     * Initializes the face up resource cards which can be drawn.
     * These will be drawn one at at time from the top of the deck.
     * @pre Game is in initialization phase
     * @post drawable cards are visually updated
     */
//    private void initializeFaceUpCards(){
//        for (int i = 0; i < 5; ++i){
//            ResourceCard card = CModel.getInstance().getCurrGame().getResourceCardList().drawCard();
//            int drawableCardIndex = 0;
//            switch (i){
//                case 0:
//                    drawableCardIndex = R.id.first_drawable_card;
//                    break;
//                case 1:
//                    drawableCardIndex = R.id.second_drawable_card;
//                    break;
//                case 2:
//                    drawableCardIndex = R.id.third_drawable_card;
//                    break;
//                case 3:
//                    drawableCardIndex = R.id.fourth_drawable_card;
//                    break;
//                case 4:
//                    drawableCardIndex = R.id.fifth_drawable_card;
//                    break;
//                default:
//                    break;
//            }
//            ((ImageButton) findViewById(drawableCardIndex)).setImageResource(getTrainColorPictureID(card.getMyColor()));
//        }
//        drawableDeck.setImageResource(R.drawable.backcard);
//    }

    /**
     * @pre color = any of: "black", "purple", "white", "blue", "yellow", "green", "red", "orange", or "wild"
     *
     * @param color color of the resource card we want
     * @return The ID to be referenced to find the image of the correct traincar drawable
     */
    private int getTrainColorPictureID(String color){
        switch (color.toLowerCase()){
            case "black":
                return R.drawable.blacktrain;
            case "purple":
                return R.drawable.purpletrain;
            case "white":
                return R.drawable.whitetrain;
            case "blue":
                return R.drawable.bluetrain;
            case "yelllow":
                return R.drawable.yellowtrain;
            case "green":
                return R.drawable.greentrain;
            case "red":
                return R.drawable.redtrain;
            case "orange":
                return R.drawable.orangetrain;
            default:
                return R.drawable.wildtrain;
        }
    }

    /**
     * Helper function
     * Draws a Player's color on a route claimed that Player.
     *
     * @pre the Connection object which specifies the connections between cityName1 and cityName2 has a canClaim == true;
     *
     * @post Claming Player's color has been drawn over the route he claimed if it was appopriate.
     *
     * @param cityName1 Name of first city on a route
     * @param cityName2 Name of connected city on a route
     * @param color Color of the player who is claiming the route
     * @param isDoubleRoute true if the route has more than one connection between the two cities, false otherwise
     * @param hasOneDoubleClaimed true if isDoubleRoute == true AND one of double connections has been claimed.
     */
    private void drawClaimedRoute(String cityName1, String cityName2, String color, boolean isDoubleRoute, boolean hasOneDoubleClaimed){
        MapBaseView mapBaseView = (MapBaseView) findViewById(R.id.map_base_view);
        Map<String, Point> cities = mapBaseView.cityMap;
        mapBaseView.addClaimedRoute(cities.get(cityName1.toLowerCase()), cityName1.toLowerCase(), cities.get(cityName2.toLowerCase()), cityName2.toLowerCase(), color, isDoubleRoute, hasOneDoubleClaimed);
    }

    /**
     * Initializes the MapPResenter
     *
     * @pre Activity initialization state
     * @post MapPresenter has been initialized
     */
    private void setupView() {
        Log.d(TAG,"setupView");
        /* Create the Presenter; Set the Presenter as a interface to limit communication*/
        presenter = new MapPresenter(this);
    }


    /**
     * @pre Claimed RouteList in the model has changed
     * @post Newly claimed routes have been drawn on the board
     */
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

    /**
     * Getter for Application Context
     *
     * @return Context of the application
     */
    @Override
    public Context getAppContext() {
        return getApplicationContext();
    }


    /**
     * Getter for MapActicty Context
     *
     * @return the MapActivity Context
     */
    @Override
    public Context getActivityContext() {
        return this;
    }

    @Override
    public void ResourceCardOption() {
        FragmentManager fm = getSupportFragmentManager();
        DrawResourceCardFragment fragment = new DrawResourceCardFragment();
        fragment.show(fm,"resource_card_fragment");
    }

    @Override
    public void DestinationCardOption() {
        FragmentManager fm = getSupportFragmentManager();
        DestinationCardFragment fragment = new DestinationCardFragment();
        fragment.show(fm,"destination_card_fragment");
    }

    @Override
    public void ClaimRouteOption() {
        FragmentManager fm = getSupportFragmentManager();
        ClaimRouteFragment fragment = new ClaimRouteFragment();
        fragment.show(fm,"claim_route_fragment");
    }

    @Override
    public void update(Observable o, Object arg) {
        //Don't think I need this anymore - Kwan
    }
}
