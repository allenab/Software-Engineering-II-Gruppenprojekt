package at.uni.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import at.uni.utils.Box2DHelper;

public class Bombs extends GameObject {
    private List<Bomb> bombs;
    private List<ParticleEffect> explosions;
    private World world;
    private Map map;
    private Sound explosionSound = Gdx.audio.newSound(Gdx.files.internal("sounds/explosion.mp3"));
    private Sound dropSound = Gdx.audio.newSound(Gdx.files.internal("sounds/bombdrop.mp3"));

    public Bombs(Map map){
        bombs = new ArrayList<Bomb>();
        explosions = new ArrayList<ParticleEffect>();
        this.map = map;
    }

    public void addBomb(float x, float y){
        dropSound.play(0.3f);
        Bomb bomb = new Bomb(x, y);
        bomb.load(world);
        bombs.add(bomb);
    }

    @Override
    public void load(World world) {
        this.world = world;
    }

    /*
    @Override
    public void handleInput(InputData data) {

    }
    */

    @Override
    public void update(float deltatime) {

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
                temp.update(0);
                ParticleEffect effect = new ParticleEffect();
                effect.load(Gdx.files.internal("effects/splash.p"), Gdx.files.internal("effects"));
                effect.setPosition(temp.getPosition().x, temp.getPosition().y);
                effect.start();
                this.explosions.add(effect);
            }
        }
        //remove bombs
        //explosionParticle(expired);
        handleExplosiveBombs(expired);
        bombs.removeAll(expired);

        sb.begin();
        Iterator iterator1 = explosions.iterator();
        while (iterator1.hasNext()){
            ParticleEffect temp = (ParticleEffect) iterator1.next();
            temp.update(Gdx.graphics.getDeltaTime());
            temp.draw(sb);
        }
        sb.end();
    }

    private void handleExplosiveBombs(List<Bomb> expired){
        for (Bomb bomb: expired) {
            explosionSound.play();
            map.explosionCheck(bomb.position);
            Body b = Box2DHelper.createExplosion(world, bomb.position.x, bomb.position.y, 105, 105, BodyDef.BodyType.DynamicBody, false, Box2DHelper.BIT_EXPLOSION, (short)(Box2DHelper.BIT_BRICK | Box2DHelper.BIT_PLAYER), Box2DHelper.BIT_WALL); }
    }

//    private void explosionParticle(List<Bomb> expired){
//        for (Bomb bomb: expired) {
//            float x = bomb.position.x;
//            float y = bomb.position.y;
//            Body north = Box2DHelper.createBox(world, x, y, 5, 5, BodyDef.BodyType.DynamicBody, false, Box2DHelper.BIT_WALL, Box2DHelper.BIT_WALL, Box2DHelper.BIT_WALL);
//            north.setUserData("Particle");
//            Body south = Box2DHelper.createBox(world, x, y, 5, 5, BodyDef.BodyType.DynamicBody, false, Box2DHelper.BIT_WALL, Box2DHelper.BIT_WALL, Box2DHelper.BIT_WALL);
//            south.setUserData("Particle");
//            Body east = Box2DHelper.createBox(world, x, y, 5, 5, BodyDef.BodyType.DynamicBody, false, Box2DHelper.BIT_WALL, Box2DHelper.BIT_WALL, Box2DHelper.BIT_WALL);
//            east.setUserData("Particle");
//            Body west = Box2DHelper.createBox(world, x, y, 5, 5, BodyDef.BodyType.DynamicBody, false, Box2DHelper.BIT_WALL, Box2DHelper.BIT_WALL, Box2DHelper.BIT_WALL);
//            west.setUserData("Particle");
//            north.applyLinearImpulse(new Vector2(0, 50), bomb.position, true);
//            south.applyLinearImpulse(new Vector2(0, -50), bomb.position, true);
//            east.applyLinearImpulse(new Vector2(50, 0), bomb.position, true);
//            west.applyLinearImpulse(new Vector2(-50, 0), bomb.position, true);
//        }
//    }

    @Override
    public void dispose() {
        Iterator iterator1 = explosions.iterator();
        while (iterator1.hasNext()){
            ParticleEffect temp = (ParticleEffect) iterator1.next();
            temp.dispose();
        }
        Iterator iterator = bombs.iterator();
        while (iterator.hasNext()){
            Bomb temp = (Bomb) iterator.next();
            temp.dispose();
        }

        explosionSound.dispose();
        dropSound.dispose();
    }

    public List<Bomb> getBombs() {
        return bombs;
    }
}
