package at.uni.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import at.uni.utils.InputData;

public abstract class GameObject {

    protected Vector2 position;
    protected Rectangle bounds;
    protected Body body;
    protected Texture texture;
    protected float healthPoints;
    protected String name;

    public abstract void load(World world);
    public abstract void handleInput(InputData data);
    public abstract void update(float deltatime);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(float x, float y) {
        this.position = new Vector2(x, y);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public Body getBody() { return body; }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Texture getTexture() {
        return texture;
    }

    public void damage(float d) {
        if(healthPoints < d) {
            healthPoints = 0;
        } else {
            healthPoints -= d;
        }
    }

    public float getHP() {
        return healthPoints;
    }

}
