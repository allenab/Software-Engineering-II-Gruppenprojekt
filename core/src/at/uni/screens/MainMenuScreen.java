package at.uni.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import at.uni.Application;


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
        TextButton btnname = new TextButton("Play", skin);
        btnname.setSize(80, 50);
        btnname.setPosition(100, 100);

       /* btnname.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                // process user input
                if (Gdx.input.isTouched()) {
                    Name touchPos = new Name();
                    touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
                    camera.unproject(touchPos);
                    bucket.x = touchPos.x - 64 / 2;
                }
                if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
                    bucket.x -= 200 * Gdx.graphics.getDeltaTime();
                if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
                    bucket.x += 200 * Gdx.graphics.getDeltaTime();

            }
        });

        stage.addActor(btnname);
*/

        //creates the play button with the text, its position and the size
        skin = new Skin(Gdx.files.internal("data/uiskin.json"));
        TextButton btnPlay = new TextButton("Play", skin);
        btnPlay.setSize(180, 50);
        btnPlay.setPosition(45, 100);

        btnPlay.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                super.touchUp(event, x, y, pointer, button);
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
                System.out.println("SETTINGS");
                super.touchUp(event, x, y, pointer, button);
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


        super.show();
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
