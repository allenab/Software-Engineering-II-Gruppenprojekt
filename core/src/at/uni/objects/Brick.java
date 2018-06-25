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
        texture = new Texture("images/brick.png");
    }

    @Override
    public void load(World world) {
        body = Box2DHelper.createBrick(this, world, position.x, position.y, bounds.width, bounds.height);
    }

    @Override
    public void handleInput(InputData data) {

    }

    public void update(float deltatime) {

    }

    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(texture, position.x - bounds.width / 2, position.y - bounds.height / 2, bounds.width,bounds.height);
        sb.end();
    }

    @Override
    public void dispose() {
        texture.dispose();
    }

    public void OnDestroyedByExplosion(Map map, World world)
    {
        if(map.spawnedPowerups.size() > 3)
        {
            return;
        }
        int rngnumber = (int)(Math.random()*100);
        if (rngnumber > 80)
        {
            PowerupShield shield = new PowerupShield(world, this.position.x, this.position.y);
            map.spawnedPowerups.add(shield);
        }
        else if (rngnumber > 60)
        {
            PowerupMoreBombs powerup = new PowerupMoreBombs(world, this.position.x, this.position.y);
            map.spawnedPowerups.add(powerup);
        }
        else if (rngnumber > 40)
        {
            PowerupSpeedUp powerup = new PowerupSpeedUp(world, this.position.x, this.position.y);
            map.spawnedPowerups.add(powerup);
        }
    }
}
