package at.uni.tests.objects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

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
		Assert.assertEquals(0, bombs.getBombs().size());

		bombs.addBomb(51, 100);
		bombs.addBomb(221, 100);
		bombs.addBomb(501, 0);

		Assert.assertEquals(3, bombs.getBombs().size());

		bombs.addBomb(301, 30);
		bombs.addBomb(221, 100);

		Assert.assertEquals(5, bombs.getBombs().size());
	}

	@Test
	public void expiring() throws InterruptedException {
		bombs.addBomb(100, 101);
		bombs.addBomb(220, 101);

		Assert.assertEquals(2, bombs.getBombs().size());

		TimeUnit.SECONDS.sleep(5);
		bombs.render(null);

		Assert.assertEquals(0, bombs.getBombs().size());

		bombs.addBomb(300, 30);
		bombs.addBomb(220, 100);

		TimeUnit.SECONDS.sleep(2);
		bombs.render(null);

		Assert.assertEquals(2, bombs.getBombs().size());

		bombs.addBomb(220, 100);

		Assert.assertEquals(3, bombs.getBombs().size());

		TimeUnit.SECONDS.sleep(2);
		bombs.render(null);

		Assert.assertEquals(1, bombs.getBombs().size());
	}
}
