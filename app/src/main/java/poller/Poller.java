package poller;

import android.os.AsyncTask;
import android.util.Log;

import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import clientModel.CModel;
import commandData.GetGameListCommandData;
import modeling.Player;
import result.GameList;
import result.PlayerList;
import servercomms.ClientFacade;
import servercomms.ServerProxy;

/**
 * Created by Hwang on 10/6/2017.
 */

public class Poller {

    CModel clientModel = CModel.getInstance();
    URL URL;
    GetGameListCommandData command;
    Timer timer = new Timer();
    private final String TAG = "Poller";
    boolean active = false;

    private static Poller instance = new Poller();

    private Poller(){
        try{
            this.URL = new URL("http://192.168.2.134:8080/user/command");
            command = new GetGameListCommandData();
            command.setType("getGameList");
            active = true;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Poller getInstance()
    {
        if(instance == null){
            instance = new Poller();
        }
        return instance;
    }

    public void updateGameList() {
        if (!active){
            timer = new Timer();
        }
        //final Handler handler = new Handler();
        TimerTask doAsynchronousTask = new TimerTask() {
            @Override
            public void run() {
                //handler.post(new Runnable() {
                UpdateLobby updateLobby = new UpdateLobby();
                // PerformBackgroundTask this class is the class that extends AsynchTask
                updateLobby.execute();
            }
        };
        timer.schedule(doAsynchronousTask,0,3000); //execute in every 3000 ms
    }

    public void updatePlayerList(){
        TimerTask doAsyoncronousTask = new TimerTask() {
            @Override
            public void run() {
                //handler.post(new Runnable() {
                UpdateLobby updateLobby = new UpdateLobby();
                // PerformBackgroundTask this class is the class that extends AsynchTask
                updateLobby.execute();
            }
        };
    }

    public void stopPoller(){
        if(timer != null && active){
            timer.cancel();
            active = false;
        }
    }



    public class UpdateLobby extends AsyncTask<Void, Void, Integer>
    {
        private GameList gameList;

        @Override
        protected Integer doInBackground(Void... params)
        {
            //TODO: Push the request to the serverProxy

            ServerProxy serverProxy = ServerProxy.getInstance();
            try{
                gameList = (GameList) serverProxy.getGameList(URL, command);
                //clientModel.setAllGames(serverProxy.getGameList(URL, command));
            }catch (Exception e){
                Log.d(TAG,e.getMessage());
                e.printStackTrace();
            }
            return 0;
        }

        @Override
        protected void onPostExecute(Integer integer)
        {
            //TODO: Receive the response from the Proxy
            super.onPostExecute(integer);
            ClientFacade.getInstance().updateGameList(gameList);
        }
    }

    public class UpdatePlayers extends AsyncTask<Void, Void, Integer>
    {
        private PlayerList playerList;

        @Override
        protected Integer doInBackground(Void... params)
        {
            //TODO: Push the request to the serverProxy

            ServerProxy serverProxy = ServerProxy.getInstance();
            try{
                //CModel cModel = CModel.getInstance();
                //playerList = cModel.getAllPlayers();
                playerList = (PlayerList) serverProxy.getGameList(URL, command);
                //clientModel.setAllGames(serverProxy.getGameList(URL, command));
            }catch (Exception e){
                Log.d(TAG,e.getMessage());
                e.printStackTrace();
            }
            return 0;
        }

        @Override
        protected void onPostExecute(Integer integer)
        {
            //TODO: Receive the response from the Proxy
            super.onPostExecute(integer);
            ClientFacade.getInstance().updatePlayerList(playerList);
        }
    }
}
