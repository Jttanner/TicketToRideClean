package presenters;

import java.lang.ref.WeakReference;
import java.util.Observable;
import java.util.Observer;

import MVP_coms_classes.MVP_Chat;
import MVP_coms_classes.MVP_DestCard;
import MVP_coms_classes.MVP_GameList;
import clientModel.CModel;
import commandData.ChatCommandData;
import modeling.Game;
import servercomms.ServerProxy;

/**
 * Created by ACL1 on 10/28/2017.
 */

public class ChatPresenter implements MVP_Chat.ChatPresenterOps,Observer {

    private WeakReference<MVP_Chat.ChatViewOps> myView;

    public ChatPresenter(MVP_Chat.ChatViewOps view){
        myView = new WeakReference<MVP_Chat.ChatViewOps>( view );
        CModel.getInstance().addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof String){
            if(((String) arg).equals("addChat"))
            myView.get().UpdateChatView();
        }

    }

    @Override
    public void UpdateChatServer(String current) {
        Game currentgame = CModel.getInstance().getCurrGame();
        ChatCommandData data = new ChatCommandData(current,currentgame.getGameID());
        ServerProxy.getInstance().sendCommand(data);
    }
}
