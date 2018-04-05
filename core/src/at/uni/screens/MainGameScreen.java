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

import at.uni.Application;
import at.uni.objects.Player;
import at.uni.utils.InputData;

import static at.uni.utils.Box2DHelper.PPM;


public class MainGameScreen extends AbstractScreen {

    private OrthographicCamera camera;
    private OrthographicCamera b2dCamera;
    private World world;
    private Box2DDebugRenderer b2dr;

    private Player player;

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

        this.player = new Player(world, "bomberman.png", 100 / PPM, 100 / PPM);

    }

    @Override
    public void handleInput() {
        if(InputData.isKeyDown(InputData.Key.Forward)){
            System.out.println("UP");
        }
        if(InputData.isKeyDown(InputData.Key.Backward)){
            System.out.println("DOWN");
        }
        if(InputData.isKeyDown(InputData.Key.Left)){
            System.out.println("LEFT");
            player.getBody().applyLinearImpulse(new Vector2(-1, 0), player.getBody().getWorldCenter(), true);
        }
        if(InputData.isKeyDown(InputData.Key.Right)){
            System.out.println("RIGHT");
        }
    }

    @Override
    public void update(float deltatime) {
        player.update();
        world.step(Application.STEP, 6,2);
    }

    @Override
    public void render(SpriteBatch sb) {
        b2dr.render(world, b2dCamera.combined);

        sb.begin();
        sb.draw(player, player.getX() - player.getHeight() / 2, player.getY() - player.getWidth() / 2);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
