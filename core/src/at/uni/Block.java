package at.uni;

import com.badlogic.gdx.graphics.g2d.Sprite;

import java.awt.Rectangle;

/**
 * Created by markus on 22.03.18.
 */

public class Block {

    public final Sprite image;
    public final Rectangle bounds;

    public Block( Sprite sprite, int i, int j){
        image = sprite;
        bounds = new Rectangle(i, j, 16, 16);
    }

}
