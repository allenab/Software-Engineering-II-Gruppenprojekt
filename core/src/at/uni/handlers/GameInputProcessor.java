package at.uni.handlers;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

import at.uni.utils.InputData;
import at.uni.utils.InputData.Key;

public class GameInputProcessor implements ApplicationListener, InputProcessor {

    private SpriteBatch batch;
    private BitmapFont font;
    private String message = "No gesture performed yet";
    private int w;
    private int h;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("data/arial-15.fnt"),false);
        font.setColor(Color.BLACK);
        w = Gdx.graphics.getWidth();
        h = Gdx.graphics.getHeight();

        InputMultiplexer im = new InputMultiplexer();
        GestureDetector gd = new GestureDetector((GestureDetector.GestureListener) this);
        im.addProcessor(gd);
        im.addProcessor(this);


        Gdx.input.setInputProcessor(im);

    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();

    }

    @Override
    public void render() {

       /* Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        TextBounds tb = font.getBounds(message);
        float x = w/2 - tb.width/2;
        float y = h/2 + tb.height/2;

        font.drawMultiLine(batch, message, x, y);

        batch.end();*/

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
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(pointer > 0){
            InputData.setTouchPoint(pointer, screenX, screenY);
        } else if(button > 0){
            InputData.setTouchPoint(button, screenX, screenY);
        } else {
            InputData.setTouchPoint(0, screenX, screenY);
        }
        return true;
    }


    @Override
    public boolean keyDown(int keycode) {
        switch (keycode){
            case Keys.UP:
                InputData.setKeyState(Key.Forward, true);
                break;

            case Keys.DOWN:
                InputData.setKeyState(Key.Backward, true);
                break;

            case Keys.LEFT:
                InputData.setKeyState(Key.Left, true);
                break;

            case Keys.RIGHT:
                InputData.setKeyState(Key.Right, true);
                break;

            case Keys.SPACE:
                InputData.setKeyState(Key.Space, true);
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode){
            case Keys.UP:
                InputData.setKeyState(Key.Forward, false);
                break;

            case Keys.DOWN:
                InputData.setKeyState(Key.Backward, false);
                break;

            case Keys.LEFT:
                InputData.setKeyState(Key.Left, false);
                break;

            case Keys.RIGHT:
                InputData.setKeyState(Key.Right, false);
                break;
            case Keys.SPACE:
                InputData.setKeyState(Key.Space, false);
                break;
        }
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }



    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(pointer > 0){
            InputData.setReleasePoint(pointer, screenX, screenY);
        } else if(button > 0){
            InputData.setReleasePoint(button, screenX, screenY);
        } else {
            InputData.setReleasePoint(0, screenX, screenY);
        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        message = "Mouse moved";
        Gdx.app.log("INFO", message);
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        message = "Scrolled";
        Gdx.app.log("INFO", message);
        return false;
    }



}
