package ServerModel;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import modeling.Game;
import modeling.Player;
import modeling.User;
import modeling.UserInfo;
import result.*;
import request.*;

/**
 * Created by jontt on 9/27/2017.
 */

public class ServerFacade {


    Map<String, User> users = new HashMap<>();//Key=UserName
    Map<String, Game> games = new HashMap<>();//Key=gameID

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

        try{
            UserInfo check = users.get(request.getUserName()).getInfo();
            return  check.checkUserInfo(request);
        }catch (NullPointerException e){
            return new LoginResult(false, "failed", "failed");
        }

    }

    public RegisterResult register(RegisterRequest request){
        String userName = request.getUserName();
        String password = request.getPassword();
        String newUserID = UUID.randomUUID().toString();
        User newUser = new User(new UserInfo(userName, password, newUserID));
        if (request.getUserName() != null && request.getPassword() != null){

            users.put(userName, newUser);
            return new RegisterResult(true, userName,"Successfully Registered.");
        } else{
            return new RegisterResult(false, userName, "Failed to Register.");
        }
    }

    public void createGame(User creator){
        try{
            Game game = new Game();
            Player creatorPlayer = new Player(creator.getUserID());
            game.addPlayer(creatorPlayer);
            creator.addPlayer(creatorPlayer);
            creator.addGame(game);
            games.put(game.getGameID(), game);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void joinGame(User user, Game game){
        try{
            if (games.containsKey(game.getGameID())){
                Game foundGame = games.get(game.getGameID());
                if (foundGame.canJoinGame()){
                    Player newPlayer = new Player(user.getUserID());
                    foundGame.addPlayer(newPlayer);
                    user.addPlayer(newPlayer);
                    user.addGame(foundGame);
                }else{
                    //don't add
                    //return game is full somehow
                    //TODO: talk about how to propegate these errors.  create exception classes?  or just check?
                }
            }
        } catch (Exception e){
            //catch if theres a bad user or game
            e.printStackTrace();
        }
    }

    public Map<String, Game> getGameList(){
        return games;
    }


}
