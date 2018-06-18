package at.uni.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import at.uni.objects.GameObjectUserData;

public final class Box2DHelper {

    private Box2DHelper(){}

    public static final float PPM = 32f;

    public static final short BIT_WALL = 1;
    public static final short BIT_PLAYER = 2;

    public static final short BIT_POWERUP = 4;
    public static final short BIT_BOMB = 8;
    public static final short BIT_EXPLOSION = 16;
    public static final short BIT_BRICK = 32;

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

        Body body = world.createBody(bodyDef);

        Fixture fixture = body.createFixture(fixtureDef);
        return body;
    }

    public static Body createExplosion(final World world, float x, float y,  float w, float h, BodyDef.BodyType type, boolean canRotate, short cBits, short mBits, short gIndex){
        BodyDef bodyDef = new BodyDef();
        bodyDef.fixedRotation = canRotate;
        bodyDef.type = type;
        bodyDef.position.set(x / PPM, y / PPM);

//        Vector2[] vertices = new Vector2[12];
//
//        vertices[0] = new Vector2(-27f  , 0f  );
//        vertices[1] = new Vector2(0f , -55f  );
//        vertices[2] = new Vector2(27f , 0f);
//        vertices[3] = new Vector2(0f , -27f);
//        vertices[4] = new Vector2(55f , 0f);
//        vertices[5] = new Vector2(0f , 27f);
//        vertices[6] = new Vector2(27f  , 0f  );
//        vertices[7] = new Vector2(0f , 55f  );
//        vertices[8] = new Vector2(-27f , 0f);
//        vertices[9] = new Vector2(0f , 27f);
//        vertices[10] = new Vector2(-55f , 0f);
//        vertices[11] = new Vector2(0f , -27f);
        PolygonShape shape1 = new PolygonShape();
        shape1.setAsBox(w / 2 / PPM / 2, h / PPM / 2);

        FixtureDef fixtureDef1 = new FixtureDef();
        fixtureDef1.shape = shape1;
        fixtureDef1.density = 1.0f;
        fixtureDef1.filter.categoryBits = cBits;
        fixtureDef1.filter.maskBits = mBits;
        fixtureDef1.filter.groupIndex = gIndex;
        fixtureDef1.isSensor = true;

        PolygonShape shape2 = new PolygonShape();
        shape2.setAsBox(w / PPM / 2, h / 2 / PPM / 2);

        FixtureDef fixtureDef2 = new FixtureDef();
        fixtureDef2.shape = shape2;
        fixtureDef2.density = 1.0f;
        fixtureDef2.filter.categoryBits = cBits;
        fixtureDef2.filter.maskBits = mBits;
        fixtureDef2.filter.groupIndex = gIndex;
        fixtureDef2.isSensor = true;
        Body b = world.createBody(bodyDef);

        Fixture fix1 = b.createFixture(fixtureDef1);
        fix1.setUserData(new GameObjectUserData(null, GameObjectUserData.EUserDataType.EXPLOSION));

        Fixture fix2 = b.createFixture(fixtureDef2);
        fix2.setUserData(new GameObjectUserData(null, GameObjectUserData.EUserDataType.EXPLOSION));

        //b.createFixture(shape1, 1.0f);
        //b.createFixture(shape2, 1.0f).setSensor(true);
        return b;
    }

}
