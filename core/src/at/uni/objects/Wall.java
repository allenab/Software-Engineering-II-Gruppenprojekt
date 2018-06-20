package at.uni.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import at.uni.utils.Box2DHelper;

import static at.uni.objects.Map.GRIDSIZE;

public class Wall extends GameObject {


    public Wall(float x, float y){
        position = new Vector2(x, y);
        bounds = new Rectangle(x, y, GRIDSIZE, GRIDSIZE);
        texture = new Texture ("images/wall.png");
    }

    @Override
    public void load(World world) {
        body = Box2DHelper.createBox(world, position.x, position.y, bounds.width, bounds.height, BodyDef.BodyType.StaticBody, false, Box2DHelper.BIT_WALL, Box2DHelper.BIT_PLAYER, Box2DHelper.BIT_WALL);
        body.setUserData(new GameObjectUserData(this, GameObjectUserData.EUserDataType.WALL));
    }


    public void update(float deltatime) {
        //This wall doesn't update anything.
        //but since its is a game object it must implement the update function.
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(texture, position.x - bounds.width / 2, position.y - bounds.height / 2, bounds.width,bounds.height);
        sb.end();
    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}
