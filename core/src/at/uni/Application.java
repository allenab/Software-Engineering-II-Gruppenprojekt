package at.uni;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import at.uni.handlers.GameInputProcessor;
import at.uni.handlers.GameScreenManager;
import at.uni.utils.Assets;
import at.uni.utils.InputData;

public class Application extends Game {

	public static final String TITLE = "Kitten Splash";
	public static int VIEWPORT_WIDTH = 720;
	public static int VIEWPORT_HEIGHT = 480;
	public static final int FPS = 60;
	public static boolean musicEnabled = true;

	public static Sound bgLoop = null;

	public static final float STEP = 1f / (float) FPS;

	private SpriteBatch spriteBatch;

	private GameScreenManager gameScreenManager;
	private AssetManager assetManager;

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

		//VIEWPORT_WIDTH = Gdx.graphics.getWidth();
		//VIEWPORT_HEIGHT = Gdx.graphics.getHeight();

		gameScreenManager = new GameScreenManager(this);
		gameScreenManager.setScreen(GameScreenManager.STATE.MAIN_MENU);
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

}
