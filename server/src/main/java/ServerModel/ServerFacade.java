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

    public LoginResult login(LoginRequest request){
        User user = serverModel.login(request.getUserName(), request.getPassword());
        if(user != null && user.getInfo() != null){
            return new LoginResult(true, "login success!",user);
        }
        else{
            return new LoginResult(false, "login failed.");
        }
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


    public boolean startGame(Game game){ //TODO: The poller should be constantly checking if the game has started...what do we want to do with startgame?
        return serverModel.startGame(game);
    }


    public boolean deleteGame(Game game){
        return serverModel.deleteGame(game);
    }


    public GameList getGameList(){
        return ServerModel.getInstance().getGames();
    }


}
