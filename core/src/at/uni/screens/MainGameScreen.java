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

import at.uni.Application;
import at.uni.objects.Player;
import at.uni.utils.InputData;

import static at.uni.utils.Box2DHelper.PPM;

public class MainGameScreen extends AbstractScreen implements ContactListener {

    private OrthographicCamera camera;
    private OrthographicCamera b2dCamera;
    private World world;
    private Box2DDebugRenderer b2dr;

    private Player player;
    private Player player2ForCollisionTesting;

    public MainGameScreen(Application application) {
        super(application);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Application.VIEWPORT_WIDTH, Application.VIEWPORT_HEIGHT);

        b2dCamera = new OrthographicCamera();
        b2dCamera.setToOrtho(false, Application.VIEWPORT_WIDTH / PPM, Application.VIEWPORT_HEIGHT / PPM);

        b2dr = new Box2DDebugRenderer();

        world = new World(new Vector2(0f, 0f), true);

        // ContactListener ist unsere MainGameScreen Klasse
        world.setContactListener(this);
    }

    @Override
    public void show() {
        //application.getSpriteBatch().setProjectionMatrix(camera.combined);

        // erzeugt einen Spieler
        this.player = new Player(world, "bomberman.png", 100 / PPM, 100 / PPM);

        player2ForCollisionTesting = new Player(world, "bomberman.png", 400 / PPM, 400 / PPM);

    }

    @Override
    public void handleInput() {
        // da wir keine Beschleunigung wollen, Normalisieren wir die Geschw.
        player.getBody().setLinearVelocity(0, 0);

        // Tastatur-Input Section - Markus
        if(InputData.isKeyDown(InputData.Key.Forward)){
            System.out.println("UP");
            player.getBody().applyLinearImpulse(new Vector2(0, 2), player.getBody().getWorldCenter(), true);
        }
        if(InputData.isKeyDown(InputData.Key.Backward)){
            System.out.println("DOWN");
            player.getBody().applyLinearImpulse(new Vector2(0, -2), player.getBody().getWorldCenter(), true);
        }
        if(InputData.isKeyDown(InputData.Key.Left)){
            System.out.println("LEFT");
            player.getBody().applyLinearImpulse(new Vector2(-2, 0), player.getBody().getWorldCenter(), true);
        }
        if(InputData.isKeyDown(InputData.Key.Right)){
            System.out.println("RIGHT");
            player.getBody().applyLinearImpulse(new Vector2(2, 0), player.getBody().getWorldCenter(), true);
        }
        // Ende Tastatur-Input
    }

    @Override
    public void update(float deltatime) {
        player.update();
        player2ForCollisionTesting.update();

        world.step(Application.STEP, 6,2);
    }

    @Override
    public void render(SpriteBatch sb) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        b2dr.render(world, b2dCamera.combined);

        // hier wird der Spieler 'gezeichnet'
        sb.begin();
        sb.draw(player, player.getX() - player.getHeight() / 2, player.getY() - player.getWidth() / 2);
            //Testobjekt - wird beschleunigt weil es ein DynamicType ist.
            sb.draw(player2ForCollisionTesting, player2ForCollisionTesting.getX() - player2ForCollisionTesting.getHeight() / 2,
                    player2ForCollisionTesting.getY() - player2ForCollisionTesting.getWidth() / 2);
        sb.end();
    }

    @Override
    public void dispose() {

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
