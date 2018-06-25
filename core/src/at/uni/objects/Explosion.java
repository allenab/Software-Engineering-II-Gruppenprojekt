package at.uni.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;

import at.uni.utils.Box2DHelper;
import at.uni.utils.InputData;

public final class Explosion extends GameObject {

    private World world = null;
    private Map map = null;
    ParticleEffect particleEffect = null;
    float activeTime = 0.5f;

    public Explosion(World world, Map map, float posx, float posy)
    {
        System.out.println("Explosion created");
        this.map = map;
        this.world = world;
        ParticleEffect effect = new ParticleEffect();
        effect.load(Gdx.files.internal("effects/splash.p"), Gdx.files.internal("effects"));
        effect.setPosition(posx, posy);
        effect.start();
        this.particleEffect = effect;
        this.body = Box2DHelper.createExplosion(this, world, posx, posy, 75, 75, false);
    }

    @Override
    public void load(World world) {

    }

    @Override
    public void handleInput(InputData data) {

    }

    @Override
    public void update(float deltatime) {

        this.activeTime -= deltatime;
        if(this.activeTime < 0)
        {
            System.out.println("Explosion should get destroyed now");
            this.dispose();
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        particleEffect.update(Gdx.graphics.getDeltaTime());
        particleEffect.draw(sb);
        sb.end();
    }

    @Override
    public void dispose() {
        this.particleEffect.dispose();
        map.toDestroy.add(this.getBody());
        map.explosions.remove(this);
    }
}
