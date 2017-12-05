package teamjapannumbahone.tickettoridetest;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import clientModel.RouteCalc;
import modeling.City;
import modeling.DestinationCard;
import modeling.Player;
import modeling.Route;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class RouteCalcTest {
    private RouteCalc calc;
    private Method initLongestPath;
    private Method setRouteList;
    @Before
    public void setUp(){
        calc = new RouteCalc();
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

    @Test
    public void TestCircleWithBranches(){

        List<Player> players = new ArrayList<>();
        Player player = new Player("Name","Name","Yellow");
        //circle
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

        //Branches
        player.addRoute(new Route("Pittsburgh", "Toronto", "Wild", 2));
        player.addRoute(new Route("Duluth", "Omaha", "Wild", 2, true));
        player.addRoute(new Route("Denver", "Helena", "green", 4));
        player.addRoute(new Route("Salt Lake City", "San Francisco", "white", 5, true));

        players.add(player);
        try {
            setRouteList.invoke(calc,players.get(0).getRoutes());
            Assert.assertEquals(49,initLongestPath.invoke(calc));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void TestCircleWithHook(){

        List<Player> players = new ArrayList<>();
        Player player = new Player("Name","Name","Yellow");
        //circle
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

        //Branches
        player.addRoute(new Route("Pittsburgh", "Toronto", "Wild", 2));
        player.addRoute(new Route("Duluth", "Omaha", "Wild", 2, true));
        player.addRoute(new Route("Denver", "Helena", "green", 4));
        //hook
        player.addRoute(new Route("Salt Lake City", "San Francisco", "white", 5, true));
        player.addRoute(new Route("Los Angeles", "San Francisco", "yellow", 3, true));
        player.addRoute(new Route("El Paso", "Los Angeles", "black", 6));
        player.addRoute(new Route("El Paso", "Houston", "green", 6));
        player.addRoute(new Route("Houston", "New Orleans", "Wild", 2));
        player.addRoute(new Route("Miami", "New Orleans", "red", 6));
        player.addRoute(new Route("Charleston", "Miami", "purple", 4));

        players.add(player);
        try {
            setRouteList.invoke(calc,players.get(0).getRoutes());
            Assert.assertEquals(76,initLongestPath.invoke(calc));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void TestDestCardComplete(){
        Player player = new Player("Name","Name","Yellow");
        player.addDestinationCard(new DestinationCard(new City("Calgary"),new City("Vancouver"),15));

        player.addRoute(new Route("Calgary", "Winnipeg", "white", 6));
        player.addRoute(new Route("Sault St. Marie", "Toronto", "Wild", 2));
        player.addRoute(new Route("Sault St. Marie", "Winnipeg", "Wild", 6));
        player.addRoute(new Route("Duluth", "Toronto", "purple", 6));
        player.addRoute(new Route("Duluth", "Helena", "orange", 6));

        player.addRoute(new Route("Helena", "Salt Lake City", "purple", 3));
        player.addRoute(new Route("Portland", "Salt Lake City", "blue", 6));
        player.addRoute(new Route("Portland", "Seattle", "Wild", 1, true));
        player.addRoute(new Route("Seattle", "Vancouver", "Wild", 1, true));

        Assert.assertTrue(calc.isDestinationCardComplete(player.getDestinationCards().get(0),player.getRoutes()));
    }

    @Test
    public void TestDestCardCompleteWithHooks(){
        Player player = new Player("Name","Name","Yellow");
        player.addDestinationCard(new DestinationCard(new City("Calgary"),new City("Vancouver"),15));
        //circle
        player.addRoute(new Route("Calgary", "Winnipeg", "white", 6));
        player.addRoute(new Route("Sault St. Marie", "Toronto", "Wild", 2));
        player.addRoute(new Route("Sault St. Marie", "Winnipeg", "Wild", 6));
        player.addRoute(new Route("Duluth", "Toronto", "purple", 6));
        player.addRoute(new Route("Duluth", "Helena", "orange", 6));
        player.addRoute(new Route("Helena", "Salt Lake City", "purple", 3));
        player.addRoute(new Route("Portland", "Salt Lake City", "blue", 6));
        player.addRoute(new Route("Portland", "Seattle", "Wild", 1, true));
        player.addRoute(new Route("Seattle", "Vancouver", "Wild", 1, true));

        //Branches
        player.addRoute(new Route("Pittsburgh", "Toronto", "Wild", 2));
        player.addRoute(new Route("Duluth", "Omaha", "Wild", 2, true));
        player.addRoute(new Route("Denver", "Helena", "green", 4));
        //hook
        player.addRoute(new Route("Salt Lake City", "San Francisco", "white", 5, true));
        player.addRoute(new Route("Los Angeles", "San Francisco", "yellow", 3, true));
        player.addRoute(new Route("El Paso", "Los Angeles", "black", 6));
        player.addRoute(new Route("El Paso", "Houston", "green", 6));
        player.addRoute(new Route("Houston", "New Orleans", "Wild", 2));
        player.addRoute(new Route("Miami", "New Orleans", "red", 6));
        player.addRoute(new Route("Charleston", "Miami", "purple", 4));


    }

    @Test
    public void TestSmallDestCard(){
        Player player = new Player("Name","Name","Yellow");
        player.addDestinationCard(new DestinationCard(new City("Calgary"),new City("Sault St. Marie"),15));

        player.addRoute(new Route("Calgary", "Winnipeg", "white", 6));
        player.addRoute(new Route("Sault St. Marie", "Winnipeg", "Wild", 6));
        Assert.assertTrue(calc.isDestinationCardComplete(player.getDestinationCards().get(0),player.getRoutes()));
    }

    @Test
    public void TestHugeGraph(){
        Player player = new Player("Name","Name","Yellow");
        player.addDestinationCard(new DestinationCard(new City("Calgary"),new City("Miami"),15));
        //circle
        player.addRoute(new Route("Calgary", "Winnipeg", "white", 6));
        player.addRoute(new Route("Sault St. Marie", "Toronto", "Wild", 2));
        player.addRoute(new Route("Sault St. Marie", "Winnipeg", "Wild", 6));
        player.addRoute(new Route("Duluth", "Toronto", "purple", 6));
        player.addRoute(new Route("Duluth", "Helena", "orange", 6));
        player.addRoute(new Route("Helena", "Salt Lake City", "purple", 3));
        player.addRoute(new Route("Portland", "Salt Lake City", "blue", 6));
        player.addRoute(new Route("Portland", "Seattle", "Wild", 1, true));
        player.addRoute(new Route("Seattle", "Vancouver", "Wild", 1, true));

        //Branches
        player.addRoute(new Route("Pittsburgh", "Toronto", "Wild", 2));
        player.addRoute(new Route("Duluth", "Omaha", "Wild", 2, true));
        player.addRoute(new Route("Denver", "Helena", "green", 4));
        //hook
        player.addRoute(new Route("Salt Lake City", "San Francisco", "white", 5, true));
        player.addRoute(new Route("Los Angeles", "San Francisco", "yellow", 3, true));
        player.addRoute(new Route("El Paso", "Los Angeles", "black", 6));
        player.addRoute(new Route("El Paso", "Houston", "green", 6));
        player.addRoute(new Route("Houston", "New Orleans", "Wild", 2));
        player.addRoute(new Route("Miami", "New Orleans", "red", 6));
        player.addRoute(new Route("Charleston", "Miami", "purple", 4));

        Assert.assertTrue(calc.isDestinationCardComplete(player.getDestinationCards().get(0),player.getRoutes()));
    }

    @Test
    public void TestHugeGraph2(){
        Player player = new Player("Name","Name","Yellow");
        player.addDestinationCard(new DestinationCard(new City("Pittsburgh"),new City("San Francisco"),15));
        //circle
        player.addRoute(new Route("Calgary", "Winnipeg", "white", 6));
        player.addRoute(new Route("Sault St. Marie", "Toronto", "Wild", 2));
        player.addRoute(new Route("Sault St. Marie", "Winnipeg", "Wild", 6));
        player.addRoute(new Route("Duluth", "Toronto", "purple", 6));
        player.addRoute(new Route("Duluth", "Helena", "orange", 6));
        player.addRoute(new Route("Helena", "Salt Lake City", "purple", 3));
        player.addRoute(new Route("Portland", "Salt Lake City", "blue", 6));
        player.addRoute(new Route("Portland", "Seattle", "Wild", 1, true));
        player.addRoute(new Route("Seattle", "Vancouver", "Wild", 1, true));

        //Branches
        player.addRoute(new Route("Pittsburgh", "Toronto", "Wild", 2));
        player.addRoute(new Route("Duluth", "Omaha", "Wild", 2, true));
        player.addRoute(new Route("Denver", "Helena", "green", 4));
        //hook
        player.addRoute(new Route("Salt Lake City", "San Francisco", "white", 5, true));
        player.addRoute(new Route("Los Angeles", "San Francisco", "yellow", 3, true));
        player.addRoute(new Route("El Paso", "Los Angeles", "black", 6));
        player.addRoute(new Route("El Paso", "Houston", "green", 6));
        player.addRoute(new Route("Houston", "New Orleans", "Wild", 2));
        player.addRoute(new Route("Miami", "New Orleans", "red", 6));
        player.addRoute(new Route("Charleston", "Miami", "purple", 4));

        Assert.assertTrue(calc.isDestinationCardComplete(player.getDestinationCards().get(0),player.getRoutes()));
    }

    @Test
    public void TestHugeGraphFalse(){
        Player player = new Player("Name","Name","Yellow");
        //these cities do not exist on the game map
        player.addDestinationCard(new DestinationCard(new City("Tucson"),new City("Provo"),15));
        //circle
        player.addRoute(new Route("Calgary", "Winnipeg", "white", 6));
        player.addRoute(new Route("Sault St. Marie", "Toronto", "Wild", 2));
        player.addRoute(new Route("Sault St. Marie", "Winnipeg", "Wild", 6));
        player.addRoute(new Route("Duluth", "Toronto", "purple", 6));
        player.addRoute(new Route("Duluth", "Helena", "orange", 6));
        player.addRoute(new Route("Helena", "Salt Lake City", "purple", 3));
        player.addRoute(new Route("Portland", "Salt Lake City", "blue", 6));
        player.addRoute(new Route("Portland", "Seattle", "Wild", 1, true));
        player.addRoute(new Route("Seattle", "Vancouver", "Wild", 1, true));

        //Branches
        player.addRoute(new Route("Pittsburgh", "Toronto", "Wild", 2));
        player.addRoute(new Route("Duluth", "Omaha", "Wild", 2, true));
        player.addRoute(new Route("Denver", "Helena", "green", 4));
        //hook
        player.addRoute(new Route("Salt Lake City", "San Francisco", "white", 5, true));
        player.addRoute(new Route("Los Angeles", "San Francisco", "yellow", 3, true));
        player.addRoute(new Route("El Paso", "Los Angeles", "black", 6));
        player.addRoute(new Route("El Paso", "Houston", "green", 6));
        player.addRoute(new Route("Houston", "New Orleans", "Wild", 2));
        player.addRoute(new Route("Miami", "New Orleans", "red", 6));
        player.addRoute(new Route("Charleston", "Miami", "purple", 4));

        Assert.assertFalse(calc.isDestinationCardComplete(player.getDestinationCards().get(0),player.getRoutes()));
    }

    @Test
    public void TestGraphFalse(){
        Player player = new Player("Name","Name","Yellow");
        player.addDestinationCard(new DestinationCard(new City("Calgary"),new City("Seattle"),15));

        player.addRoute(new Route("Calgary", "Winnipeg", "white", 6));
        player.addRoute(new Route("Sault St. Marie", "Toronto", "Wild", 2));
        player.addRoute(new Route("Sault St. Marie", "Winnipeg", "Wild", 6));
        player.addRoute(new Route("Duluth", "Toronto", "purple", 6));
        player.addRoute(new Route("Duluth", "Helena", "orange", 6));
        player.addRoute(new Route("Helena", "Salt Lake City", "purple", 3));
        //missing salt lake to portland
        player.addRoute(new Route("Portland", "Seattle", "Wild", 1, true));


        Assert.assertFalse(calc.isDestinationCardComplete(player.getDestinationCards().get(0),player.getRoutes()));
    }

    @Test
    public void TestHugeGraphIncompleteCircle() {
        Player player = new Player("Name", "Name", "Yellow");
        player.addDestinationCard(new DestinationCard(new City("Calgary"), new City("Vancouver"), 15));
        //circle
        player.addRoute(new Route("Calgary", "Winnipeg", "white", 6));
        //no link from winnipeg to Sault St. Marie
        player.addRoute(new Route("Sault St. Marie", "Winnipeg", "Wild", 6));
        player.addRoute(new Route("Duluth", "Toronto", "purple", 6));
        player.addRoute(new Route("Duluth", "Helena", "orange", 6));
        player.addRoute(new Route("Helena", "Salt Lake City", "purple", 3));
        player.addRoute(new Route("Portland", "Salt Lake City", "blue", 6));
        player.addRoute(new Route("Portland", "Seattle", "Wild", 1, true));
        player.addRoute(new Route("Seattle", "Vancouver", "Wild", 1, true));
        player.addRoute(new Route("Calgary", "Vancouver", "Wild", 3));

        Assert.assertTrue(calc.isDestinationCardComplete(player.getDestinationCards().get(0),player.getRoutes()));
    }
    @After
    public void breakDown(){
        /*initLongestPath.setAccessible(false);
        setRouteList.setAccessible(false);*/
    }
}