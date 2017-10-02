package modeling;

import request.*;
import result.*;

/**
 * Created by jontt on 9/27/2017.
 */

public class UserInfo {

    public UserInfo(String userName, String password, String userID){
        this.userName = userName;
        this.password = password;
        this.userID = userID;
    }

    String userName;
    String password;
    String userID;

    public LoginResult checkUserInfo(LoginRequest request){
        String userName = request.getUserName();
        if (this.userName.equals(userName) && this.password.equals(request.getPassword())){
            return new LoginResult(true, userName, "Login Successful.");
        } else{
            return new LoginResult(false, userName,"Invalid Username or Password.");
        }
    }

}
