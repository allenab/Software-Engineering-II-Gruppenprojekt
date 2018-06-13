package at.uni.objects;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import java.util.Set;
import java.util.Random;

import at.uni.utils.InputData;

public class Map extends GameObject {

    public static final float GRIDSIZE = 55f;
    public static final int NUM_ROWS = 9;
    public static final int NUM_COLUMS = 13;

    private List<List<GameObject>> map;
    public Set<Body> toDestroy = new HashSet<Body>();
    public Set<Powerup> spawnedPowerups = new HashSet<Powerup>();
    private World world;

    public Map(){
        map = new ArrayList<List<GameObject>>();
        Random random = new Random();
        int low = 1;
        int high = 100;

        for(int y = 0; y < NUM_ROWS; y++) {
            List<GameObject> row = new ArrayList<GameObject>();
            for(int x = 0; x < NUM_COLUMS; x++) {
                GameObject o;

                if (y == 0 || y == NUM_ROWS - 1) {
                    //erste oder letzte row
                    o = new Wall(x * GRIDSIZE, y * GRIDSIZE);
                } else if (x == 0 || x == NUM_COLUMS - 1) {
                    //erste oder letzte colum
                    o = new Wall(x * GRIDSIZE, y * GRIDSIZE);
                } else if((x % 2) == 0 && (y % 2) == 0) {
                    //fixierte Blöcke im Spielbereich
                    o = new Wall(x* GRIDSIZE, y*GRIDSIZE);
                } else if (x == 1 && y ==1 || x == 2 && y == 1 || x == 1 && y == 2){
                    //Spawnpunkte
                    o = new Floor(x* GRIDSIZE, y*GRIDSIZE);
                } else if (x == 1 && y == 6 || x == 1 && y == 7 ||  x == 2 && y== 7){
                    o = new Floor(x * GRIDSIZE, y * GRIDSIZE);
                } else if (x == 10 && y == 1 || x == 11 && y == 1 ||  x == 11 && y== 2){
                    o = new Floor(x*GRIDSIZE, y*GRIDSIZE);
                } else if (x == 11 && y == 6 || x == 11 && y == 7 ||  x == 10 && y== 7){
                    o = new Floor(x * GRIDSIZE, y*GRIDSIZE);
                } else if (x == 1 && y == 3 || x == 3 && y == 1 ||  x == 1 && y== 5 || x == 3 && y== 7){
                    // Sperrt Spawnpunkte
                    o = new Brick(x * GRIDSIZE, y * GRIDSIZE);
                } else if (x == 9 && y == 1 || x == 11 && y == 3 ||  x == 11 && y== 5 || x == 9 && y== 7){
                    o = new Brick(x*GRIDSIZE, y*GRIDSIZE);
                } else {
                    //random Verteilung
                    int rand = random.nextInt(high - low) + low;
                    if (rand <= 70){
                        o = new Brick(x * GRIDSIZE, y * GRIDSIZE);
                    } else {
                        o = new Floor(x * GRIDSIZE, y * GRIDSIZE);

                    }
                }


                row.add(o);

            }

            /*++
                    o = new Wall(x * GRIDSIZE, y * GRIDSIZE);
                } else{
                    if( x > (NUM_COLUMS / 2)){
                        o = new Brick(x * GRIDSIZE, y * GRIDSIZE);
                    } else {
                        o = new Floor(x * GRIDSIZE, y * GRIDSIZE);
                    }
                } */



            map.add(row);
        }
    }




    public void explosionCheck(Vector2 pos){
        int row = (int) ((int) (pos.y+10) / GRIDSIZE);
        int column = (int) ((int) (pos.x+10) / GRIDSIZE);
        GameObject top = map.get(row).get(column + 1);
        System.out.println("ExplosionCheck "+row+column);
        if (top instanceof Brick){
            ((Brick) top).OnDestroyedByExplosion(this, this.world);
            toDestroy.add(top.body);
            map.get(row).set(column+1, new Floor((column+1)*GRIDSIZE, row*GRIDSIZE));
        }
        GameObject right = map.get(row+1).get(column);
        if (right instanceof Brick){
            ((Brick) right).OnDestroyedByExplosion(this, this.world);
            toDestroy.add(right.body);
            map.get(row+1).set(column, new Floor(column*GRIDSIZE, (row+1)*GRIDSIZE));
        }
        GameObject bottom = map.get(row).get(column-1);
        if (bottom instanceof Brick){
            ((Brick) bottom).OnDestroyedByExplosion(this, this.world);
            toDestroy.add(bottom.body);
            map.get(row).set(column-1, new Floor((column-1)*GRIDSIZE, row*GRIDSIZE));
        }
        GameObject left = map.get(row-1).get(column);
        if (left instanceof Brick){
            ((Brick) left).OnDestroyedByExplosion(this, this.world);
            toDestroy.add(left.body);
            map.get(row-1).set(column, new Floor(column*GRIDSIZE, (row-1)*GRIDSIZE));
        }
    }

    public void destroyBlock(GameObject object){
        int blockX = (int) ((object.position.x + 1) / GRIDSIZE);
        int blockY = (int) ((object.position.y + 1) / GRIDSIZE);
        System.out.println("Object pos: "+object.position.x+"blockX "+blockX);

        GameObject temp = new Floor(object.getPosition().x, object.getPosition().y);
        map.get(blockX).set(blockY, temp);
    }

    @Override
    public void load(World world) {
        this.world = world;
        for(List<GameObject> row : map) {
            for(GameObject o : row) {
                o.load(world);
            }
        }
    }

    /*
    public void handleInput(InputData data) {

    }
    */

    public void update(float deltatime) {
        int count = world.getBodyCount();
        for (Body body: toDestroy) {
            if (count > 0) {
                world.destroyBody(body);
                count--;
            }
        }
        toDestroy.clear();
    }

    public void render(SpriteBatch sb) {
        for(List<GameObject> row : map) {
            for(GameObject o : row) {
                o.render(sb);
            }
        }
    }

    @Override
    public void dispose() {
        for(List<GameObject> row : map) {
            for(GameObject o : row) {
                o.dispose();
            }
        }
    }
}
