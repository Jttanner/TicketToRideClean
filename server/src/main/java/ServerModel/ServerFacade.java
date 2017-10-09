package ServerModel;

import java.util.Map;
import java.util.UUID;

import modeling.Game;
import modeling.Player;
import modeling.User;
import modeling.UserInfo;
import request.LoginRequest;
import request.RegisterRequest;
import result.LoginResult;
import result.RegisterResult;

/**
 * Created by jontt on 9/27/2017.
 */

public class ServerFacade {

    private static ServerFacade instance = null;

    public static ServerFacade getInstance()
    {
        if(instance == null){
            instance = new ServerFacade();
        }
        return instance;
    }

    public LoginResult login(LoginRequest request){

        try{
            UserInfo check = ServerModel.getInstance().getUsers().get(request.getUserName()).getInfo();
            if (check.checkUserInfo(request)){
                String userName = request.getUserName();
                return new LoginResult(true,"login success!", userName, ServerModel.getInstance().getUsers().get(userName));
            } else{
                return new LoginResult(false, "login failed.");
            }
        }catch (NullPointerException e){
            return new LoginResult(false, "login failed.");
        }

    }

    public RegisterResult register(RegisterRequest request){
        String userName = request.getUserName();
        String password = request.getPassword();
        String newUserID = UUID.randomUUID().toString();
        User newUser = new User(new UserInfo(userName, password, newUserID));
        if (request.getUserName() != null && request.getPassword() != null){
            ServerModel.getInstance().getUsers().put(userName, newUser);
            return new RegisterResult(true, userName,"Successfully Registered.", newUser);
        } else{
            return new RegisterResult(false, userName, "Failed to Register.", null);
        }
    }

    public boolean createGame(Game newGame){
        try{
            ServerModel.getInstance().getGames().put(newGame.getGameID(), newGame);
            System.out.println(ServerModel.getInstance().getGames().get(newGame.getGameID()).getGameID());
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public void joinGame(User user, Game game){
        try{
            if (ServerModel.getInstance().getGames().containsKey(game.getGameID())){
                Game foundGame = ServerModel.getInstance().getGames().get(game.getGameID());
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
            if (ServerModel.getInstance().getGames().containsKey(game.getGameID())){
                ServerModel.getInstance().getGames().remove(game.getGameID());
                return  true;
            } else{
                return false;
            }

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean leaveGame(Game game, Player player){
        try{
            if (ServerModel.getInstance().getGames().containsKey(game.getGameID())){
                Game thisGame = ServerModel.getInstance().getGames().get(game.getGameID());
                thisGame.removePlayer(player);
            } else{
                return false;
            }
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public Map<String, Game> getGameList(){
        return ServerModel.getInstance().getGames();
    }


}
