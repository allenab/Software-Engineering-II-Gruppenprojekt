package at.uni.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import at.uni.Application;
import at.uni.handlers.GameInputProcessor;
import at.uni.handlers.GameScreenManager;

public class ConnectionScreen extends AbstractScreen {

    private OrthographicCamera camera;
    private TextField EnterConection;
    private Label Connection;
    private Label title;

    private long soundID;

    public ConnectionScreen(Application application) {
        super(application);

        Application.bgLoop = Gdx.audio.newSound(Gdx.files.internal("sounds/yummie_shortBGloop.mp3"));
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Application.VIEWPORT_WIDTH, Application.VIEWPORT_HEIGHT);

    }

    @Override
    public void show() {

        application.getSpriteBatch().setProjectionMatrix(camera.combined);

        //creates the game start button with the text, its position and the size

        Skin skin = new Skin(Gdx.files.internal("data/uiskin.json"));

        TextButton btnGamestart = new TextButton("Game start", skin);
        btnGamestart.setSize(Application.VIEWPORT_WIDTH / 6, Application.VIEWPORT_HEIGHT / 4);
        btnGamestart.setPosition(Application.VIEWPORT_WIDTH / 9, Application.VIEWPORT_HEIGHT / 4);

        btnGamestart.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                //bgLoop.stop(soundID);
                application.getGameScreenManager().setScreen(GameScreenManager.STATE.PLAY);
            }
        });

        stage.addActor(btnGamestart);

        //creates the Create Game button with the text, its position and the size
        //creates the exit button with the text, its position and the size

        TextButton btnExit2 = new TextButton("Exit", skin);
        btnExit2.setSize(Application.VIEWPORT_WIDTH / 6, Application.VIEWPORT_HEIGHT / 4);
        btnExit2.setPosition(Application.VIEWPORT_WIDTH -180, Application.VIEWPORT_HEIGHT/4);

        btnExit2.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                application.getGameScreenManager().setScreen(GameScreenManager.STATE.MAIN_MENU);
            }
        });

        stage.addActor(btnExit2);

        //creates the Connection button with the text, its position and the size

        TextButton btnConnection = new TextButton("Connection!", skin);
        btnConnection.setSize(180,33);
        btnConnection.setPosition(350, 330);

        btnConnection.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                System.out.println("Connection!!");
            }
        });

        stage.addActor(btnConnection);

        super.show();
    }

    @Override
    public void load() {

        //application.getSpriteBatch().setProjectionMatrix(camera.combined);

        Skin skin = new Skin(Gdx.files.internal("data/uiskin.json"));

        title = new Label("Connection!!", skin);
        title.setPosition(Application.VIEWPORT_WIDTH / 2 - 100, Application.VIEWPORT_HEIGHT - 30);
        stage.addActor(title);

        Connection = new Label("Connection:", skin);
        Connection.setPosition(45, 330);
        stage.addActor(Connection);

        EnterConection = new TextField("", skin);
        EnterConection.setPosition(145, 330);
        stage.addActor(EnterConection);

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
        super.dispose();
        Application.bgLoop.dispose();
    }
}
