package ServerModel;

import modeling.Game;
import modeling.GameList;
import modeling.User;
import request.LoginRequest;
import request.RegisterRequest;
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
/*
    public LoginResult login(LoginRequest request){
        UserInfoList userInfoList = new UserInfoList();
        UserInfo matches = userInfoList.login(request.getUserName(), request.getPassword());
        if(matches != null){
            return new LoginResult(true, "login sucess!", user);
        }
        else{
            return new LoginResult(false, "login failed.");
        }
    }
*/
    public LoginResult login(LoginRequest request){
        boolean success = serverModel.login(request.getUserName(), request.getPassword());
        if(success){
            return new LoginResult(true, "login success!");
        }
        else{
            return new LoginResult(false, "login failed.");
        }
    }

    public RegisterResult register(RegisterRequest request){
        User user = serverModel.register(request.getUserName(), request.getPassword());
        if (user != null){
            return new RegisterResult(true, "Successfully Registered", user); //TODO: Do we even need to give you a USER??
        }
        else{
            return new RegisterResult(false, "Failed to Register.", null);
        }
    }
/*
    public LoginResult login(LoginRequest request){

        try{
            UserInfo check = ServerModel.getInstance().getUsers().get(request.getUserName()).getInfo();
            if (check.checkUserInfo(request)){
                String userName = request.getUserName();
                return new LoginResult(true,"login success!", ServerModel.getInstance().getUsers().get(userName));
            } else{
                return new LoginResult(false, "login failed.");
            }
        }catch (NullPointerException e){
            return new LoginResult(false, "login failed.");
        }

    }*/

/*
    public RegisterResult register(RegisterRequest request){
        String userName = request.getUserName();
        String password = request.getPassword();
        String newUserID = UUID.randomUUID().toString();
        User newUser = new User(new UserInfo(userName, password, newUserID));
        if (validRegister(request)){
            ServerModel.getInstance().getUsers().put(userName, newUser);
            return new RegisterResult(true,"Successfully Registered.", newUser);
        } else{
            return new RegisterResult(false, "Failed to Register.", null);
        }
    }
    */
    //TODO: Don't need this validRegister anymore! Yay! It's checked in the UserInfoList!
    /*
    private boolean validRegister(RegisterRequest request) {
        String userName = request.getUserName();
        return  request.getPassword().length() > 0
                && userName.length() > 0
                && (!ServerModel.getInstance().getUsers().containsKey(userName));
    }*/

    public boolean createGame(Game newGame){
        return serverModel.createGame(newGame);
    }
/*
    public boolean createGame(Game newGame){
        try{
            ServerModel.getInstance().getGamesAsMap().put(newGame.getGameID(), newGame);
            System.out.println(ServerModel.getInstance().getGamesAsMap().get(newGame.getGameID()).getGameID());
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
*/

    public boolean joinGame(User user, String gameID)
    {
        return serverModel.joinGame(user, gameID);
    }

    /*
    public boolean joinGame(User user, String gameID){
        try{
            if (ServerModel.getInstance().getGamesAsMap().containsKey(gameID)){
                Game foundGame = ServerModel.getInstance().getGamesAsMap().get(gameID);
                if(foundGame.getPlayers().size() > foundGame.getPlayerMax())
                    return false;
                if (foundGame.canJoinGame()){
                    Player newPlayer = new Player(user.getUserName());
                    switch (foundGame.getPlayers().size()){

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
                    foundGame.addPlayer(newPlayer);
                    ServerModel.getInstance().getGamesAsMap().put(foundGame.getGameID(),foundGame);
                    user.addPlayer(newPlayer);
                    user.addGame(foundGame);
                    return true;
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
        return false;
    }
*/

    public boolean startGame(Game game){ //TODO: The poller should be constantly checking if the game has started...what do we want to do with startgame?
        return serverModel.startGame(game);
    }

    /*
    public boolean startGame(Game game){
        ServerModel serverModel = ServerModel.getInstance();
        if (serverModel.getGamesAsMap().containsKey(game.getGameID())){

            serverModel.getGamesAsMap().get(game.getGameID()).setHasStarted(true);
            //game.setHasStarted(true);
            //serverModel.getGamesAsMap().put(game.getGameID(), game);
            return true;
        } else {
            return false;
        }
    }
    */

    public boolean deleteGame(Game game){
        return serverModel.deleteGame(game);
    }

    /*
    public boolean deleteGame(Game game){
        try{
            if (ServerModel.getInstance().getGamesAsMap().containsKey(game.getGameID())){
                ServerModel.getInstance().getGamesAsMap().remove(game.getGameID());
                return  true;
            } else{
                return false;
            }

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    */

    /*
    public boolean leaveGame(Game game, Player player){
        return serverModel.deleteGame(game, player);
    }*/


    /*
    public boolean leaveGame(Game game, Player player){
        try{
            if (ServerModel.getInstance().getGamesAsMap().containsKey(game.getGameID())){
                Game thisGame = ServerModel.getInstance().getGamesAsMap().get(game.getGameID());
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
    */

    public GameList getGameList(){
        return ServerModel.getInstance().getGames();
    }


}
