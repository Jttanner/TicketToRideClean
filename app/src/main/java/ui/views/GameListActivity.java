/*
package ui.views;

import android.content.Intent;
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
import presenters.GameListPresenter;
import teamjapannumbahone.tickettoride.R;

*/
/**
 * Created by LabUser on 10/2/2017.
 *//*


public class GameListActivity extends FragmentActivity implements MVP_GameList.GameListActivityInterface {
    //Button StartGameButton;
    Button CreateGameButton;
    Button JoinGameButton;
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
        radapter = new GameListAdapter(CModel.getInstance().getAllGames(),presenter);
        recyclerView.setAdapter(radapter);
    }

    @Override
    public void UpdateList(List<Game> list) {
        if(list != null){
            //This will do stuffs
            List<Game> games = radapter.getGames();
            for(Game g : list){
                if(!(games.contains(g))){
                    games.add(g);
                }
            }

            radapter.setList(games);
            radapter.notifyDataSetChanged();
            //radapter = new GameListAdapter(games,presenter);
            //recyclerView.setAdapter(radapter);
        }
    }

    @Override
    public void JoinGameResult() {
        Intent intent = new Intent(this,WaitingRoomActivity.class);
        startActivity(intent);
        //this is where we go to the next activity
    }

    @Override
    public void ToggleButton(boolean startGame, boolean joinGame) {
        //StartGameButton.setEnabled(startGame);
        JoinGameButton.setEnabled(joinGame);
    }
}
*/
package ui.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Adapters.ExpandableListAdapter;
import MVP_coms_classes.MVP_GameList;
import clientModel.CModel;
import modeling.Game;
import poller.Poller;
import presenters.GameListPresenter;
import teamjapannumbahone.tickettoride.R;

public class MainActivity extends Activity implements MVP_GameList.GameListActivityInterface {
    Button CreateGameButton;
    Button JoinGameButton;
    MVP_GameList.GameListPresenterInterface presenter;
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // preparing list data
        presenter = new GameListPresenter(this);
        wireUp();
        prepareListData();

        // setting list adapter
        expListView.setAdapter(listAdapter);


        Poller poller = Poller.getInstance();
        poller.updateGameList();
    }

    void wireUp(){
        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);
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
        //recyclerView = (RecyclerView) findViewById(R.id.GameListRecycler);
        JoinGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //radapter = new GameListAdapter(CModel.getInstance().getAllGames(),presenter);
        //recyclerView.setAdapter(radapter);
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        // Adding child data
        listDataHeader.add("Games");

        // Adding child data
        List<String> gameList = new ArrayList<String>();
        for (Game g : CModel.getInstance().getAllGames()){
            gameList.add(g.toString());
        }

        listDataChild.put(listDataHeader.get(0), gameList); // Header, Child data
    }

    private void updateGameList(List<Game> list){
        // Adding child data
        List<String> gameList = new ArrayList<String>();
        for (Game g : list){
            gameList.add(g.toString());
        }
        listDataChild.put(listDataHeader.get(0), gameList);
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public void UpdateList(List<Game> list) {
        if(list != null){
            listDataChild.put(listDataHeader.get(0), updateGameList(list)); // Header, Child data
        }
    }

    @Override
    public void JoinGameResult() {
        Intent intent = new Intent(this,WaitingRoomActivity.class);
        startActivity(intent);
        //this is where we go to the next activity
    }

    @Override
    public void ToggleButton(boolean startGame, boolean joinGame) {
        //StartGameButton.setEnabled(startGame);
        JoinGameButton.setEnabled(joinGame);
    }
}
