package at.uni;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Random;

public class Game extends ApplicationAdapter {
	SpriteBatch batch;
	ArrayList<Floor> map;
	int width = 6;
	int height = 4;
	int floorWidth = 256;
	int floorHeight = 256;
	
	@Override
	public void create () {
		batch = new SpriteBatch();

		map = new ArrayList<Floor>();

		for (int x = 0; x < width; x++){
			for(int y = 0;  y < height; y++){

				int random = new Random().nextInt(2);

				if(random == 1) {
					map.add(
							new Floor(
									x * floorWidth,
									y * floorHeight,
									new Ground(new Texture("badlogic.jpg"))
							)
					);
				} else if(random == 0) {
					map.add(
							new Floor(
									x * floorWidth,
									y * floorHeight,
									new Wall(new Texture("wall.jpg"))
							)
					);
				}
			}
		}
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		for(int i = 0; i < map.size(); i++){
			if(map.get(i).getGround() != null) {
				batch.begin();
				batch.draw(map.get(i).getGround().getTexture(), map.get(i).getX(), map.get(i).getY());
				batch.end();
			} else {
				batch.begin();
				batch.draw(map.get(i).getWall().getTexture(), map.get(i).getX(), map.get(i).getY());
				batch.end();
			}
		}

	}
	
	@Override
	public void dispose () {
		batch.dispose();
		for(int i = 0; i < map.size(); i++){
			if(map.get(i).getGround() != null) {
				map.get(i).getGround().getTexture().dispose();
			} else {
				map.get(i).getWall().getTexture().dispose();
			}
		}
	}
}
