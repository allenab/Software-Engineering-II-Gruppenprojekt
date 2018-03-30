package at.uni.handlers;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

import at.uni.utils.InputData;
import at.uni.utils.InputData.Button;

public class GameInputProcessor implements InputProcessor {
    @Override
    public boolean keyDown(int keycode) {
        switch (keycode){
            case Keys.UP:
                InputData.setButtonState(Button.Forward, true);
                break;

            case Keys.DOWN:
                InputData.setButtonState(Button.Backward, true);
                break;

            case Keys.LEFT:
                InputData.setButtonState(Button.Left, true);
                break;

            case Keys.RIGHT:
                InputData.setButtonState(Button.Right, true);
                break;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode){
            case Keys.UP:
                InputData.setButtonState(Button.Forward, false);
                break;

            case Keys.DOWN:
                InputData.setButtonState(Button.Backward, false);
                break;

            case Keys.LEFT:
                InputData.setButtonState(Button.Left, false);
                break;

            case Keys.RIGHT:
                InputData.setButtonState(Button.Right, false);
                break;
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
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
}
