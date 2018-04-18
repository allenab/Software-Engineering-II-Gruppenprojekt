package at.uni.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import at.uni.Application;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = Application.TITLE;
		config.width = Application.VIEWPORT_WIDTH;
		config.height = Application.VIEWPORT_HEIGHT;
		config.foregroundFPS = Application.FPS;

		new LwjglApplication(new Application(), config);
	}
}
