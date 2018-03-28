package at.uni;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import at.uni.handlers.GameScreenManager;
import at.uni.utils.Assets;

public class Application extends Game {

	public static final String TITLE = "Kitten Splash";
	public static final int VIEWPORT_WIDTH = 720;
	public static final int VIEWPORT_HEIGHT = 480;
	public static final int FPS = 60;

	public static final float STEP = 1f / (float) 60;

	private SpriteBatch spriteBatch;

	private GameScreenManager gameScreenManager;
	private AssetManager assetManager;

	public SpriteBatch getSpriteBatch(){
		return spriteBatch;
	}

	public AssetManager getAssetManager(){
		return assetManager;
	}

	@Override
	public void create() {
		spriteBatch = new SpriteBatch();

		assetManager = new AssetManager();
		Assets.loadAssets(assetManager);

		gameScreenManager = new GameScreenManager(this);
		gameScreenManager.setScreen(GameScreenManager.STATE.PLAY);
	}

	@Override
	public void render(){
		super.render();
	}

	@Override
	public void dispose(){
		super.dispose();
		spriteBatch.dispose();
		assetManager.dispose();
		gameScreenManager.dispose();
	}

}
