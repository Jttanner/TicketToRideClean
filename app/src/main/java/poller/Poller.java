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
    URL URL;
    GetGameListCommandData command;

    private static Poller instance = new Poller();

    private Poller(){
        try{
            this.URL = new URL("http://192.168.0.7:8080/user/command");
            command = new GetGameListCommandData();
            command.setType("getGameList");
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
        private List<Game> gameList;

        @Override
        protected Integer doInBackground(Void... params)
        {
            //TODO: Push the request to the serverProxy

            ServerProxy serverProxy = ServerProxy.getInstance();
            try{
                gameList = serverProxy.getGameList(URL, command);
                //clientModel.setAllGames(serverProxy.getGameList(URL, command));
            }catch (Exception e){
                e.printStackTrace();
            }
            return 0;
        }

        @Override
        protected void onPostExecute(Integer integer)
        {
            //TODO: Receive the response from the Proxy
            super.onPostExecute(integer);
            clientModel.setAllGames(gameList);
        }
    }
}
