package result;


/**
 * Created by jontt on 10/13/2017.
 */

public class JoinGameCommandResult extends CommandResult {

    private String gameId;

    public JoinGameCommandResult(String gameId){
        super();
        this.gameId = gameId;
    }

    public String getGameId() {
        return gameId;
    }
}
