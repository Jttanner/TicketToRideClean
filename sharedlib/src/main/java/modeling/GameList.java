package modeling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tyler on 10/8/2017.
 */

public class GameList {

    private List<Game> games = new ArrayList<>();
    private List<Game> gamesStarted = new ArrayList<>();
    private Map<String, Game> gameIDtoGame = new HashMap<>();

    public void setGames(List<Game> games){
        this.games = games;
    }

    /**Called when the game has been created and sent serverSide
     * @param newGame The new game object
     * @return Boolean*/
    public boolean addGame(Game newGame){
        if (newGame != null && !games.contains(newGame)) {
            games.add(newGame);
            gameIDtoGame.put(newGame.getGameID(),newGame);
            return true;
        }

        return false;
    }

    public Game joinGame(User user, String gameID){
        Game existingGame = findGame(gameID);
        if (existingGame.canJoinGame()){ //Checks the player max
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
            //newPlayer.setPlayerName(user.getInfo().getUserName());
            existingGame.addPlayer(newPlayer);
            user.addPlayer(newPlayer);
            user.addGame(existingGame);

        }
        return existingGame;
    }

    /*public boolean startGame(String gameID){
        //Find the game from the list of games
        //Place that same game in the list of gamesStarted
        //Delete the game from the general list of games
        Game serverGame = gameIDtoGame.get(gameID);

//        Game serverGame = games.find
        //int minPlayersNeeded = 2;
        //TODO uncomment this when neeeded, keeping it commented right now for testing purposes
        if(false){
        //if(serverGame.equals(game)){
       // if(serverGame.getGameID().equals(gameID)){ //&& serverGame.getPlayers().size() > minPlayersNeeded){
            serverGame.setHasStarted(true);
            gamesStarted.add(serverGame);
            games.remove(serverGame);
            return true;
        }
        return false;
    }*/

    public boolean deleteGame(Game game){
        for(Game gameFound : games) {
            if (gameFound.equals(game)) {
                games.remove(game);
                return true;
            }
        }
        for(Game gameFound : gamesStarted){
            if (gameFound.equals(game)){
                gamesStarted.remove(game);
                return true;
            }
        }
        return false;
    }

    /*
    public boolean leaveGame(Game game, Player player){
        for(Game gameFound : games){
            if(gameFound.equals(game)){
                gameFound.removePlayer(player);
            }
        }
        for(Game gameFound : gamesStarted){
            if(gameFound.equals(game)){
                gameFound.removePlayer(player);
            }
        }
    }*/

    public Game findGame(String gameID){

        return gameIDtoGame.get(gameID); //TODO: Will this return the game that I want that's associated with GameID?
    }

    public GameList() {

    }

    public List<Game> getGames() {
        return games;
    }

    //TODO: implement public boolean startGame(Game)

}
