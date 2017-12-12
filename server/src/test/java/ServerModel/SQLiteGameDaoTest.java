package ServerModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modeling.Game;

import static org.junit.Assert.*;

/**
 * Created by TannerDesktop on 12/12/2017.
 */
public class SQLiteGameDaoTest {

    SQLPersistenceManager persistenceManager = new SQLPersistenceManager();

    IGameDao gameDao;

    @Before
    public void setUp() throws Exception {
        gameDao = persistenceManager.getGameDao();
    }

    @After
    public void tearDown() throws Exception {
        gameDao = null;
    }

    @Test
    public void updateGameState() throws Exception {
        Game game = new Game();
        gameDao.updateGameState(game);
    }

    @Test
    public void getGameState() throws Exception {
    }

    @Test
    public void removeGame() throws Exception {
    }

    @Test
    public void clear() throws Exception {
        gameDao.clear();
    }

}