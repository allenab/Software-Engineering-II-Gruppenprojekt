package at.uni.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import at.uni.utils.InputData;

import static at.uni.objects.Map.GRIDSIZE;

public class Bomb extends GameObject{

    private long countdown;

    public Bomb(float x, float y){
        position = new Vector2(x, y);
        bounds = new Rectangle(x, y, GRIDSIZE / 2, GRIDSIZE / 2);
        texture = new Texture("images/bomb.png");

        this.countdown = System.currentTimeMillis();
    }

    public long getCountdown() {
        return countdown;
    }

    @Override
    public void load(World world) {

    }

    @Override
    public void handleInput(InputData data) {

    }

    @Override
    public void update() {
//        long currentTime = System.currentTimeMillis();
//        if (currentTime - countdown > 5){
//            dispose();
//        }
    }

    @Override
    public void render(SpriteBatch sb) {

    }

    @Override
    public void dispose() {

    }
}
