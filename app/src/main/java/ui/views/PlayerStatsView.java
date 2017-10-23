package ui.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import Adapters.StatusAdapter;
import MVP_coms_classes.MVP_PlayerStats;
import clientModel.MyColor;
import modeling.Game;
import presenters.PlayerStatsPresenter;
import teamjapannumbahone.tickettoride.R;

/**
 * Created by tyler on 10/20/2017.
 */

public class PlayerStatsView extends RelativeLayout implements MVP_PlayerStats.ViewOps{
    /**The recycler view for the game stats*/
    private RecyclerView mGameStatus;
    /**The textview which holds the column names for the recycler views info*/
    private TextView mTextView;
    /**The adapter for the recycler*/
    private StatusAdapter statusAdapter;
    /**Our presenter that we can call*/
    private MVP_PlayerStats.PresOps presenter;
    /**Our context*/
    private Context myParentContext;
    /**The head stat column strings*/
    private String statNames;
    /**Set of attributes for this view*/
    private  AttributeSet attrs;
    /**Our view of this class*/
    private View myView;
    /**Textviews holding num of user's cards*/
    Map<MyColor,TextView> colorNumMap = new HashMap<>();



    public PlayerStatsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        myParentContext = context;
        this.attrs = attrs;
        init();
    }

    public PlayerStatsView(Context context) {
        super(context);
        myParentContext = context;
        init();
    }

    public PlayerStatsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        myParentContext = context;
        init();
    }

    /**Initializes the view an sets up the presenter/view relationship*/
    private void init() {
        myView = inflate(getContext(),R.layout.player_stat_view,this);

        TypedArray a=getContext().obtainStyledAttributes(this.attrs, R.styleable.PlayerStatsView);
        //Use a
        statNames = a.getString(R.styleable.PlayerStatsView_android_text);

        a.recycle();


    }

    /**Sets the text of a particular textview that is tied to a resource card color
     * @param color THe MyColor Key
     * @param numOfThisColor The number of this kind of resource cards we have*/
    @Override
    public void setMyTextView(MyColor color, int numOfThisColor) {
        TextView textView = colorNumMap.get(color);
        //set the textview to that number
        textView.setText(String.valueOf(numOfThisColor));
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        setupTrains();
        mGameStatus = (RecyclerView) myView.findViewById(R.id.recycler_status);
        mTextView = (TextView) myView.findViewById(R.id.statusNames);
        mTextView.setText(statNames.replaceAll("\\t","\t"));
        presenter = new PlayerStatsPresenter(this);
        //setup recycler
        statusAdapter = new StatusAdapter(myParentContext,presenter.getPlayerColumns());
        LinearLayoutManager manager = new LinearLayoutManager(myParentContext);
        mGameStatus.setLayoutManager(manager);
        mGameStatus.setAdapter(statusAdapter);
        presenter.setCardNumbers(colorNumMap);
    }

    private void setupTrains() {
        TextView redNum = (TextView)myView.findViewById(R.id.rednum);
        TextView blueNum = (TextView)myView.findViewById(R.id.bluenum);
        TextView blackNum = (TextView)myView.findViewById(R.id.blacknum);
        TextView greenNum = (TextView)myView.findViewById(R.id.greennum);
        TextView whiteNum = (TextView)myView.findViewById(R.id.whitenum);
        TextView yellowNum = (TextView)myView.findViewById(R.id.yellownum);
        TextView rainbowNum = (TextView)myView.findViewById(R.id.rainbownum);
        TextView purpleNum = (TextView)myView.findViewById(R.id.purplenum);
        TextView orangeNum = (TextView)myView.findViewById(R.id.orangenum);
        colorNumMap.put(MyColor.RED,redNum);
        colorNumMap.put(MyColor.BLUE,blueNum);
        colorNumMap.put(MyColor.BLACK,blackNum);
        colorNumMap.put(MyColor.GREEN,greenNum);
        colorNumMap.put(MyColor.WHITE,whiteNum);
        colorNumMap.put(MyColor.YELLOW,yellowNum);
        colorNumMap.put(MyColor.WILD,rainbowNum);
        colorNumMap.put(MyColor.ORANGE,orangeNum);
        colorNumMap.put(MyColor.PURPLE,purpleNum);



    }

    @Override
    public Context getAppContext() {
        return this.getContext().getApplicationContext();
    }

    @Override
    public Context getActivityContext() {
        return this.getContext();
    }

    @Override
    public void updatePlayerStats(Game g) {
        presenter.setCardNumbers(colorNumMap);
        statusAdapter.setPlayerColumns(presenter.getPlayerColumns());
        statusAdapter.notifyDataSetChanged();
    }
}
