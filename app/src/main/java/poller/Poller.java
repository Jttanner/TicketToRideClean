package poller;

import android.os.AsyncTask;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

import clientModel.CModel;
import commandData.GetGameListCommandData;
import modeling.GameList;
import servercomms.ServerProxy;

/**
 * Created by Hwang on 10/6/2017.
 */

public class Poller {

    CModel clientModel = CModel.getInstance();
    //URL URL;
    GetGameListCommandData command;
    //Timer needs to be static or else it will cancel another instance of the Timer class
    private static Timer timer;
    private final String TAG = "Poller";

    private static Poller instance = new Poller();

    private Poller(){
        try{
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

    public void doEverything(){
        updateGameList();
    }
    public void FetchChat(){


    }
    public void updateGameList() {
        timer = new Timer();
        //final Handler handler = new Handler();
        TimerTask doAsynchronousTask = new TimerTask() {
            @Override
            public void run() {
                //handler.post(new Runnable() {
                UpdateLobby updateLobby = new UpdateLobby();
                // PerformBackgroundTask this class is the class that extends AsynchTask
                updateLobby.execute();
                //updatechat
            }
        };
        timer.schedule(doAsynchronousTask,0,3000); //execute in every 3000 ms
    }

    public void getCommands(){
        TimerTask doAsyoncronousTask = new TimerTask() {
            @Override
            public void run() {
                //handler.post(new Runnable() {
                //UpdateLobby updateLobby = new UpdateLobby();
                // PerformBackgroundTask this class is the class that extends AsynchTask
                //updateLobby.execute();
            }
        };
    }

    public void stopPoller(){
        if(timer != null){
            timer.cancel();
            timer = null;
        }
    }



    private class UpdateLobby extends AsyncTask<Void, Void, Integer>
    {
        private GameList gameList;

        @Override
        protected Integer doInBackground(Void... params)
        {
            //TODO: Push the request to the serverProxy

            ServerProxy serverProxy = ServerProxy.getInstance();
            try{
                serverProxy.sendCommand(command);
            }catch (Exception e){
                Log.d(TAG,e.getMessage());
                e.printStackTrace();
            }
            return 0;
        }

        @Override
        protected void onPostExecute(Integer integer)
        {
            super.onPostExecute(integer);
        }
    }
}
