package at.uni;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by elmedinasaldic on 26.03.18.
 */

public class Floor {
    private int x;
    private int y;
    private Ground ground;
    private Wall wall;


    public Floor(int x, int y, Ground ground) {
        this.x = x;
        this.y = y;
        this.ground = ground;
    }

    public Floor(int x, int y, Wall wall) {
        this.x = x;
        this.y = y;
        this.wall = wall;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Ground getGround() {
        return ground;
    }

    public void setGround(Ground ground) {
        this.ground = ground;
    }

    public Wall getWall() {
        return wall;
    }

    public void setWall(Wall wall) {
        this.wall = wall;
    }
}
