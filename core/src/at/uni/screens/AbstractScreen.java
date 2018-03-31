package at.uni.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import at.uni.Application;

public abstract class AbstractScreen implements Screen {

    protected Application application;

    public AbstractScreen(Application application){
        this.application = application;
    }

    public abstract void handleInput();
    public abstract void update(float deltatime);
    public abstract void render(SpriteBatch sb);

    @Override
    public void render(float deltatime){
        handleInput();
        update(deltatime);
        render(application.getSpriteBatch());
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

}
