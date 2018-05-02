package at.uni.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import at.uni.utils.InputData;

import static at.uni.utils.Box2DHelper.PPM;

// erzeugt von Markus

public class Player extends GameObject {

    private World world;
    private List<Bomb> bombs;
    private int facingDirection;
    private int maximumBombs = 3;
    private int health;
    private List<ParticleEffect> explosions;

    public Player(World world, String name, float x, float y){
        this.texture = new Texture(name);
        this.bounds = new Rectangle(x, y, texture.getWidth(), texture.getHeight());
        this.world = world;
        setPosition(x - bounds.width / 2, y - bounds.height / 2);
        load(world);
        facingDirection = 0;
        bombs = new ArrayList<Bomb>();

        this.health = 100;

        facingDirection = 0;
        bombs = new ArrayList<Bomb>();
        explosions = new LinkedList<ParticleEffect>();
    }

    //erzeugt den "Körper", auf dem Physics wirken
    @Override
    public void load(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(100 / PPM,100 / PPM);
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        this.body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(25 / PPM,25 / PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData("Player");

        shape.dispose();
    }

    @Override
    public void handleInput(InputData data) {
        // da wir keine Beschleunigung wollen, Normalisieren wir die Geschw.
        body.setLinearVelocity(0, 0);

        // Tastatur-Input Section - Markus
        if(data.isKeyDown(InputData.Key.Forward)){
            body.applyLinearImpulse(new Vector2(0, 5), body.getWorldCenter(), true);
            facingDirection = 0;
        }
        if(data.isKeyDown(InputData.Key.Backward)){
            body.applyLinearImpulse(new Vector2(0, -5), body.getWorldCenter(), true);
            facingDirection = 1;
        }
        if(data.isKeyDown(InputData.Key.Left)){
            body.applyLinearImpulse(new Vector2(-5, 0), body.getWorldCenter(), true);
            facingDirection = 2;
        }
        if(data.isKeyDown(InputData.Key.Right)){
            body.applyLinearImpulse(new Vector2(5, 0), body.getWorldCenter(), true);
            facingDirection = 3;
        }

        if(data.isKeyPressed(InputData.Key.Space)){
            System.out.println("Direction: " + facingDirection);
            if (bombs.size() < maximumBombs){
                Bomb bomb = new Bomb(position.x, position.y);
                bomb.load(world);
                bombs.add(bomb);
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
        //Bomben zeichnen, falls vorhanden
        List<Bomb> expired = new ArrayList<Bomb>();
        Iterator iterator = bombs.iterator();
        while (iterator.hasNext()){
            Bomb temp = (Bomb) iterator.next();
            long bombTime = temp.getCreationTime();
            if (System.currentTimeMillis() - bombTime < 5000){
                temp.render(sb);
            } else {
                expired.add(temp);
                temp.update();
                ParticleEffect effect = new ParticleEffect();
                effect.load(Gdx.files.internal("effects/splash.p"), Gdx.files.internal("effects"));
                effect.setPosition(temp.getPosition().x, temp.getPosition().y);
                effect.start();
                this.explosions.add(effect);
            }
        }

        // hier wird der Spieler 'gezeichnet'
        sb.begin();
        sb.draw(texture, position.x - bounds.height / 2, position.y - bounds.width / 2);
        Iterator iterator1 = explosions.iterator();
        while (iterator1.hasNext()){
            ParticleEffect temp = (ParticleEffect) iterator1.next();
            temp.update(Gdx.graphics.getDeltaTime());
            temp.draw(sb);
        }
        sb.end();
    }

    public void dispose(){

    }

    public void damageTaken(){
        this.health -= 40;
        if (this.health <= 0){
            System.out.println("Player died");
        }
    }
}
