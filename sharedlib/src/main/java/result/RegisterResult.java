package result;

import request.RequestObject;

/**
 * Created by Hwang on 9/28/2017.
 */

public class RegisterResult extends ResultObject {


    public RegisterResult(boolean success, String userName, String errorMessage) {
        super(success, userName, errorMessage);
    }

    public RegisterResult(boolean success, String errorMessage) {
        super(success, errorMessage);
    }
}
