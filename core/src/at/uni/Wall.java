package at.uni;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by elmedinasaldic on 26.03.18.
 */

public class Wall {
    private Texture texture;

    public Wall(Texture texture) {
        this.texture = texture;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }
}
