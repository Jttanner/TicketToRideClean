package ServerModel;

import org.junit.Assert;

import java.sql.Connection;
import java.sql.DriverManager;

import static org.junit.Assert.*;

/**
 * Created by jontt on 12/7/2017.
 */
public class SQLiteUserDaoTest {
    Connection connection;
    String dbURL = "jdbc:sqlite:c/Users/jontt/AndroidStudioProjects/TicketToRidePhase3/TicketToRidePhase3Testing/test.db";
    SQLiteUserDao userDao;

    @org.junit.Before
    public void setUp() throws Exception {
        connection = DriverManager.getConnection(dbURL);
        userDao = new SQLiteUserDao(connection.toString());
    }

    @org.junit.After
    public void tearDown() throws Exception {
        connection.close();
        userDao = null;
    }

    @org.junit.Test
    public void registerUser() throws Exception {
        Assert.assertTrue(userDao.registerUser("TestName", "TestPassword"));
    }

    @org.junit.Test
    public void verifyUser() throws Exception {

    }

    @org.junit.Test
    public void clear() throws Exception {

    }

}