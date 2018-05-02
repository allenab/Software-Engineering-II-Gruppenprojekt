package at.uni.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer;

import at.uni.Application;
import at.uni.handlers.GameScreenManager;


public class SettingsScreen extends AbstractScreen {

    private OrthographicCamera camera;

    public SettingsScreen(Application application) {
        super(application);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Application.VIEWPORT_WIDTH, Application.VIEWPORT_HEIGHT);

    }

    @Override
    public void show() {

        application.getSpriteBatch().setProjectionMatrix(camera.combined);


        //creates the Sound where you can start the sound

        Skin skin = new Skin(Gdx.files.internal("data/uiskin.json"));
        TextButton btnSettings = new TextButton("Sound", skin);
        btnSettings.setSize(180, 50);
        btnSettings.setPosition(45, 100);

        btnSettings.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                System.out.println("SOUND");
                super.touchUp(event, x, y, pointer, button);
                final Sound mp3Sound = Gdx.audio.newSound(Gdx.files.internal("data/mp3.mp3"));

                final long id = mp3Sound.loop();
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        mp3Sound.stop(id);
                    }
                }, 5.0f);
                mp3Sound.dispose();
            }
        });
        //creates the Language button where you can chose the Language

        TextButton Language = new TextButton("Language", skin);
        Language.setSize(180,50);
        Language.setPosition(270, 100);

        Language.addListener(new ClickListener() {

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

            }
        });

        stage.addActor(Language);
        stage.addActor(btnSettings);
    }


    @Override
    public void load() {

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

    }
}
