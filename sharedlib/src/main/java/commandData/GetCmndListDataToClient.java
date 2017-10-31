package commandData;

import modeling.CommandList;

/**
 * Created by tyler on 10/23/2017.
 * This class takes the GameCommandMap and sends it to the client
 */

public class GetCmndListDataToClient extends Command{

    private CommandList returnCommandList;
    private String gameId;
    //takes in a map and wraps it up to send
    public GetCmndListDataToClient(CommandList commandList,String gameID) {
        setType("getCommandList");
        this.gameId = gameID;
        returnCommandList = commandList;
//        this.commandList = new GameCommandMap(returnCommandList);
    }


//    public GameCommandMap getCommandList() {
//        return commandList;
//    }

    public CommandList getReturnCommandList() {
        return returnCommandList;
    }

    public void setReturnCommandList(CommandList returnCommandList) {
        this.returnCommandList = returnCommandList;
    }

    public String getGameId() {
        return gameId;
    }
}
