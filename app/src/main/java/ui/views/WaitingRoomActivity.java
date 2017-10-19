package ui.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import Adapters.PlayerListAdapter;
import MVP_coms_classes.MVP_WaitingRoom;
import clientModel.CModel;
import commandData.StartGameCommandData;
import modeling.Game;
import poller.Poller;
import presenters.WaitingRoomPresenter;
import teamjapannumbahone.tickettoride.R;

/**
 * Created by korea on 10/6/2017.
 */

public class WaitingRoomActivity extends AppCompatActivity implements MVP_WaitingRoom.RequiredViewOps {
    private static final String TAG = "WaitingRoomActivity";
    private Context mContext = this;
    private Button StartGameButton;
    private MVP_WaitingRoom.RequiredPresenterOps mPresenter;
    private ListView playerListView;
    private PlayerListAdapter playerListAdapter;// = new PlayerListAdapter(this,CModel.getInstance().getCurrGame().getPlayers());

    @Override
    public Context getAppContext() {
        return getApplicationContext();
    }

    @Override
    public Context getActivityContext() {
        return mContext;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Poller.getInstance().stopPoller();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG,"OnCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waitingroom);
        setupMVP();
        wireUp();
        Poller.getInstance().updateGameList();

    }

    private void setupMVP() {
        Log.d(TAG,"setupMVP");
        /* Create the Presenter; Set the Presenter as a interface to limit communication*/
        mPresenter = new WaitingRoomPresenter(this);
        playerListAdapter = new PlayerListAdapter(this,CModel.getInstance().getCurrGame().getPlayers());
    }
    void wireUp(){
        Log.d(TAG,"wireUp");
        // StartGameButton = (Button) findViewById(R.id.StartGameButton);
        StartGameButton = (Button) findViewById(R.id.waitingRoom_StartGame);
        StartGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CModel cModel = CModel.getInstance();
                Game currentGame =cModel.getCurrGame();
                StartGameCommandData startGameCommandData = new StartGameCommandData(currentGame);
                mPresenter.startGame(startGameCommandData);
                //Check if it is valid. Go to new activity
                //TODO: implment way to check if game started successfully
                Intent intent = new Intent(getApplicationContext(),MapActivity.class);
                startActivity(intent, null);

            }
        });
        playerListView = (ListView) findViewById(R.id.waitingRoom_PlayerList);
        playerListView.setAdapter(playerListAdapter);
    }
    @Override
    /**Handles the updating of the playerlist in the listview
     * @param Game THe game that may or may not have changed*/
    public void updateWaitingRoom(Game game) {
        Log.d(TAG,"updateWaitingRoom");
        playerListAdapter.setPlayerList(game.getPlayers());
        playerListAdapter.notifyDataSetChanged();
        //playerListAdapter = new PlayerListAdapter(this,game.getPlayers());
        //playerListView.setAdapter(playerListAdapter);
    }

}
