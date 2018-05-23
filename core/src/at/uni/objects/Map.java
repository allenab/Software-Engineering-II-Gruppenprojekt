package at.uni.objects;


import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

import at.uni.utils.InputData;

public class Map extends GameObject {

    public static final float GRIDSIZE = 55f;
    public static final int NUM_ROWS = 9;
    public static final int NUM_COLUMS = 13;

    //private List<List<GameObject>> map;
    //public List<Body> toDestroy = new ArrayList<Body>();
    //private World world;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;
    private OrthographicCamera camera;

    public Map(OrthographicCamera camera){

        this.camera = camera;

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("maps/levelOne.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map);
        mapRenderer.setView(camera);

        /*map = new ArrayList<List<GameObject>>();

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
                    if( x > (NUM_COLUMS / 2)){
                        o = new Brick(x * GRIDSIZE, y * GRIDSIZE);
                    } else {
                        o = new Floor(x * GRIDSIZE, y * GRIDSIZE);
                    }
                }

                row.add(o);
            }
            map.add(row);
        }
        */

    }

    /*
    public void explosionCheck(Vector2 pos){
        int row = (int) ((int) (pos.y+10) / GRIDSIZE);
        int column = (int) ((int) (pos.x+10) / GRIDSIZE);
        GameObject top = map.get(row).get(column + 1);
        System.out.println("ExplosionCheck "+row+column);
        if (top instanceof Brick){
            toDestroy.add(top.body);
            map.get(row).set(column+1, new Floor((column+1)*GRIDSIZE, row*GRIDSIZE));
        }
        GameObject right = map.get(row+1).get(column);
        if (right instanceof Brick){
            toDestroy.add(right.body);
            map.get(row+1).set(column, new Floor(column*GRIDSIZE, (row+1)*GRIDSIZE));
        }
        GameObject bottom = map.get(row).get(column-1);
        if (bottom instanceof Brick){
            toDestroy.add(bottom.body);
            map.get(row).set(column-1, new Floor((column-1)*GRIDSIZE, row*GRIDSIZE));
        }
        GameObject left = map.get(row-1).get(column);
        if (left instanceof Brick){
            toDestroy.add(left.body);
            map.get(row-1).set(column, new Floor(column*GRIDSIZE, (row-1)*GRIDSIZE));
        }
    }*/

    /*public void destroyBlock(GameObject object){
        int blockX = (int) ((object.position.x + 1) / GRIDSIZE);
        int blockY = (int) ((object.position.y + 1) / GRIDSIZE);
        System.out.println("Object pos: "+object.position.x+"blockX "+blockX);

        GameObject temp = new Floor(object.getPosition().x, object.getPosition().y);
        map.get(blockX).set(blockY, temp);
    }*/

    @Override
    public void load(World world) {

        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rectangle = ((RectangleMapObject) object) .getRectangle();
            Wall wall = new Wall(rectangle.getX() + rectangle.getWidth() / 2, rectangle.getY() + rectangle.getHeight() / 2, rectangle.getWidth(), rectangle.getHeight());
            wall.load(world);
        }

        for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rectangle = ((RectangleMapObject) object) .getRectangle();
            Brick brick = new Brick(rectangle.getX() + rectangle.getWidth() / 2, rectangle.getY() + rectangle.getHeight() / 2, rectangle.getWidth(), rectangle.getHeight());
            brick.load(world);
        }

        /*this.world = world;
        for(List<GameObject> row : map) {
            for(GameObject o : row) {
                o.load(world);
            }
        }*/

    }

    public void handleInput(InputData data) {

    }

    public void update(float deltatime) {
        /*for (Body body: toDestroy) {
            world.destroyBody(body);
        }
        toDestroy.clear();
        */

    }

    public void render(SpriteBatch sb) {
        /*for(List<GameObject> row : map) {
            for(GameObject o : row) {
                o.render(sb);
            }
        }*/
        mapRenderer.render();
    }

    @Override
    public void dispose() {
        mapRenderer.dispose();
    }
}
