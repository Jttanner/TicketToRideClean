/**
 * Created by jontt on 9/27/2017.
 */

public class UserInfo {

    private String userName;
    private String password;

    public UserInfo() {
    }

    public UserInfo(String userName, String password){
        this.userName = userName;
        this.password = password;
    }

    //Authorizing that the username and password are correct
    public boolean checkUserInfo(LoginRequest request){
        String userName = request.getUserName();
        if (this.userName.equals(userName) && this.password.equals(request.getPassword())){
            return true;
            //return new LoginResult(true, userName, "Login Successful.", );
        } else{
            return false;
            //return new LoginResult(false, userName,"Invalid Username or Password.");
        }
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
