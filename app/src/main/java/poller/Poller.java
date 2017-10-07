package poller;

import android.os.AsyncTask;

import java.util.Timer;
import java.util.TimerTask;

import clientModel.CModel;
import modeling.User;
import servercomms.ClientFacade;
import servercomms.ServerProxy;

/**
 * Created by Hwang on 10/6/2017.
 */

public class Poller {

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
            //serverProxy.getGameList(URL, command);
            return null;
        }

        @Override
        protected void onPostExecute(Integer integer)
        {
            //TODO: Receive the response from the Proxy
            super.onPostExecute(integer);
            ClientFacade clientFacade = ClientFacade.getInstance();

        }
    }
}
