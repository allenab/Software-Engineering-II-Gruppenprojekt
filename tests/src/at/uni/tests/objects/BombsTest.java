package at.uni.tests.objects;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.concurrent.TimeUnit;

import at.uni.objects.Map;
import at.uni.tests.GdxTestRunner;
import at.uni.objects.Bombs;

@RunWith(GdxTestRunner.class)
public class BombsTest {

	private Bombs bombs;
	private Map map;

	@Before
	public void setUp() throws Exception {
		map = new Map();
		bombs = new Bombs(map);
	}

	@After
	public void tearDown() throws Exception {
		map.dispose();
		bombs.dispose();
	}

	@Test
	public void adding() {
		Texture texture = new Texture("images/bomberman.png");
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

		TimeUnit.SECONDS.sleep(5);

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
