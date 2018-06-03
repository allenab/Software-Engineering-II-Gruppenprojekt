package at.uni.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public final class Box2DHelper {

    private Box2DHelper(){}

    public static final float PPM = 32f;

    public static final short BIT_WALL = 1;
    public static final short BIT_PLAYER = 2;


    public static Body createBox(final World world, float x, float y, float w, float h, BodyDef.BodyType type, boolean canRotate, short cBits, short mBits, short gIndex){
        BodyDef bodyDef = new BodyDef();
        bodyDef.fixedRotation = canRotate;
        bodyDef.type = type;
        bodyDef.position.set(x / PPM, y / PPM);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(w / PPM / 2, h / PPM / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;
        fixtureDef.filter.categoryBits = cBits;
        fixtureDef.filter.maskBits = mBits;
        fixtureDef.filter.groupIndex = gIndex;

        return world.createBody(bodyDef).createFixture(fixtureDef).getBody();
    }

    public static Body createExplosion(final World world, float x, float y, BodyDef.BodyType type, boolean canRotate, short cBits, short mBits, short gIndex){
        BodyDef bodyDef = new BodyDef();
        bodyDef.fixedRotation = canRotate;
        bodyDef.type = type;
        bodyDef.position.set(x / PPM, y / PPM);

        Vector2[] vertices = new Vector2[11];

        vertices[0] = new Vector2(-27f  , 0f  );
        vertices[1] = new Vector2(0f , -55f  );
        vertices[2] = new Vector2(27f , 0f);
        vertices[3] = new Vector2(0f , -27f);
        vertices[4] = new Vector2(55f , 0f);
        vertices[5] = new Vector2(0f , 27f);
        vertices[6] = new Vector2(27f  , 0f  );
        vertices[7] = new Vector2(0f , 55f  );
        vertices[8] = new Vector2(-27f , 0f);
        vertices[9] = new Vector2(0f , 27f);
        vertices[10] = new Vector2(-55f , 0f);
        vertices[11] = new Vector2(0f , -27f);
        PolygonShape shape = new PolygonShape();
        shape.set(vertices);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;
        fixtureDef.filter.categoryBits = cBits;
        fixtureDef.filter.maskBits = mBits;
        fixtureDef.filter.groupIndex = gIndex;

        return world.createBody(bodyDef).createFixture(fixtureDef).getBody();
    }

}
