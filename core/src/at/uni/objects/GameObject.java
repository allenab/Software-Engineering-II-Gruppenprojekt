package at.uni.objects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public abstract class GameObject {

    protected Vector2 position;
    protected Rectangle bounds;

    public abstract Body getBody();

}
