package result;

import modeling.User;

/**
 * Created by Hwang on 9/28/2017.
 */

public class LoginResult extends ResultObject {

    /*
    * Will return the login result for the login process
    * */

    private User user;

    public LoginResult(boolean success, String userName, String errorMessage, User user) {
        super(success, userName, errorMessage);
        this.user = user;
    }

    public LoginResult(boolean success, String errorMessage) {
        super(success, errorMessage);
    }

    public User getUser() {
        return user;
    }
}
