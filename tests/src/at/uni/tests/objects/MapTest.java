package at.uni.tests.objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import at.uni.objects.Bombs;
import at.uni.objects.Map;
import at.uni.objects.Player;
import at.uni.tests.GdxTestRunner;

import static at.uni.utils.Box2DHelper.PPM;

@RunWith(GdxTestRunner.class)
public class MapTest {

    private Map map;

    @Before
    public void setUp() throws Exception {
        map = new Map();
    }

    @After
    public void tearDown() throws Exception {
        map.dispose();

    }

    @Test
    public void loadMap() {

    }

    @Test
    public void randomBricks() {

    }
}

