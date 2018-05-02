package at.uni.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import at.uni.utils.InputData;

import static at.uni.utils.Box2DHelper.PPM;

// erzeugt von Markus
// Extended Sprite und NICHT GameObject, bitte daweil so lassen

public class Player extends Sprite {

    private World world;
    private Body body;

    public Player(World world, String name, float x, float y){
        super(new Texture(name));
        this.world = world;
        setPosition(x - getWidth() / 2, y - getHeight() / 2);
        createBody();
    }

    //erzeugt den "Körper", auf dem Physics wirken
    private void createBody(){
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

    public void update(){
        this.setPosition(body.getPosition().x * PPM, body.getPosition().y * PPM);
    }

    public void handleInput(){
        // da wir keine Beschleunigung wollen, Normalisieren wir die Geschw.
        body.setLinearVelocity(0, 0);

        // Tastatur-Input Section - Markus
        if(InputData.isKeyDown(InputData.Key.Forward)){
            body.applyLinearImpulse(new Vector2(0, 2), body.getWorldCenter(), true);
        }
        if(InputData.isKeyDown(InputData.Key.Backward)){
            body.applyLinearImpulse(new Vector2(0, -2), body.getWorldCenter(), true);
        }
        if(InputData.isKeyDown(InputData.Key.Left)){
            body.applyLinearImpulse(new Vector2(-2, 0), body.getWorldCenter(), true);
        }
        if(InputData.isKeyDown(InputData.Key.Right)){
            body.applyLinearImpulse(new Vector2(2, 0), body.getWorldCenter(), true);
        }
        // Ende Tastatur-Input
    }

    public Body getBody() {
        return body;
    }

    public void dispose(){

    }
}
