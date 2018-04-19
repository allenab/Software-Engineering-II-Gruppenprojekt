package at.uni.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import at.uni.Application;
import at.uni.utils.InputData;

public class MainMenuScreen extends AbstractScreen {

    private BitmapFont font;
    private OrthographicCamera camera;

    public MainMenuScreen(Application application) {
        super(application);

        font = new BitmapFont();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Application.VIEWPORT_WIDTH, Application.VIEWPORT_HEIGHT);

    }

    @Override
    public void show() {
        application.getSpriteBatch().setProjectionMatrix(camera.combined);


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
        sb.begin();
        font.draw(sb, "GAME MENU", 100, 100);
        sb.end();
    }

    @Override
    public void dispose() {
        font.dispose();
    }
}
