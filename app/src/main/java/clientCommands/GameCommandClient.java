package clientCommands;

import java.util.List;

import clientModel.CModel;
import commandData.GameCommandData;
import commandData.ResetCommandIndexData;
import modeling.Game;
import modeling.GameList;
import modeling.Player;
import servercomms.ServerProxy;

/**
 * Created by tyler on 12/13/2017.
 */

public class GameCommandClient implements ClientCommand {
    private GameCommandData gameCommandData;

    public GameCommandClient(GameCommandData cmd) {
        gameCommandData = cmd;
    }

    @Override
    public void execute() {
        List<Game> games = CModel.getInstance().getAllGames();
        if(games != null){
            for(Game game : games){
                if(game.getGameID().equals(gameCommandData.getGame().getGameID())){
                    game = gameCommandData.getGame();
                    for(Player player : game.getPlayers()){
                        if(player.getPlayerName().equals(CModel.getInstance().getMyUser().getUserName())){
                            CModel.getInstance().setCurrGame(game);
                        }
                    }
                }
            }
        }
        GameList gamelist = new GameList();
        gamelist.setGames(games);
        CModel.getInstance().setAllGames(gamelist);
        ResetCommandIndexClient cmd= new ResetCommandIndexClient();
        cmd.execute();
        ServerProxy.getInstance().sendCommand(new ResetCommandIndexData(gameCommandData.getGame().getGameID()));
    }

}
