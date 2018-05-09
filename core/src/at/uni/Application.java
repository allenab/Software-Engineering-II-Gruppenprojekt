package at.uni;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import at.uni.handlers.GameInputProcessor;
import at.uni.handlers.GameScreenManager;
import at.uni.net.KittenClient;
import at.uni.net.KittenServer;
import at.uni.utils.Assets;
import at.uni.utils.InputData;

public class Application extends Game {

	public static final String TITLE = "Kitten Splash";
	public static final int VIEWPORT_WIDTH = 720;
	public static final int VIEWPORT_HEIGHT = 480;
	public static final int FPS = 60;

	public static final float STEP = 1f / (float) FPS;

	private SpriteBatch spriteBatch;

	private GameScreenManager gameScreenManager;
	private AssetManager assetManager;

	private KittenClient client;
	private KittenServer server;

	public SpriteBatch getSpriteBatch(){
		return spriteBatch;
	}

	public AssetManager getAssetManager(){
		return assetManager;
	}

	public GameScreenManager getGameScreenManager() { return gameScreenManager; }

	@Override
	public void create() {
		spriteBatch = new SpriteBatch();

		assetManager = new AssetManager();
		Assets.loadAssets(assetManager);

		gameScreenManager = new GameScreenManager(this);
		gameScreenManager.setScreen(GameScreenManager.STATE.GAMESERVER);
	}

    @Override
    public void render() {
        super.render();
        InputData.update();
    }

    @Override
	public void dispose(){
		super.dispose();
		spriteBatch.dispose();
		assetManager.dispose();
		gameScreenManager.dispose();
	}

	public KittenClient getClient() {
		return client;
	}

	public void setClient(KittenClient client) {
		this.client = client;
	}

	public KittenServer getServer() {
		return server;
	}

	public void setServer(KittenServer server) {
		this.server = server;
	}
}
