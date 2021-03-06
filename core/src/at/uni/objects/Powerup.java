package at.uni.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import at.uni.utils.Box2DHelper;

import static at.uni.objects.Map.GRIDSIZE;
import static at.uni.utils.Box2DHelper.PPM;

public abstract class Powerup extends GameObject{

    EPowerUpType type = null;

    enum EPowerUpType {
        SHIELD
    };

    public Powerup(World world, float x, float y)
    {
        position = new Vector2(x, y);
        bounds = new Rectangle(x, y, GRIDSIZE, GRIDSIZE);
        texture = new Texture("images/powerup_dummy.png");
        this.load(world);
    }

    @Override
    public void update(float deltatime)
    {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(texture, position.x - bounds.width / 2, position.y - bounds.height / 2, bounds.width,bounds.height);
        sb.end();
    }

    @Override
    public void load(World world) {
        body = Box2DHelper.createBox(world, position.x, position.y, bounds.width, bounds.height, BodyDef.BodyType.StaticBody, false, (short)0, (short)0, (short)0);
        body.setUserData(new GameObjectUserData(this, GameObjectUserData.EUserDataType.POWERUP));
    }

    @Override
    public void dispose()
    {
        this.texture.dispose();
    }

    abstract public void OnCollectedByPlayer(Player player);

    public void OnHitByBomb()
    {

    };
}
