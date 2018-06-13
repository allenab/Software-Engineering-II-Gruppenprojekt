package at.uni.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import at.uni.utils.Box2DHelper;
import at.uni.utils.InputData;

import static at.uni.objects.Map.GRIDSIZE;

public class Wall extends GameObject {


    public Wall(float x, float y){
        position = new Vector2(x, y);
        bounds = new Rectangle(x, y, GRIDSIZE, GRIDSIZE);
    }

    public Wall(float x, float y, float w, float h){
        position = new Vector2(x, y);
        bounds = new Rectangle(x, y, w, h);
    }

    @Override
    public void load(World world) {
        body = Box2DHelper.createBox(world, position.x, position.y, bounds.width, bounds.height, BodyDef.BodyType.StaticBody, false, Box2DHelper.BIT_WALL, Box2DHelper.BIT_WALL, Box2DHelper.BIT_WALL);
    }

    @Override
    public void handleInput(InputData data) {

    }


    public void update(float deltatime) {

    }

    @Override
    public void render(SpriteBatch sb) {
    }

    @Override
    public void dispose() {

    }
}
