package ui.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.List;

import Adapters.GameOverListAdapter;
import MVP_coms_classes.MVP_GameOver;
import clientModel.CModel;
import clientModel.LongestRouteCalc;
import modeling.Player;
import poller.Poller;
import presenters.GameListPresenter;
import teamjapannumbahone.tickettoride.R;

/**
 * Created by ACL1 on 11/12/2017.
 */

public class GameOverActivity extends FragmentActivity implements MVP_GameOver.GameOverActivity{

    TextView WinningInfo;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameover);
        SetUp();
    }

    private void SetUp(){
        WinningInfo = (TextView) findViewById(R.id.Winning_Info);


        recyclerView = (RecyclerView) findViewById(R.id.gameoverlist);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        recyclerView.setLayoutManager(layoutManager);
        GameOverListAdapter adapter = new GameOverListAdapter(CModel.getInstance().getCurrGame().getPlayers());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void SetUpWinScreen(List<Player> players) {

    }

    @Override
    public void SetLongestRoute(List<Player> players) {
        LongestRouteCalc calc = new LongestRouteCalc();
        Player player = calc.findLongestRoute(players);

        String longestPath = player.getPlayerName() + "has won the game with the longest path with "
                + player.getPoints() + " points";
        WinningInfo.setText(longestPath);
    }
}
