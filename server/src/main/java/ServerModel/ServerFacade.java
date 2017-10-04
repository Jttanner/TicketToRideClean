package ServerModel;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
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
            if (check.checkUserInfo(request)){
                String userName = request.getUserName();
                return new LoginResult(true,"login success!", userName, users.get(userName));
            } else{
                return new LoginResult(false, "login failed.", "", null);
            }
        }catch (NullPointerException e){
            return new LoginResult(false, "login failed.", "", null);
        }

    }

    public RegisterResult register(RegisterRequest request){
        String userName = request.getUserName();
        String password = request.getPassword();
        String newUserID = UUID.randomUUID().toString();
        User newUser = new User(new UserInfo(userName, password, newUserID));
        if (request.getUserName() != null && request.getPassword() != null){
            users.put(userName, newUser);
            return new RegisterResult(true, userName,"Successfully Registered.", newUser);
        } else{
            return new RegisterResult(false, userName, "Failed to Register.", null);
        }
    }

    public boolean createGame(Game newGame){
        try{
            Game game = newGame;
            games.put(game.getGameID(), game);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
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

    public boolean deleteGame(Game game){
        try{
            String gameID = game.getGameID();
            if (games.containsKey(gameID)){
                games.remove(games.get(gameID));
                return  true;
            } else{
                return false;
            }

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public Map<String, Game> getGameList(){
        return games;
    }


}
