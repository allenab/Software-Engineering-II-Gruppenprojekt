package at.uni.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer;

import at.uni.Application;
import at.uni.handlers.GameScreenManager;


public class SettingsScreen extends AbstractScreen {

    private OrthographicCamera camera;
    private Label title;

    public SettingsScreen(Application application) {
        super(application);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Application.VIEWPORT_WIDTH, Application.VIEWPORT_HEIGHT);

    }

    @Override
    public void load() {
        application.getSpriteBatch().setProjectionMatrix(camera.combined);

        Skin skin = new Skin(Gdx.files.internal("data/uiskin.json"));

        title = new Label("Game Settings!!", skin);
        title.setPosition(Application.VIEWPORT_WIDTH / 2 - 100, Application.VIEWPORT_HEIGHT - 10);
        stage.addActor(title);

        //creates the Sound where you can start the sound
        TextButton btnSettings = new TextButton("Sound", skin);
        btnSettings.setSize(180, 50);
        btnSettings.setPosition(45, 100);

        btnSettings.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                System.out.println("SOUND");
                super.touchUp(event, x, y, pointer, button);
                Application.musicEnabled = !Application.musicEnabled;
                if(Application.musicEnabled)
                {
                    Application.bgLoop.loop();
                }
                else
                {
                    Application.bgLoop.stop();
                }

            }
        });
        //creates the Exit button where you can chose the Language

        TextButton Exit = new TextButton("Exit", skin);
        Exit.setSize(180,50);
        Exit.setPosition(270, 100);

        Exit.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                application.getGameScreenManager().setScreen(GameScreenManager.STATE.MAIN_MENU);
            }
        });

        stage.addActor(Exit);
        stage.addActor(btnSettings);
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
