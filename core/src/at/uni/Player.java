package at.uni;

import com.badlogic.gdx.graphics.g2d.Sprite;

import java.awt.Rectangle;

/**
 * Created by markus on 22.03.18.
 */

public class Player {

    public final Sprite image;
    public final Rectangle bounds;

    public Player(int posX, int posY){
        image = Assets.player;
        bounds = new Rectangle(posX, posY, 15, 15);
    }

}
