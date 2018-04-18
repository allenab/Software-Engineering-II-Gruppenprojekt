package at.uni.objects;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;
import java.util.List;

import at.uni.utils.InputData;

public class Map extends GameObject {

    public static final float GRIDSIZE = 55f;
    public static final int NUM_ROWS = 9;
    public static final int NUM_COLUMS = 13;

    private List<List<GameObject>> map;

    public Map(){
        map = new ArrayList<List<GameObject>>();

        for(int y = 0; y < NUM_ROWS; y++) {
            List<GameObject> row = new ArrayList<GameObject>();
            for(int x = 0; x < NUM_COLUMS; x++) {
                GameObject o;

                if (y == 0 || y == NUM_ROWS - 1) {
                    //erste oder letzte row
                    o = new Wall(x * GRIDSIZE, y * GRIDSIZE);
                } else if (x == 0 || x == NUM_COLUMS - 1) {
                    //erste oder letzt colum
                    o = new Wall(x * GRIDSIZE, y * GRIDSIZE);
                } else if((x % 2) == 0 && (y % 2) == 0) {
                    o = new Wall(x * GRIDSIZE, y * GRIDSIZE);
                }else {
                    o = new at.uni.objects.Floor(x * GRIDSIZE, y * GRIDSIZE);
                }

                row.add(o);
            }
            map.add(row);
        }

    }

    @Override
    public void load(World world) {
        for(List<GameObject> row : map) {
            for(GameObject o : row) {
                o.load(world);
            }
        }
    }

    public void handleInput(InputData data) {

    }

    public void update() {

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
