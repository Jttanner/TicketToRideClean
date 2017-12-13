package result;

import modeling.GameList;
import modeling.User;
import sun.rmi.runtime.Log;

/**
 * Created by Hwang on 9/28/2017.
 */

public class LoginResult extends ResultObject {

    /*
    * Will return the login result for the login process
    * */

    private User user;
    private String gameID = "";
   // private GameList gameList = null;

    public String getGameID() {
        return gameID;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }
//
//    public GameList getGameList() {
//        return gameList;
//    }
//
//    public void setGameList(GameList gameList) {
//        this.gameList = gameList;
//    }

    public LoginResult(boolean success, String message, String gameId, GameList gameList1, User user){
        super(success,message);
        this.gameID = gameId;
        //this.gameList = gameList1;
        this.user = user;
    }

    public LoginResult(boolean success, String message, User user) {
        super(success, user.getInfo().getUserName(), message);
        this.user = user;
    }

    public LoginResult(boolean success, String errorMessage) {
        super(success, errorMessage);
    }

    public User getUser() {
        return user;
    }
}
