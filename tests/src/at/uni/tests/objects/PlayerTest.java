package at.uni.tests.objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import at.uni.objects.Map;
import at.uni.objects.Player;
import at.uni.tests.GdxTestRunner;
import at.uni.objects.Bombs;

import static at.uni.utils.Box2DHelper.PPM;

@RunWith(GdxTestRunner.class)
public class PlayerTest {

    private Player player;
    private Bombs bombs;
    private Map map;

    @Before
    public void setUp() throws Exception {
        map = new Map();
        bombs = new Bombs(map);
        player = new Player("images/bomberman.png", 100 / PPM, 100 / PPM, bombs);
    }

    @After
    public void tearDown() throws Exception {
        map.dispose();
        bombs.dispose();
        player.dispose();
    }

    @Test
    public void healthA() {
        Assert.assertEquals(100, player.getHealth());

        player.damageTaken();

        Assert.assertEquals(60, player.getHealth());

        player.damageTaken();

        Assert.assertEquals(20, player.getHealth());

        player.damageTaken();

        Assert.assertEquals(-20, player.getHealth());
    }

    @Test
    public void shield() {
        Assert.assertEquals(100, player.getHealth());

        player.damageTaken();

        Assert.assertEquals(60, player.getHealth());

        player.activateShield();
        player.damageTaken();

        Assert.assertEquals(60, player.getHealth());

        player.damageTaken();

        Assert.assertEquals(20, player.getHealth());

        player.activateShield();
        player.activateShield();
        player.damageTaken();
        player.activateShield();
        player.damageTaken();
        
        Assert.assertEquals(20, player.getHealth());
    }
}

