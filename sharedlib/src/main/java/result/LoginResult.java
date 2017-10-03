package result;

import request.RequestObject;

/**
 * Created by Hwang on 9/28/2017.
 */

public class LoginResult extends ResultObject {


    public LoginResult(boolean success, String userName, String errorMessage) {
        super(success, userName, errorMessage);
    }

    public LoginResult(boolean success, String errorMessage) {
        super(success, errorMessage);
    }
}
