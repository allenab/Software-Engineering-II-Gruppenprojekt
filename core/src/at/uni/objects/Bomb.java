package at.uni.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;


import static at.uni.objects.Map.GRIDSIZE;

public class Bomb extends GameObject{

    private long creationTime;

    public Bomb(float x, float y){
        position = new Vector2(x, y);
        bounds = new Rectangle(x, y, GRIDSIZE / 2, GRIDSIZE / 2);
        texture = new Texture("images/bomb.png");

        this.creationTime = System.currentTimeMillis();
    }

    public long getCreationTime() {
        return creationTime;
    }

    @Override
    public void load(World world) {

    }

    @Override
    public void handleInput(InputData data) {

    }

    @Override
    public void update(float deltatime) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - creationTime > 5){
            explode();
        }
    }

    private void explode(){
        System.out.println("I EXPLODE NOW");
//        effect.load(Gdx.files.internal("effects/splash.p"), Gdx.files.internal("effects"));
//        effect.setPosition(position.x, position.y);
//        effect.start();
//        explosion = true;
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(texture, position.x - bounds.width / 2, position.y - bounds.height / 2, bounds.width,bounds.height);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
