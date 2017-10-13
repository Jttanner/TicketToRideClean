package modeling;

import java.util.List;
import java.util.Map;

import modeling.Game;

/**
 * Created by tyler on 10/8/2017.
 */

public class GameList {

    private List<Game> games;
    private Map<String, Game> gameIDtoGame; //TODO: Should already have the gameID contained within the game though...

    public boolean addGame(Game newGame){
        if (newGame != null) {
            games.add(newGame);
            return true;
        }
        return false;
    }

    public boolean joinGame(User user, String gameID){
        Game existingGame = findGame(gameID);
        if (existingGame.getPlayers().size() >= existingGame.getPlayerMax()){
            return false;
        }
        if (!existingGame.isHasStarted()){
            Player newPlayer = new Player(user.getUserName());
            switch (existingGame.getPlayers().size()){
                case 0:
                    newPlayer.setColor("Red");
                    break;
                case 1:
                    newPlayer.setColor("Green");
                    break;
                case 2:
                    newPlayer.setColor("Black");
                    break;
                case 3:
                    newPlayer.setColor("Blue");
                    break;
                case 4:
                    newPlayer.setColor("Yellow");
                    break;
                default:
                    break;
            }
            newPlayer.setPlayerName(user.getInfo().getUserName());
            existingGame.addPlayer(newPlayer);
            //ServerModel.getInstance().getGamesAsMap().put(foundGame.getGameID(),foundGame); //TODO: Don't need this since it's already in the map
            user.addPlayer(newPlayer);
            user.addGame(existingGame);
            return true;
        }
        return false;
    }

    public Game findGame(String gameID){
        return gameIDtoGame.get(gameID); //TODO: Will this return the game that I want that's associated with GameID?
    }

    public GameList(List<Game> games) {
        this.games = games;
    }

    public List<Game> getGames() {
        return games;
    }

}
