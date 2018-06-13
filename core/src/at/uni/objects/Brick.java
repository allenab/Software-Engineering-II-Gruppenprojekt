package at.uni.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import java.util.Random;

import at.uni.utils.Box2DHelper;
import at.uni.utils.InputData;

import static at.uni.objects.Map.GRIDSIZE;

public class Brick extends GameObject {

    public Brick(float x, float y){
        position = new Vector2(x, y);
        bounds = new Rectangle(x, y, GRIDSIZE, GRIDSIZE);
    }

    public Brick(float x, float y, float w, float h){
        position = new Vector2(x, y);
        bounds = new Rectangle(x, y, GRIDSIZE, GRIDSIZE);
    }

    @Override
    public void load(World world) {
        body = Box2DHelper.createBox(world, position.x, position.y, bounds.width, bounds.height, BodyDef.BodyType.StaticBody, false, Box2DHelper.BIT_WALL, Box2DHelper.BIT_WALL, Box2DHelper.BIT_WALL);
        body.setUserData(new GameObjectUserData(this, GameObjectUserData.EUserDataType.BRICK));
    }

    /*
    public void handleInput(InputData data) {

    }
    */

    public void update(float deltatime) {

    }

    public void render(SpriteBatch sb) {

    }

    @Override
    public void dispose() {

    }

    public void OnDestroyedByExplosion(Map map, World world)
    {
        if(map.spawnedPowerups.size() > 3)
        {
            return;
        }
        int rngnumber = (int)(Math.random()*100);
        if (rngnumber > 50)
        {
            PowerupShield shield = new PowerupShield(world, this.position.x, this.position.y);
            map.spawnedPowerups.add(shield);
        }

        // 1. nimm coordinaten von diesem Brick
        // 2. rolle random() RNG
        // 3. if random() > x (irgendeine zahl) -> spawn POwerupSHield object an coordinates von diesem brick
        // 4. zerstören diesen brick0
    }
}
