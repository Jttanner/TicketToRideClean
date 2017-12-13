/**
 * Created by jontt on 10/9/2017.
 */

public class StartGameCommandData extends Command {
    private String gameID;

    public StartGameCommandData(String game){
        setType("startGame");
        this.gameID = game;
    }
    public StartGameCommandData(){

    }
    public String getGame() {
        return gameID;
    }

    public void setGame(String game) {
        this.gameID = game;
    }
}
