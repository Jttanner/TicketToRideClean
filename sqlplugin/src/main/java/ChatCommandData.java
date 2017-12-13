import java.util.List;

/**
 * Created by ACL1 on 10/21/2017.
 */

public class ChatCommandData extends Command {

    String chatString;
    List<String> chatHistory;
    String gameId;

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public List<String> getChatHistory() {
        return chatHistory;
    }

    public void setChatHistory(List<String> chatHistory) {
        this.chatHistory = chatHistory;
    }

    public ChatCommandData(List<String> list,String gameId){
        this.gameId = gameId;
        chatHistory = list;
        setType("addChat");
    }
    public ChatCommandData(){}
    public ChatCommandData(String s,String gameId){
        this.gameId = gameId;
        chatString = s;
        setType("addChat");
    }

    public String getChatString() {
        return chatString;
    }

    public void setChatString(String chatString) {
        this.chatString = chatString;
    }
}
