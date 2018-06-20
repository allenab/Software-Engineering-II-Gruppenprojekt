package at.uni.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
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
    protected Texture backgroundTexture;

    private long soundID;

    public ConnectionScreen(Application application) {
        super(application);

        Application.bgLoop = Gdx.audio.newSound(Gdx.files.internal("sounds/yummie_shortBGloop.mp3"));
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Application.VIEWPORT_WIDTH, Application.VIEWPORT_HEIGHT);

        backgroundTexture = new Texture ("images/MainMenuScreen.png");
    }

    @Override
    public void show() {

        application.getSpriteBatch().setProjectionMatrix(camera.combined);


        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();

        //creates the game start button with the text, its position and the size

        Skin skin = new Skin(Gdx.files.internal("data/uiskin.json"));

        TextButton btnGamestart = new TextButton("Game start", skin);
        btnGamestart.getLabel().setFontScale(1);
        btnGamestart.setSize(width / 6, height / 4);
        btnGamestart.setPosition(10, height / 4);

        btnGamestart.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                //bgLoop.stop(soundID);
                application.getGameScreenManager().setScreen(GameScreenManager.STATE.PLAY);
            }
        });

        stage.addActor(btnGamestart);



        TextButton btnConnection = new TextButton("Connection!", skin);
        btnConnection.getLabel().setFontScale(1);
        btnConnection.setSize(width / 6, height / 4);
        btnConnection.setPosition(btnGamestart.getX()+btnGamestart.getWidth()+10, height/4);

        btnConnection.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                System.out.println("Connection!!");
            }
        });

        stage.addActor(btnConnection);


        //creates the Create Game button with the text, its position and the size
        //creates the exit button with the text, its position and the size

        TextButton btnExit2 = new TextButton("Exit", skin);
        btnExit2.getLabel().setFontScale(1);
        btnExit2.setSize(width / 6, height / 4);
        btnExit2.setPosition(btnConnection.getX()+btnConnection.getWidth()+10, height/4);

        btnExit2.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                application.getGameScreenManager().setScreen(GameScreenManager.STATE.MAIN_MENU);
            }
        });

        stage.addActor(btnExit2);

        //creates the Connection button with the text, its position and the size

        super.show();
    }

    @Override
    public void load() {

        //application.getSpriteBatch().setProjectionMatrix(camera.combined);

        Skin skin = new Skin(Gdx.files.internal("data/uiskin.json"));
        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();

        title = new Label("Connection!!", skin);
        title.setFontScale(2.f);
        title.setPosition(width / 2 - 100, height - 100);

        stage.addActor(title);

        Connection = new Label("Connect0ion:", skin);
        Connection.setFontScale(2.f);
        Connection.setPosition(10, height /2 + 50);
        stage.addActor(Connection);

        EnterConection = new TextField("", skin);
        EnterConection.setPosition(Connection.getX()+Connection.getWidth()*2+ 10, height /2 + 50);
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
