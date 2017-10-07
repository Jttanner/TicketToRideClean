package ui.views;

import android.app.Fragment;

import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.List;

import Adapters.GameListAdapter;
import MVP_coms_classes.MVP_GameList;
import commandData.GetGameListCommandData;
import modeling.Game;
import poller.Poller;
import presenters.GameListPresenter;
import teamjapannumbahone.tickettoride.R;

/**
 * Created by LabUser on 10/2/2017.
 */

public class GameListActivity extends AppCompatActivity implements MVP_GameList.GameListActivityInterface {
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
        GameListPresenter.initiazlizePoller();
    }

    void wireUp(){
       // StartGameButton = (Button) findViewById(R.id.StartGameButton);
        CreateGameButton = (Button) findViewById(R.id.CreateGameButton);
        CreateGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //not yet
                //MVP_GameList.GameListPresenterInterface presenter = new GameListPresenter();


            }
        });
        JoinGameButton = (Button) findViewById(R.id.JoinGameButton);
        recyclerView = (RecyclerView) findViewById(R.id.GameListRecycler);
        JoinGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();
                android.support.v4.app.Fragment fragment = fm.findFragmentById(R.id.activity_login);
                if(fragment == null){
                    fragment = new CreateGameFragment();
                    fm.beginTransaction().add(R.id.fragment_spot,fragment).commit();
                }
            }
        });

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
