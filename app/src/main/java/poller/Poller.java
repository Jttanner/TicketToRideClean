package poller;

import android.os.AsyncTask;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

import clientModel.CModel;
import commandData.GetGameListCommandData;
import result.GameList;
import servercomms.ServerProxy;

/**
 * Created by Hwang on 10/6/2017.
 */

public class Poller {

    CModel clientModel = CModel.getInstance();
    //URL URL;
    GetGameListCommandData command;
    Timer timer = new Timer();
    private final String TAG = "Poller";

    private static Poller instance = new Poller();

    private Poller(){
        try{
            //this.URL = new URL("http://10.24.71.220:8080/user/command");
            command = new GetGameListCommandData();
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
        if(timer != null){
            timer.cancel();
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
                serverProxy.sendCommand(command);
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
           // ClientFacade.getInstance().updateGameList(gameList);
        }
    }
}
