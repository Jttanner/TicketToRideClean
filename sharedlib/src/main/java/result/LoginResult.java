package result;

import modeling.User;
import request.RequestObject;

/**
 * Created by Hwang on 9/28/2017.
 */

public class LoginResult extends ResultObject {

    User user;

    public LoginResult(boolean success, String userName, String errorMessage, User user) {
        super(success, userName, errorMessage);
        this.user = user;
    }

    public LoginResult(boolean success, String errorMessage) {
        super(success, errorMessage);
    }
}
