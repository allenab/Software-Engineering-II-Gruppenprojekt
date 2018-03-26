package at.uni.screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.sun.corba.se.impl.oa.poa.POAPolicyMediatorImpl_R_USM;

import at.uni.Application;

import static at.uni.utils.Box2DVars.PPM;


public class MainGameScreen extends AbstractScreen {

    private OrthographicCamera camera;
    private OrthographicCamera b2dCamera;
    private World world;
    private Box2DDebugRenderer b2dr;

    public MainGameScreen(Application application) {
        super(application);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Application.VIEWPORT_WIDTH, Application.VIEWPORT_HEIGHT);

        b2dCamera = new OrthographicCamera();
        b2dCamera.setToOrtho(false, Application.VIEWPORT_WIDTH / PPM, Application.VIEWPORT_HEIGHT / PPM);

        b2dr = new Box2DDebugRenderer();

        world = new World(new Vector2(0f, 0f), true);
    }

    @Override
    public void show() {
        //application.getSpriteBatch().setProjectionMatrix(camera.combined);

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(100 / PPM,100 / PPM);
        bodyDef.type = BodyDef.BodyType.StaticBody;

        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(25 / PPM,25 / PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);

    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float deltatime) {
        world.step(Application.STEP, 6,2);

    }

    @Override
    public void render(SpriteBatch sb) {
        b2dr.render(world, b2dCamera.combined);

    }

    @Override
    public void dispose() {

    }
}
