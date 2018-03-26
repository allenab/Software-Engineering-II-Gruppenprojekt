package at.uni.screens;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import at.uni.Application;

public class MainMenuScreen extends AbstractScreen {

    private BitmapFont font;

    public MainMenuScreen(Application application) {
        super(application);

        font = new BitmapFont();

    }

    @Override
    public void show() {

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
        font.draw(sb, "MENU", 100, 100);
        sb.end();
    }

    @Override
    public void dispose() {
        font.dispose();
    }
}
