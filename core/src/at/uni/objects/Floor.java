package at.uni.objects;

/**
 * Created by elmedinasaldic on 26.03.18.
 */


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import at.uni.utils.InputData;

import static at.uni.objects.Map.GRIDSIZE;

public class Floor extends GameObject {

    public Floor(float x, float y){
        position = new Vector2(x, y);
        bounds = new Rectangle(x, y, GRIDSIZE, GRIDSIZE);
    }

    @Override
    public void load(World world) {

    }

    public void handleInput(InputData data) {

    }

    public void update(float deltatime) {

    }

    public void render(SpriteBatch sb) {
    }

    @Override
    public void dispose() {

    }

}
