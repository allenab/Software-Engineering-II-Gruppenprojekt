package at.uni;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by markus on 21.03.18.
 */

public class Assets {
    //In diesem File sammeln sich alle Assets, um die Game Klasse ubersichtlich zu halten

    public static SpriteBatch batch;
    public static Texture backgrounds;
    public static Sprite wall;
    public static Sprite freeSpace;
    public static Sprite player;


    public static void load(){
        batch = new SpriteBatch();
        backgrounds = new Texture("android/assets/spritesheet_test.png");

        wall = new Sprite(backgrounds, 0, 6, 16, 16);
        freeSpace = new Sprite(backgrounds, 17, 8, 16, 16);

        player = new Sprite(Assets.backgrounds, 492, 380, 15, 15);
    }
}
