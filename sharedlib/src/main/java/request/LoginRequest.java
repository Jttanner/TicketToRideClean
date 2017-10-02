package request;

/**
 * Created by Hwang on 9/28/2017.
 */

public class LoginRequest extends  RequestObject {

    private String userName;
    private String password;


    public LoginRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
