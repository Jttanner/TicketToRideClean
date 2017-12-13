import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Hwang on 10/12/2017.
 */

public class UserInfoList {

    private List<UserInfo> userInfoList;
    //Checks username to find it's userinfo
    private Map<String, UserInfo> usernameToUserInfo = new HashMap<>();

    private Map<User, UserInfo> userToUserInfo = new HashMap<>();


    public UserInfoList() {
        userInfoList = new ArrayList();
    }

    public void addUser(UserInfo userInfo)
    {
        userInfoList.add(userInfo);
    }

    public User register(String userName, String password)
    {
        if ((userName.length() > 0) && (password.length() > 0))
        if(!usernameToUserInfo.containsKey(userName)){
            UserInfo newUserInfo = new UserInfo();
            newUserInfo.setUserName(userName);
            newUserInfo.setPassword(password);
            userInfoList.add(newUserInfo);

            User user = new User(new UserInfo(userName, password));
            userToUserInfo.put(user, newUserInfo);

            //Places it into a map so we can check if they already have an account
            usernameToUserInfo.put(userName, newUserInfo);
            return user;
        }
        return null;
    }

    public UserInfo login(String userName, String password)
    {
        UserInfo matches = null;
        for (UserInfo userInfo : userInfoList) {
            if (userInfo.getUserName().equals(userName) && userInfo.getPassword().equals(password)) {
                matches = userInfo;
            }
        }
        return matches;
    }

    public Map<String, UserInfo> getUsernameToUserInfo() {
        return usernameToUserInfo;
    }

    public Map<User, UserInfo> getUserToUserInfo() {
        return userToUserInfo;
    }
}
