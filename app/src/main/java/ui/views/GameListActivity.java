package ui.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.List;

import Adapters.GameListAdapter;
import MVP_coms_classes.MVP_GameList;
import clientModel.CModel;
import modeling.Game;
import poller.Poller;
import teamjapannumbahone.tickettoride.R;

/**
 * Created by LabUser on 10/2/2017.
 */

public class GameListActivity extends FragmentActivity implements MVP_GameList.GameListActivityInterface {
    //Button StartGameButton;
    Button CreateGameButton;
    Button JoinGameButton;

    RecyclerView recyclerView;
    RecyclerView.Adapter radapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamelist);
        wireUp();
        //GameListPresenter.initiazlizePoller();
        Poller poller = Poller.getInstance();
        poller.updateGameList();
    }

    void wireUp(){
       // StartGameButton = (Button) findViewById(R.id.StartGameButton);
        CreateGameButton = (Button) findViewById(R.id.CreateGameButton);
        CreateGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //not yet
                //MVP_GameList.GameListPresenterInterface presenter = new GameListPresenter();
                FragmentManager fm = getSupportFragmentManager();
                CreateGameFragment fragment = new CreateGameFragment();
                fragment.show(fm,"fragment_creategame");

            }
        });
        JoinGameButton = (Button) findViewById(R.id.JoinGameButton);
        recyclerView = (RecyclerView) findViewById(R.id.GameListRecycler);
        JoinGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        radapter = new GameListAdapter(CModel.getInstance().getAllGames());
    }

    @Override
    public void UpdateList(List<Game> list) {
        if(list != null){
            radapter = new GameListAdapter(list);
            recyclerView.setAdapter(radapter);
        }
    }

    @Override
    public void JoinGameResult() {
        //this is where we go to the next activity
    }

    @Override
    public void ToggleButton(boolean startGame, boolean joinGame) {
        //StartGameButton.setEnabled(startGame);
        JoinGameButton.setEnabled(joinGame);
    }
}
