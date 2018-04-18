package at.uni.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import at.uni.Application;
import at.uni.handlers.GameScreenManager;
import at.uni.utils.InputData;

public class MainMenuScreen extends AbstractScreen {

    private OrthographicCamera camera;

    public MainMenuScreen(Application application) {
        super(application);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Application.VIEWPORT_WIDTH, Application.VIEWPORT_HEIGHT);

    }

    @Override
    public void show() {

        application.getSpriteBatch().setProjectionMatrix(camera.combined);


        // creates the pack for the buttons, its skin, the font used on the buttons, and the stage(screen)

        //creates the play button with the text, its position and the size
        Skin skin = new Skin(Gdx.files.internal("data/uiskin.json"));
        TextButton btnPlay = new TextButton("Play", skin);
        btnPlay.setSize(80, 50);
        btnPlay.setPosition(100, 200);

        btnPlay.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("PLAY");
                super.touchUp(event, x, y, pointer, button);
            }
        });

        stage.addActor(btnPlay);

        //creates the exit button with the text, its position and the size

        TextButton btnExit = new TextButton("Exit", skin);
        btnExit.setSize(80,50);
        btnExit.setPosition(300, 100);

        btnExit.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("EXIT");
                super.touchUp(event, x, y, pointer, button);
            }
        });

        stage.addActor(btnExit);

        //creates the setting button with the text, its position and the size

        TextButton btnSettings = new TextButton("Settings", skin);
        btnSettings.setSize(80,50);
        btnSettings.setPosition(500, 100);

        btnSettings.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("SETTINGS");
                super.touchUp(event, x, y, pointer, button);
            }
        });

        stage.addActor(btnSettings);

        super.show();
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

    }
}
