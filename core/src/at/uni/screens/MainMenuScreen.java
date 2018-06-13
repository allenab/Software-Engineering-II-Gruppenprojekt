package at.uni.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import at.uni.Application;
import at.uni.handlers.GameScreenManager;

import static at.uni.utils.Box2DHelper.PPM;


public class MainMenuScreen extends AbstractScreen {

    private OrthographicCamera camera;
    private TextField EnterName;
    private Label name;
    private Label title;

    private long soundID;

    public MainMenuScreen(Application application) {
        super(application);

        Application.bgLoop = Gdx.audio.newSound(Gdx.files.internal("sounds/yummie_shortBGloop.mp3"));
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Application.VIEWPORT_WIDTH, Application.VIEWPORT_HEIGHT);
    }

    @Override
    public void show() {

        application.getSpriteBatch().setProjectionMatrix(camera.combined);

        //creates the play button with the text, its position and the size

        Skin skin = new Skin(Gdx.files.internal("data/uiskin.json"));

        TextButton btnPlay = new TextButton("Play", skin);
        btnPlay.setSize(Application.VIEWPORT_WIDTH / 6, Application.VIEWPORT_HEIGHT / 4);
        btnPlay.setPosition(45, 100);

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
        btnSettings.setSize(180,50);
        btnSettings.setPosition(270, 100);

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
        btnExit.setSize(180,50);
        btnExit.setPosition(495, 100);

        btnExit.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                Gdx.app.exit();
            }
        });

        stage.addActor(btnExit);

        //creates the exit button with the text, its position and the size

        TextButton btnSaveName = new TextButton("Save Name", skin);
        btnSaveName.setSize(180,33);
        btnSaveName.setPosition(350, 330);

        btnSaveName.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                System.out.println("Save Name!!");
            }
        });

        stage.addActor(btnSaveName);


        super.show();
    }

    @Override
    public void load() {

        //application.getSpriteBatch().setProjectionMatrix(camera.combined);

        Skin skin = new Skin(Gdx.files.internal("data/uiskin.json"));

        title = new Label("Game Menu!!", skin);
        title.setPosition(Application.VIEWPORT_WIDTH / 2 - 100, Application.VIEWPORT_HEIGHT - 10);
        stage.addActor(title);

        name = new Label("Name:", skin);
        name.setPosition(45, 330);
        stage.addActor(name);

        EnterName = new TextField("", skin);
        EnterName.setPosition(145, 330);
        stage.addActor(EnterName);

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

    }

    @Override
    public void dispose() {
        Application.bgLoop.dispose();
    }
}
