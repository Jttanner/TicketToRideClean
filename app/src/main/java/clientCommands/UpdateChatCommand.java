package clientCommands;

import java.util.ArrayList;
import java.util.List;

import clientModel.CModel;
import commandData.ChatCommandData;
import modeling.Game;

/**
 * Created by ACL1 on 10/28/2017.
 */

public class UpdateChatCommand implements ClientCommand {

    List<String> chatHistory;
    String GameID;
    public UpdateChatCommand(ChatCommandData chatCommandData){
        chatHistory = chatCommandData.getChatHistory();
        GameID = chatCommandData.getGameId();
    }
    @Override
    public void execute() {
        CModel.getInstance().UpdateHistory(chatHistory,GameID);
    }
}
