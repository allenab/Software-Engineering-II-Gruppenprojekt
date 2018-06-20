package at.uni.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;

import at.uni.Application;
import at.uni.handlers.GameInputProcessor;
import at.uni.utils.InputData;

public abstract class AbstractScreen implements Screen {

    protected Application application;
    protected Stage stage;
    protected OrthographicCamera camera;
    private FillViewport viewport;

    private GameInputProcessor gameInputProcessor;
    private InputMultiplexer multiplexer;

    public AbstractScreen(Application application){
        this.application = application;
        this.stage = new Stage();
        this.gameInputProcessor = new GameInputProcessor();
        this.multiplexer = new InputMultiplexer(stage, gameInputProcessor);
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, Application.VIEWPORT_WIDTH, Application.VIEWPORT_HEIGHT);
        this.viewport = new FillViewport(Application.VIEWPORT_WIDTH, Application.VIEWPORT_HEIGHT, camera);
    }

    public abstract void handleInput();
    public abstract void update(float deltatime);
    public abstract void render(SpriteBatch sb);
    public abstract void load();
    public abstract void unload();

    @Override
    public void show() {
        stage.clear();
        application.getSpriteBatch().setProjectionMatrix(camera.combined);
        //viewport.apply();
        load();
        Gdx.input.setInputProcessor(multiplexer);
    }

    @Override
    public void render(float deltatime){
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        handleInput();
        stage.act(deltatime);
        update(deltatime);
        render(application.getSpriteBatch());
        stage.draw();
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

    @Override
    public void dispose() {
        unload();
        stage.dispose();
    }
}
