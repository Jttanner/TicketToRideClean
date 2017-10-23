package command;

import ServerModel.ServerFacade;
import commandData.ChatCommandData;
import result.CommandResult;

/**
 * Created by ACL1 on 10/22/2017.
 */

public class AddChatCommand extends ChatCommandData implements ICommand {

    public AddChatCommand(ChatCommandData chatCommandData){
        super(chatCommandData.getChatString());

    }

    @Override
    public CommandResult execute() {

        ServerFacade.getInstance().addChat(getChatString());
        return null;
    }
}
