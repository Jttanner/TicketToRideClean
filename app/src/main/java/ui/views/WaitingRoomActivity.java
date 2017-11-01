package ui.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import Adapters.PlayerListAdapter;
import MVP_coms_classes.MVP_WaitingRoom;
import clientModel.CModel;
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
    private Button ChatRoomButton;
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
        //Poller.getInstance().stopPoller();
        //Poller.getInstance().stopGetCommandsPoller();
        super.onDestroy();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG,"OnCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waitingroom);
        setupMVP();
        wireUp();
        //Poller.getInstance().updateGameList();
        Poller.getInstance().getCommandList();

    }

    private void setupMVP() {
        Log.d(TAG,"setupMVP");
        /* Create the Presenter; Set the Presenter as a interface to limit communication*/
        mPresenter = new WaitingRoomPresenter(this);
        playerListAdapter = new PlayerListAdapter(this,CModel.getInstance().getCurrGame().getPlayers());
    }

    @Override
    public void goToMapFailed() {
        Toast.makeText(this,"Need more players to start a game",Toast.LENGTH_LONG).show();
    }

    @Override
    public void goToMapActivity() {
        Intent intent = new Intent(this, MapActivity.class);
        mPresenter.deleteObserver();
        startActivity(intent);
    }

    void wireUp(){
        Log.d(TAG,"wireUp");
        // StartGameButton = (Button) findViewById(R.id.StartGameButton);
        StartGameButton = (Button) findViewById(R.id.waitingRoom_StartGame);
        StartGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Poller.getInstance().stopPoller();
                mPresenter.startGame();

            }
        });
        ChatRoomButton = (Button) findViewById(R.id.waitingRoom_Chat_Room);
        ChatRoomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();
                ChatFragment fragment = new ChatFragment();
                fragment.show(fm,"chat_fragment");

            }
        });

        playerListView = (ListView) findViewById(R.id.waitingRoom_PlayerList);
        playerListView.setAdapter(playerListAdapter);
    }


    @Override
    /*Handles the updating of the playerlist in the listview
     * @param Game THe game that may or may not have changed*/
    public void updateWaitingRoom(Game game) {
        Log.d(TAG,"updateWaitingRoom");
        playerListAdapter.setPlayerList(game.getPlayers());
        playerListAdapter.notifyDataSetChanged();
    }

}
