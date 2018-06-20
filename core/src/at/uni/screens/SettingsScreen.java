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
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import at.uni.Application;
import at.uni.handlers.GameInputProcessor;
import at.uni.handlers.GameScreenManager;


public class SettingsScreen extends AbstractScreen {

    private OrthographicCamera camera;
    private Label title;
    protected Texture backgroundTexture;

    public SettingsScreen(Application application) {
        super(application);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Application.VIEWPORT_WIDTH, Application.VIEWPORT_HEIGHT);


        backgroundTexture = new Texture ("images/MainMenuScreen.png");

    }

    @Override
    public void show() {
        application.getSpriteBatch().setProjectionMatrix(camera.combined);

        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();

        //creates the Sound where you can start the sound

        Skin skin = new Skin(Gdx.files.internal("data/uiskin.json"));

        TextButton btnSound = new TextButton("Sound", skin);
        btnSound.getLabel().setFontScale(2);
        btnSound.setSize(width / 6, height / 4);
        btnSound.setPosition(10, height / 4);;

        btnSound.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                System.out.println("SOUND");
                super.touchUp(event, x, y, pointer, button);
                Application.musicEnabled = !Application.musicEnabled;
                if(Application.musicEnabled)
                {
                    Application.bgLoop.resume();
                }
                else
                {
                    Application.bgLoop.pause();
                }

            }
        });
        //creates the Language button where you can chose the Language

        TextButton btnExit1 = new TextButton("Exit", skin);
        btnExit1.getLabel().setFontScale(2);
        btnExit1.setSize(width / 6, height / 4);
        btnExit1.setPosition(btnSound.getX()+btnSound.getWidth()+10, height/4);

        btnExit1.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                application.getGameScreenManager().setScreen(GameScreenManager.STATE.MAIN_MENU);
            }
        });

        stage.addActor(btnExit1);
        stage.addActor(btnSound);

        super.show();
    }


    @Override
    public void load() {

        Skin skin = new Skin(Gdx.files.internal("data/uiskin.json"));

        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();

        title = new Label("Game Settings!!", skin);
        title.setFontScale(2.f);
        title.setPosition(width / 2 - 100, height - 150);
        stage.addActor(title);

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
    }
}
