package ServerModel;

import org.junit.Assert;

import java.sql.Connection;
import java.sql.DriverManager;

import modeling.User;
import modeling.UserInfo;

import static org.junit.Assert.*;

/**
 * Created by jontt on 12/7/2017.
 */
public class SQLiteUserDaoTest {
    //Connection connection;
    String dbURL = "jdbc:sqlite:ttrdb.sqlite";
    SQLiteUserDao userDao;

    String testName = "TestName";
    String testPassword = "TestPassword";

    @org.junit.Before
    public void setUp() throws Exception {
        Class.forName("org.sqlite.JDBC");
        userDao = new SQLiteUserDao(dbURL);
    }

    @org.junit.After
    public void tearDown() throws Exception {
        userDao = null;
    }

    @org.junit.Test
    public void registerUser() throws Exception {
        userDao.clear();
        Assert.assertTrue(userDao.registerUser("TestName", "TestPassword"));
    }

    @org.junit.Test
    public void verifyUser() throws Exception {
        userDao.clear();
        userDao.registerUser(testName, testPassword);
        UserInfo testUserInfo = new User(new UserInfo(testName, testPassword)).getInfo();
        UserInfo foundUserInfo = userDao.verifyUser(testName, testPassword).getInfo();
        Assert.assertTrue(testUserInfo.getUserName().equals(foundUserInfo.getUserName()));
        Assert.assertTrue(testUserInfo.getPassword().equals(foundUserInfo.getPassword()));
    }

    @org.junit.Test
    public void clear() throws Exception {
        userDao.clear();
    }

}