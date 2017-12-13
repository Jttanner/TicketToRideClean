
package ui.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.List;

import Adapters.GameListAdapter;
import MVP_coms_classes.MVP_GameList;
import clientModel.CModel;
import modeling.Game;
import modeling.Player;
import poller.Poller;
import presenters.GameListPresenter;
import teamjapannumbahone.tickettoride.R;

/**
 * Created by LabUser on 10/2/2017.
 */


public class GameListActivity extends FragmentActivity implements MVP_GameList.GameListActivityInterface {
    Button CreateGameButton;
    MVP_GameList.GameListPresenterInterface presenter;
    RecyclerView recyclerView;
    GameListAdapter radapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamelist);
        presenter = new GameListPresenter(this);

        wireUp();
        Poller poller = Poller.getInstance();
        poller.updateGameList();

        checkUp();
    }

    void checkUp(){
        Player player = CModel.getInstance().getUserPlayer();
        if(player!=null) {
            for (Game game : CModel.getInstance().getAllGames()) {
                for (Player each : game.getPlayers()) {
                    if (player.getPlayerName().equals(each.getPlayerName())) {
                        Poller.getInstance().stopPoller();
                        CModel.getInstance().setCurrGame(game);
                        JoinGameResult(game);
                    }
                }
            }
        }
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
        recyclerView = (RecyclerView) findViewById(R.id.GameListRecycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);

        radapter = new GameListAdapter(CModel.getInstance().getAllGames(),presenter);
        recyclerView.setAdapter(radapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Poller.getInstance().stopPoller();
    }

    @Override
    public void UpdateList(List<Game> list) {
        if(list != null){
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

            recyclerView.setLayoutManager(linearLayoutManager);
            radapter = new GameListAdapter(list, presenter);
            recyclerView.setAdapter(radapter);
        }
    }

    /**This activity starts the waiting room activity*/
    @Override
    public void JoinGameResult(Game game) {
        //Poller.getInstance().stopPoller();
        presenter.deleteObserver();
        Intent intent = new Intent(this,WaitingRoomActivity.class);
        startActivity(intent);
        //this is where we go to the next activity
    }
}
