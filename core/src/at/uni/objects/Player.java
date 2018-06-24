package at.uni.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import at.uni.utils.Box2DHelper;
import at.uni.utils.InputData;

import static at.uni.utils.Box2DHelper.PPM;

// erzeugt von Markus

public class Player extends GameObject {

    private World world;
    private int facingDirection;
    private int maximumBombs = 1;
    private int health;
    private Bombs bombs;
    private int shieldCount = 0;

    private float movementSpeed = 5;

    public Player(World world, String name, float x, float y, Bombs bombs){
        this.texture = new Texture(name);
        this.bounds = new Rectangle(x, y, texture.getWidth(), texture.getHeight());
        this.world = world;
        this.bombs = bombs;
        setPosition(x - bounds.width / 2, y - bounds.height / 2);
        load(world);

        this.health = 100;

        facingDirection = 0;
    }

    //erzeugt den "Körper", auf dem Physics wirken
    @Override
    public void load(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(100 / PPM,100 / PPM);
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        this.body = world.createBody(bodyDef);

        /*
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(25 / PPM,25 / PPM);
*/
        CircleShape shape = new CircleShape();
        shape.setRadius(25 / PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.filter.categoryBits = Box2DHelper.BIT_PLAYER;
        fixtureDef.filter.maskBits = Box2DHelper.BIT_WALL + Box2DHelper.BIT_POWERUP + Box2DHelper.BIT_EXPLOSION + Box2DHelper.BIT_BRICK;
        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData(new GameObjectUserData(this, GameObjectUserData.EUserDataType.PLAYER));

        shape.dispose();
    }

    public void handleInput(InputData data) {
        // da wir keine Beschleunigung wollen, Normalisieren wir die Geschw.
        body.setLinearVelocity(0, 0);

        // Tastatur-Input Section - Markus
        if(data.isKeyDown(InputData.Key.Forward)){
            body.applyLinearImpulse(new Vector2(0, this.movementSpeed), body.getWorldCenter(), true);
            facingDirection = 0;
        }
        if(data.isKeyDown(InputData.Key.Backward)){
            body.applyLinearImpulse(new Vector2(0, -this.movementSpeed), body.getWorldCenter(), true);
            facingDirection = 1;
        }
        if(data.isKeyDown(InputData.Key.Left)){
            body.applyLinearImpulse(new Vector2(-this.movementSpeed, 0), body.getWorldCenter(), true);
            facingDirection = 2;
        }
        if(data.isKeyDown(InputData.Key.Right)){
            body.applyLinearImpulse(new Vector2(this.movementSpeed, 0), body.getWorldCenter(), true);
            facingDirection = 3;
        }

        if(data.isKeyPressed(InputData.Key.Space)){
            System.out.println("Direction: " + facingDirection);
            if (bombs.getBombs().size() < maximumBombs){
                bombs.addBomb(position.x, position.y);
            }
        }
        // Ende Tastatur-Input
    }

    @Override
    public void update(float deltatime){
        this.setPosition(body.getPosition().x * PPM, body.getPosition().y * PPM);
        this.setBounds(new Rectangle(position.x, position.y, texture.getWidth(), texture.getHeight()));
    }

    @Override
    public void render(SpriteBatch sb) {
        // hier wird der Spieler 'gezeichnet'
        sb.begin();
        sb.draw(texture, position.x - bounds.height / 2, position.y - bounds.width / 2);
        sb.end();
    }

    public void dispose(){

    }

    public void damageTaken(){
        if(this.isProtectedByShield())
        {
            System.out.println("Player took damage but was protected by shield powerup");
            this.shieldCount--;
            return;
        }
        this.health -= 40;
        System.out.println("damage taken, new health: "+health);
        if (this.health <= 0){
            System.out.println("Player died");
        }
    }

    public int getHealth() {
        return health;
    }

    public boolean isProtectedByShield()
    {
        boolean isProtectedByShield = this.shieldCount > 0;
        return isProtectedByShield;
    }

    public void activateShield()
    {
        System.out.println("Player is now protected by shield powerup");
        this.shieldCount++;
    }

    public void increaseMaxBombCount()
    {
        this.maximumBombs = clamp(this.maximumBombs + 1, 1, 10);
    }

    public void modifyMovementSpeed(float amount)
    {
        this.movementSpeed = clamp(this.movementSpeed + amount, 5, 10);
    }
}
