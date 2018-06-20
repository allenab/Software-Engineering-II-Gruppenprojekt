package at.uni.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import at.uni.Application;
import at.uni.handlers.GameInputProcessor;
import at.uni.handlers.GameScreenManager;

import static at.uni.utils.Box2DHelper.PPM;


public class MainMenuScreen extends AbstractScreen {

    private Viewport viewport;
    private OrthographicCamera camera;
    private OrthographicCamera b2dCamera;
    private Label title;
    private Box2DDebugRenderer b2dr;
    private World world;
    protected Texture backgroundTexture;

    private long soundID;

    public MainMenuScreen(Application application) {
        super(application);

        Application.bgLoop = Gdx.audio.newSound(Gdx.files.internal("sounds/yummie_shortBGloop.mp3"));

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Application.VIEWPORT_WIDTH, Application.VIEWPORT_HEIGHT);

        viewport = new FitViewport(Application.VIEWPORT_WIDTH, Application.VIEWPORT_HEIGHT, camera);
        b2dCamera = new OrthographicCamera();
        b2dCamera.setToOrtho(false, Application.VIEWPORT_WIDTH / PPM, Application.VIEWPORT_HEIGHT / PPM);

        b2dr = new Box2DDebugRenderer();

        world = new World(new Vector2(0f, 0f), true);

        backgroundTexture = new Texture ("images/MainMenuScreen.png");
    }

    @Override
    public void show() {

        application.getSpriteBatch().setProjectionMatrix(camera.combined);

        //creates the play button with the text, its position and the size

        Skin skin = new Skin(Gdx.files.internal("data/uiskin.json"));

        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();

        TextButton btnPlay = new TextButton("Play", skin);
        btnPlay.getLabel().setFontScale(2);
        btnPlay.setSize(width / 6, height / 4);
        btnPlay.setPosition(10, height / 4);

        btnPlay.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                //bgLoop.stop(soundID);
                //application.getGameScreenManager().setScreen(GameScreenManager.STATE.PLAY);
                application.getGameScreenManager().setScreen(GameScreenManager.STATE.CONNECTION);
            }
        });

        stage.addActor(btnPlay);

        //creates the setting button with the text, its position and the size

        TextButton btnSettings = new TextButton("Settings", skin);
        btnSettings.getLabel().setFontScale(2);
        btnSettings.setSize(width / 6, height / 4);
        btnSettings.setPosition(btnPlay.getX()+btnPlay.getWidth()+10, height / 4);

        btnSettings.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                application.getGameScreenManager().setScreen(GameScreenManager.STATE.SETTINGS);
            }
        });

        stage.addActor(btnSettings);


        //creates the exit button with the text, its position and the size

        TextButton btnExit = new TextButton("Exit", skin);
        btnExit.getLabel().setFontScale(2);
        btnExit.setSize(width / 6, height / 4);
        btnExit.setPosition(btnSettings.getX()+btnSettings.getWidth()+10, height/4);

        btnExit.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                Gdx.app.exit();
            }
        });

        stage.addActor(btnExit);

        super.show();
    }

    @Override
    public void load() {
        //application.getSpriteBatch().setProjectionMatrix(camera.combined);

        Skin skin = new Skin(Gdx.files.internal("data/uiskin.json"));

        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();

        title = new Label("Game Menu!!", skin);
        title.setFontScale(2.f);
        title.setPosition(width / 2 - 100, height - 150);
        stage.addActor(title);

        this.soundID = Application.bgLoop.loop();
    }

    @Override
    public void unload() {

    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float deltatime) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(this.backgroundTexture, 0, 0);
        sb.end();
        }

    @Override
    public void dispose() {
        super.dispose();
        Application.bgLoop.dispose();
    }
}
