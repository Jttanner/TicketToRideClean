package result;

import modeling.User;

/**
 * Created by Hwang on 9/28/2017.
 */

public class LoginResult extends ResultObject {

    private User user;

    public LoginResult(boolean success, String errorMessage, User user) {
        super(success, user.getInfo().getUserName(), errorMessage);
        this.user = user;
    }

    public LoginResult(boolean success, String errorMessage) {
        super(success, errorMessage);
    }

    public User getUser() {
        return user;
    }
}
