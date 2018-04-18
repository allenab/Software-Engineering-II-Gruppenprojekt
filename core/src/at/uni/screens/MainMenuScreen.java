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
    }

    @Override
    public void handleInput() {
        if(InputData.isTouched(0, new Rectangle(100, Application.VIEWPORT_HEIGHT - 100, 50 , 20))){
            System.out.println("PLAY");
        }
        if(InputData.isPressed(1, new Rectangle(100,Application.VIEWPORT_HEIGHT - 100,50,20))){
            System.out.println("STOP");
        }
    }

    @Override
    public void update(float deltatime) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        font.draw(sb, "MENU", 100, 100);
        sb.end();
    }

    @Override
    public void dispose() {
        font.dispose();
    }
}
