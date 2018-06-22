package test.java.at.uni.objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import at.uni.objects.Bombs;
import at.uni.objects.Map;

public class BombsTest {

    Bombs bombs;
    Map map;

    @Before
    public void setUp() throws Exception {
        map = new Map();
        bombs = new Bombs(map);
    }

    @After
    public void tearDown() throws Exception {
        map = null;
        bombs = null;
    }

    @Test
    public void adding() {
        //Texture texture = new Texture("/bomberman.png");
        Assert.assertEquals(0, bombs.getBombs().size());

        bombs.addBomb(50, 100);
        bombs.addBomb(220, 100);
        bombs.addBomb(500, 0);

        Assert.assertEquals(3, bombs.getBombs().size());

        bombs.addBomb(300, 30);
        bombs.addBomb(220, 100);

        Assert.assertEquals(5, bombs.getBombs().size());
    }

    @Test
    public void expiring() throws InterruptedException {
        bombs.addBomb(0, 100);
        bombs.addBomb(220, 100);

        Assert.assertEquals(2, bombs.getBombs().size());

        TimeUnit.SECONDS.sleep(4);

        Assert.assertEquals(0, bombs.getBombs().size());

        bombs.addBomb(300, 30);
        bombs.addBomb(220, 100);

        TimeUnit.SECONDS.sleep(2);

        Assert.assertEquals(2, bombs.getBombs().size());

        bombs.addBomb(220, 100);

        Assert.assertEquals(3, bombs.getBombs().size());

        TimeUnit.SECONDS.sleep(2);

        Assert.assertEquals(1, bombs.getBombs().size());
    }
}