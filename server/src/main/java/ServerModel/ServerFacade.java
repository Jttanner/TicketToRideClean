package ServerModel;

import java.awt.color.CMMException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import command.ClaimDestinationCardCommand;
import commandData.ClaimDestinationCardCommandData;
import commandData.ClaimRouteCommandData;
import commandData.Command;
import commandData.DrawDestinationCardCommandData;
import commandData.EndTurnCommandData;
import modeling.CommandList;
import modeling.DestinationCard;
import modeling.Game;
import modeling.GameList;
import modeling.Player;
import modeling.Route;
import modeling.User;
import request.LoginRequest;
import request.RegisterRequest;
import result.CommandResult;
import result.LoginResult;
import result.RegisterResult;

/**
 * Created by jontt on 9/27/2017.
 */

public class ServerFacade {

    /*
    *The organizer/task distributer of the server: Receives the variables from the commands and sends them through to the Model
    * Receives the results from the model and pushes it back to the client.
     */

    private static ServerFacade instance = null;
    private static ServerModel serverModel = ServerModel.getInstance();

    public static ServerFacade getInstance()
    {
        if(instance == null){
            instance = new ServerFacade();
        }
        return instance;
    }

    public LoginResult login(LoginRequest request){
        User user = serverModel.login(request.getUserName(), request.getPassword());
        if(user != null && user.getInfo() != null){
            return new LoginResult(true, "login success!",user);
        }
        else{
            return new LoginResult(false, "login failed.");
        }
    }

    public void addChat(String s,String gameID){

        ServerModel.getInstance().addChatHistory(s,gameID);
//        ChatCommandData chatCommandData = new ChatCommandData( ServerModel.getInstance().getGamesAsMap().get(gameID).getChatHistory(),gameID);
//        ServerModel.getInstance().getReturnCommand().add(chatCommandData);
//        addCommandToList(gameID,chatCommandData);


    }

    public RegisterResult register(RegisterRequest request){
        User user = serverModel.register(request.getUserName(), request.getPassword());
        if (user != null){
            return new RegisterResult(true, "Successfully Registered", user);
        }
        else{
            return new RegisterResult(false, "Failed to Register.", null);
        }
    }


    public boolean createGame(Game newGame){
        return serverModel.createGame(newGame);
    }


    public Game joinGame(User user, String gameID)
    {
        return serverModel.joinGame(user, gameID);
    }


    public void startGame(String game){ //TODO: The poller should be constantly checking if the game has started...what do we want to do with startgame?
        for(Game thisgame : serverModel.getGames().getGames()){
            if(thisgame.getGameID().equals(game)){
                thisgame.setHasStarted(true);
            }

        }
    }


    public boolean deleteGame(Game game){
        return serverModel.deleteGame(game);
    }


    public GameList getGameList(){
        return serverModel.getGames();
    }

    /**Adds command to the correct commandList
     * @param gameID THe game id key*/
    public void addCommandToList(String gameID, Command command){
        Map<String, List<Command>> commandListMap = serverModel.getCommandListMap();
        if(!commandListMap.containsKey(gameID))
            commandListMap.put(gameID, new ArrayList<Command>());
        commandListMap.get(gameID).add(command);

    }

    public List<DestinationCard> getDestinationCardList(DrawDestinationCardCommandData data) {
        Game currGame = ServerModel.getInstance().getGames().findGame(data.getGameID());
        return currGame.getDestinationCardList().get3Cards();
    }
/*
    public List<DestinationCard> distributeUsedDestinationCards(ClaimDestinationCardCommandData data) {
        Game currGame = ServerModel.getInstance().getGames().findGame(data.getGameID());
        Player currPlayer = currGame.getPlayer(data.getPlayerID());
        //currGame.getDestinationCardList().distributeUsedDestinationCards(data);
        List<DestinationCard> claimedCards = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            boolean isClaimed = data.getClaimDestinationCards().get(i).isClaimed();
            if (isClaimed) {
                claimedCards.add(data.getClaimDestinationCards().get(i));
            } else {
                currGame.getDestinationCardList().addDestinationCardBackToDeck(data.getClaimDestinationCards().get(i));
            }
        }
        currPlayer.addDestinationCards(claimedCards);
        return claimedCards;
    }
*/
    public CommandResult distributeUsedDestinationCards(ClaimDestinationCardCommandData data) {
        Game currGame = ServerModel.getInstance().getGames().findGame(data.getGameID());
        Player currPlayer = currGame.getPlayer(data.getPlayerID());
        //currGame.getDestinationCardList().distributeUsedDestinationCards(data);
        List<DestinationCard> claimedCards = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            boolean isClaimed = data.getClaimDestinationCards().get(i).isClaimed();
            if (isClaimed) {
                claimedCards.add(data.getClaimDestinationCards().get(i));
            } else {
                currGame.getDestinationCardList().addDestinationCardBackToDeck(data.getClaimDestinationCards().get(i));
            }
        }
        currPlayer.addDestinationCards(claimedCards);
        return new CommandResult(true);
    }


    public CommandResult claimRoute(ClaimRouteCommandData data){
        String checkWildColor = data.isWild() ? "Wild" : data.getRouteColor();
        Game currGame = ServerModel.getInstance().getGames().findGame(data.getGameID());
        if (currGame.claimAvailableRoute(new Route(data.getStartCity(), data.getEndCity(), checkWildColor, data.getDistance()),
                                     currGame.getPlayer(data.getPlayerName()), data.isWild())){
            //addCommandToList(data.getGameID(), data);

            if(currGame.isLastRound()){
               if( currGame.FinalCountDown()){
                   Command endGame = new Command();
                   endGame.setType("EndGame");
                   addCommandToList(currGame.getGameID(),endGame);
               }
            }
            if(!currGame.isLastRound()&&(currGame.getPlayer(data.getPlayerName()).getTrainCarList().getNumOfCars()<=2)){

                currGame.setLastRound(true,currGame.getPlayers().size());
                currGame.FinalCountDown();
            }
            return new CommandResult(true);
        } else{
            return new CommandResult(false);
        }
    }
    /**Handles the ending of player's turns
     * @param gameID The game we are dealing with*/
    public void endTurn(String gameID) {
        Game game = ServerModel.getInstance().getGames().findGame(gameID);
        if(game != null) {
            game.advancePlayerTurn();
        }
    }


/*
    public boolean endTurn(EndTurnCommandData commandData) {
        return serverModel.endTurn(commandData);
    }*/
}
