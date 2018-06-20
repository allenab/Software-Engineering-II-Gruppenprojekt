package at.uni.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import at.uni.Application;
import at.uni.handlers.GameScreenManager;

public class NewGameScreen extends AbstractScreen {
    private OrthographicCamera camera;

    private long soundID;

    public NewGameScreen(Application application) {
        super(application);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Application.VIEWPORT_WIDTH, Application.VIEWPORT_HEIGHT);

    }

    @Override
    public void load() {

        //creates the game start button with the text, its position and the size

        application.getSpriteBatch().setProjectionMatrix(camera.combined);
        this.soundID = Application.bgLoop.loop();

        Skin skin = new Skin(Gdx.files.internal("data/uiskin.json"));

        TextButton btnCreateGame = new TextButton("New Lobby", skin);
        btnCreateGame.setSize(Application.VIEWPORT_WIDTH / 6, Application.VIEWPORT_HEIGHT / 4);
        btnCreateGame.setPosition(45, 250);

        btnCreateGame.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                //bgLoop.stop(soundID);
                application.setServer(true);
                application.getGameScreenManager().setScreen(GameScreenManager.STATE.LOBBY);
            }
        });

        stage.addActor(btnCreateGame);

        TextButton btnJoinGame = new TextButton("Join Lobby", skin);
        btnJoinGame.setSize(Application.VIEWPORT_WIDTH / 6, Application.VIEWPORT_HEIGHT / 4);
        btnJoinGame.setPosition(445, 250);

        btnJoinGame.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                //bgLoop.stop(soundID);
                application.setServer(false);
                application.getGameScreenManager().setScreen(GameScreenManager.STATE.LOBBY);
            }
        });

        stage.addActor(btnJoinGame);

        //creates the exit button with the text, its position and the size

        TextButton btnExit = new TextButton("Exit", skin);
        btnExit.setSize(180,50);
        btnExit.setPosition(495, 100);

        btnExit.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                application.getGameScreenManager().setScreen(GameScreenManager.STATE.MAIN_MENU);
            }
        });

        stage.addActor(btnExit);

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
}
