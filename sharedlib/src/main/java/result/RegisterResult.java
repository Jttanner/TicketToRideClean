package result;

import modeling.User;

/**
 * Created by Hwang on 9/28/2017.
 */

public class RegisterResult extends ResultObject {

    /*
    * Will return the register result for the login process
    * */

    private User user;

    public RegisterResult(boolean success, String userName, String errorMessage, User user) {
        super(success, userName, errorMessage);
        this.user = user;
    }

    public RegisterResult(boolean success, String errorMessage, User user) {
        super(success, errorMessage);
        this.user = user;
    }

    public RegisterResult(boolean success,String errorMessage) {
        super(success,errorMessage);
    }

    public User getUser() {
        return user;
    }
}
