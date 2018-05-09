package at.uni.handlers;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import at.uni.utils.InputData;
import at.uni.utils.InputData.Key;

public class GameInputProcessor implements InputProcessor, ApplicationListener {

    @Override
    public void create() {

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
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }



    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
