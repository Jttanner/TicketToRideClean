package teamjapannumbahone.tickettoridetest;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import clientModel.LongestRouteCalc;
import modeling.Player;
import modeling.Route;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class LongestRouteCalcTest {
    private LongestRouteCalc calc;
    private Method initLongestPath;
    private Method setRouteList;
    @Before
    public void setUp(){
        calc = new LongestRouteCalc();
        Class<?> clazz = calc.getClass();
        try {
            initLongestPath = clazz.getDeclaredMethod("initLongestPath");
            initLongestPath.setAccessible(true);

            Class[] c = new Class[1];
            c[0] = List.class;
            setRouteList = clazz.getDeclaredMethod("setRouteList",c);
            setRouteList.setAccessible(true);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void TestGettingCorrectLength(){

        List<Player> players = new ArrayList<>();
        Player player = new Player("Name","Name","Yellow");

        player.addRoute(new Route("Portland", "Seattle", "Wild", 1, true));
        player.addRoute(new Route("Dallas", "El Paso", "red", 4));
        player.addRoute(new Route("El Paso", "Oklahoma City", "yellow", 5));
        player.addRoute(new Route("Oklahoma City", "Santa Fe", "blue", 3));

        players.add(player);
        try {
            setRouteList.invoke(calc,players.get(0).getRoutes());
            Assert.assertEquals(12,initLongestPath.invoke(calc));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestCorrectLengthSmall(){
        List<Player> players = new ArrayList<>();
        Player player2 = new Player("Name2","Name2","Red");

        player2.addRoute(new Route("Portland", "Seattle", "Wild", 1, true));
        player2.addRoute(new Route("Seattle", "Vancouver", "Wild", 1, true));

        players.add(player2);
        try {
            setRouteList.invoke(calc,players.get(0).getRoutes());
            Assert.assertEquals(initLongestPath.invoke(calc),2);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestPlayerCompare(){

        List<Player> players = new ArrayList<>();
        Player player = new Player("Name","Name","Yellow");
        Player player2 = new Player("Name2","Name2","Red");

        player.addRoute(new Route("Portland", "Seattle", "Wild", 1, true));
        player.addRoute(new Route("Dallas", "El Paso", "red", 4));
        player.addRoute(new Route("El Paso", "Oklahoma City", "yellow", 5));
        player.addRoute(new Route("Oklahoma City", "Santa Fe", "blue", 3));

        player2.addRoute(new Route("Portland", "Seattle", "Wild", 1, true));
        player2.addRoute(new Route("Seattle", "Vancouver", "Wild", 1, true));

        players.add(player);
        Assert.assertEquals(calc.findLongestRoute(players),player);

    }

    @Test
    public void TestLargeGraphCircle(){

        List<Player> players = new ArrayList<>();
        Player player = new Player("Name","Name","Yellow");

        player.addRoute(new Route("Calgary", "Winnipeg", "white", 6));
        player.addRoute(new Route("Sault St. Marie", "Toronto", "Wild", 2));
        player.addRoute(new Route("Sault St. Marie", "Winnipeg", "Wild", 6));
        player.addRoute(new Route("Duluth", "Toronto", "purple", 6));
        player.addRoute(new Route("Duluth", "Helena", "orange", 6));

        player.addRoute(new Route("Helena", "Salt Lake City", "purple", 3));
        player.addRoute(new Route("Portland", "Salt Lake City", "blue", 6));
        player.addRoute(new Route("Portland", "Seattle", "Wild", 1, true));
        player.addRoute(new Route("Seattle", "Vancouver", "Wild", 1, true));
        player.addRoute(new Route("Calgary", "Vancouver", "Wild", 3));

        players.add(player);
        try {
            setRouteList.invoke(calc,players.get(0).getRoutes());
            Assert.assertEquals(40,initLongestPath.invoke(calc));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void TestTwoHalfCircles(){

        List<Player> players = new ArrayList<>();
        Player player = new Player("Name","Name","Yellow");

        player.addRoute(new Route("Calgary", "Winnipeg", "white", 6));
        player.addRoute(new Route("Sault St. Marie", "Toronto", "Wild", 2));
        player.addRoute(new Route("Sault St. Marie", "Winnipeg", "Wild", 6));
        player.addRoute(new Route("Duluth", "Toronto", "purple", 6));
        player.addRoute(new Route("Duluth", "Helena", "orange", 6));
        player.addRoute(new Route("Helena", "Salt Lake City", "purple", 3));
        player.addRoute(new Route("Portland", "Salt Lake City", "blue", 6));
        player.addRoute(new Route("Portland", "Seattle", "Wild", 1, true));
        player.addRoute(new Route("Seattle", "Vancouver", "Wild", 1, true));
        player.addRoute(new Route("Calgary", "Vancouver", "Wild", 3));
        //cut the circle in half
        player.addRoute(new Route("Helena", "Winnipeg", "blue", 4));

        players.add(player);
        try {
            setRouteList.invoke(calc,players.get(0).getRoutes());
            Assert.assertEquals(44,initLongestPath.invoke(calc));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }


    @After
    public void breakDown(){
        /*initLongestPath.setAccessible(false);
        setRouteList.setAccessible(false);*/
    }
}