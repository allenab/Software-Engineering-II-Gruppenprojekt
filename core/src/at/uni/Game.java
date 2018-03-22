package at.uni;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;

public class Game extends ApplicationAdapter {

	public static final int HEIGHT = 800;
	public static final int WIDTH = 480;
	public static final String TITLE = "Kitten Wars";

	public static Player player;
	public static Block[][] map;
	
	@Override
	public void create () {
		//lädt alle Assets (Grafiken, Spieler, etc.)
		Assets.load();

		//erzeugt Spieler mit Startposition x: 16, y: 16
		player = new Player(16, 16);

		//erzeugt die Karte
		map = new Block[15][15];
		for (int i = 0; i < map.length; i++){
			for (int j = 0; j < map[i].length; j++){
				if ((i == 0 || i == 14) || (j == 0 || j == 14) || (i % 2 == 0 && j % 2 == 0)){
					map[i][j] = new Block(Assets.wall, i*16, j*16);
				} else {
					map[i][j] = new Block(Assets.freeSpace, i*16, j*16);
				}
			}
		}
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		playerMovementUpdate();

		Assets.batch.begin();
		//DRAW MAP
		for (int i = 0; i < map.length; i++){
			for (int j = 0; j < map[i].length; j++){
				Assets.batch.draw(map[i][j].image, map[i][j].bounds.x, map[i][j].bounds.y);
			}
		}

		//PLAYER
		Assets.batch.draw(player.image, player.bounds.x, player.bounds.y);

		Assets.batch.end();
	}

	private void playerMovementUpdate(){
		int pressedKey = 0;
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			player.bounds.x -= 2;
			pressedKey = Input.Keys.LEFT;
		} else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			player.bounds.x += 2;
			pressedKey = Input.Keys.RIGHT;
		} else if (Gdx.input.isKeyPressed(Input.Keys.UP)){
			player.bounds.y += 2;
			pressedKey = Input.Keys.UP;
		} else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
			player.bounds.y -= 2;
			pressedKey = Input.Keys.DOWN;
		}

		//KOLLISIONSBEHANDLUNG - des geht sicher viel leichter und besser xD
			for (int i = 0; i < map.length; i++){
				for (int j = 0; j < map[i].length; j++){
					// nur wenn der Spieler mit einer Wand kollidiert, wird die Position wieder rückgangig gemacht
					if ((player.bounds.intersects(map[i][j].bounds)) && (map[i][j].image.equals(Assets.wall))){
						switch (pressedKey){
							case Input.Keys.LEFT:
								player.bounds.x += 2;
								break;
							case Input.Keys.RIGHT:
								player.bounds.x -= 2;
								break;
							case Input.Keys.UP:
								player.bounds.y -= 2;
								break;
							case Input.Keys.DOWN:
								player.bounds.y += 2;
								break;
							default: break;
						}
					}
				}
			}
	}
	
	@Override
	public void dispose () {
		Assets.batch.dispose();
		Assets.backgrounds.dispose();
	}
}
