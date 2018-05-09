package at.uni.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;

import java.io.IOException;

import at.uni.Application;
import at.uni.net.KittenClient;
import at.uni.net.KittenServer;
import at.uni.objects.Map;
import at.uni.objects.Player;
import at.uni.utils.InputData;

import static at.uni.utils.Box2DHelper.PPM;

public class MainGameServerScreen extends AbstractScreen implements ContactListener {

    private OrthographicCamera camera;
    private OrthographicCamera b2dCamera;
    private World world;
    private Box2DDebugRenderer b2dr;

    private Player player;
    private Player remotePlayer;
    private Map map;

    private KittenServer server;

    public MainGameServerScreen(Application application) {
        super(application);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Application.VIEWPORT_WIDTH, Application.VIEWPORT_HEIGHT);

        b2dCamera = new OrthographicCamera();
        b2dCamera.setToOrtho(false, Application.VIEWPORT_WIDTH / PPM, Application.VIEWPORT_HEIGHT / PPM);

        b2dr = new Box2DDebugRenderer();

        world = new World(new Vector2(0f, 0f), true);

        // ContactListener ist unsere MainGameScreen Klasse
        world.setContactListener(this);

        map = new Map();


    }

    @Override
    public void load() {
        //application.getSpriteBatch().setProjectionMatrix(camera.combined);

        try {
            server = new KittenServer();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // erzeugt einen Spieler
        player = new Player(world, "bomberman.png", 100 / PPM, 100 / PPM);
        server.addGameObject(0, player);

        map.load(world);
    }

    @Override
    public void handleInput() {
        player.handleInput(new InputData());
        if(remotePlayer != null)
            remotePlayer.setPosition(server.getPlayer(1).getPosition().x / PPM, server.getPlayer(1).getPosition().y / PPM);
    }

    @Override
    public void update(float deltatime) {
        if(server.getPlayer(1) != null && remotePlayer == null){
            remotePlayer = new Player(world, "bomberman.png", server.getPlayer(1).getPosition().x / PPM, server.getPlayer(1).getPosition().y / PPM);
        }


        player.update(deltatime);
        if(remotePlayer != null)
            remotePlayer.update(deltatime);

        server.updatePlayer(0, player.getPosition());

        map.update(deltatime);

        world.step(Application.STEP, 6,2);
    }

    @Override
    public void render(SpriteBatch sb) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        b2dr.render(world, b2dCamera.combined);

        map.render(sb);

        player.render(sb);
        if(remotePlayer != null)
            remotePlayer.render(sb);
    }

    @Override
    public void unload() {
        map.dispose();
    }

    // METHODEN FUER DIE COLLISION DETECTION
    @Override
    public void beginContact(Contact contact) {
        System.out.println("Kontakt initiiert");
        // um herauszufinden welche Objekte miteinander kollidieren
        Fixture fixtureA = contact.getFixtureA(), fixtureB = contact.getFixtureB();

        if (fixtureA.getUserData() == "Player"){
            System.out.println("Fixture A = Player");
        } else if (fixtureB.getUserData() == "Player"){
            System.out.println("Fixture B = Player");
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
