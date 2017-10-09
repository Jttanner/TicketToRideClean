package poller;

import android.os.AsyncTask;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import clientModel.CModel;
import commandData.GetGameListCommandData;
import modeling.Game;
import modeling.User;
import servercomms.ClientFacade;
import servercomms.ServerProxy;

/**
 * Created by Hwang on 10/6/2017.
 */

public class Poller {

    CModel clientModel = CModel.getInstance();
    String URL;
    GetGameListCommandData command;

    public Poller(String URL){
        this.URL = URL;
        command = new GetGameListCommandData();
        command.setType("getGameList");
    }

    public void updateGameList() {
        //final Handler handler = new Handler();
        Timer timer = new Timer();
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

    public class UpdateLobby extends AsyncTask<Void, Void, Integer>
    {
        @Override
        protected Integer doInBackground(Void... params)
        {
            //TODO: Push the request to the serverProxy
            ServerProxy serverProxy = ServerProxy.getInstance();
            try{
                clientModel.setAllGames(serverProxy.getGameList(new URL(URL), command));
            }catch (MalformedURLException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Integer integer)
        {
            //TODO: Receive the response from the Proxy
            super.onPostExecute(integer);


        }
    }
}
