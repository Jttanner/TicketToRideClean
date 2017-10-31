package poller;

import android.os.AsyncTask;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

import clientModel.CModel;
import commandData.Command;
import commandData.GetCmndDataFromServer;
import commandData.GetGameListCommandData;
import servercomms.ServerProxy;

/**
 * Created by Hwang on 10/6/2017.
 */

public class Poller {

    CModel clientModel = CModel.getInstance();
    //URL URL;
    Command command;
    //Timer needs to be static or else it will cancel another instance of the Timer class
    private static Timer timer,getCommandTimer;
    private final String TAG = "Poller";
    /**Our Async task*/
    private Poll sendCommand;
    private static Poller instance = new Poller();

    private Poller(){
        Log.d(TAG,"Making Poller Object");
    }

    public static Poller getInstance()
    {
        if(instance == null){
            instance = new Poller();
        }
        return instance;
    }

    /*public void doEverything(){
        updateGameList();
    }*/
    public void FetchChat(){


    }
    /**Method to call in order to have the poller the game list*/
    public void updateGameList() {
        Log.d(TAG,"updatingGameList");
        timer = new Timer();
        command = new GetGameListCommandData();
        //makes the Timer task
        TimerTask doAsynchronousTask = new TimerTask() {
            @Override
            public void run() {
                Log.d(TAG,"Going into AsynTask");
                sendCommand = new Poll();
                sendCommand.execute();
            }
        };
        timer.schedule(doAsynchronousTask,0,3000); //execute in every 3000 ms

    }

    /**Method to call in order to have the poller get the command list*/
    public void getCommandList(){
        Log.d(TAG,"getting command list");
         getCommandTimer = new Timer();
        command = new GetCmndDataFromServer(CModel.getInstance().getCurrGame().getGameID());
        //makes the TimerTask
        TimerTask doAsynchronousTask = new TimerTask() {
            @Override
            public void run() {
                Log.d(TAG,"Going into AsynTask");
                sendCommand = new Poll();
                sendCommand.execute();
            }
        };
        getCommandTimer.schedule(doAsynchronousTask,0,3000); //execute in every 3000 ms
    }

    /**Stops the GetCommandsPoller from running*/
    public void stopGetCommandsPoller(){
        if(getCommandTimer != null){
            Log.d(TAG,"Stopping getCommand poller");
            getCommandTimer.cancel();
            getCommandTimer = null;
        }
    }
    /**Stops the GetGameList poller from working*/
    public void stopPoller(){
        if(timer != null){
            Log.d(TAG,"Stopping getGamelist poller");
            timer.cancel();
            timer = null;
        }
    }

    private class Poll extends AsyncTask<Void, Void, Integer>
    {
        @Override
        protected Integer doInBackground(Void... params)
        {
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
