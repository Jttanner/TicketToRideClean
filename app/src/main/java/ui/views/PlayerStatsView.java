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

import Adapters.StatusAdapter;
import MVP_coms_classes.MVP_PlayerStats;
import modeling.Game;
import presenters.PlayerStatsPresenter;
import teamjapannumbahone.tickettoride.R;

/**
 * Created by tyler on 10/20/2017.
 */

public class PlayerStatsView extends RelativeLayout implements MVP_PlayerStats.ViewOps{
    private RecyclerView mGameStatus;
    private TextView mTextView;
    private StatusAdapter statusAdapter;
    private MVP_PlayerStats.PresOps presenter;
    private Context myParentContext;
    private String statNames;
    private  AttributeSet attrs;
    private View myView;



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
        statNames = a.getString(
                R.styleable.PlayerStatsView_android_text);
        /*Log.i("test",a.getString(
                R.styleable.PlayerStatsView_android_text));*/
        /*Log.i("test",""+a.getColor(
                R.styleable.PlayerStatsView_android_textColor, Color.BLACK));
        Log.i("test",a.getString(
                R.styleable.PlayerStatsView_extraInformation));*/

        a.recycle();


    }



    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mGameStatus = (RecyclerView) myView.findViewById(R.id.recycler_status);
        mTextView = (TextView) myView.findViewById(R.id.statusNames);
        mTextView.setText(statNames.replaceAll("\\n","\n"));
        presenter = new PlayerStatsPresenter(this);
        //setup recycler
        statusAdapter = new StatusAdapter(myParentContext,presenter.getPlayerColumns());
        LinearLayoutManager manager = new LinearLayoutManager(myParentContext);
        mGameStatus.setLayoutManager(manager);
        mGameStatus.setAdapter(statusAdapter);
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
        statusAdapter.setPlayerColumns(presenter.getPlayerColumns());
        statusAdapter.notifyDataSetChanged();
    }
}
