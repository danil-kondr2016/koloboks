package ru.danila.koloboks.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.danila.koloboks.KoloboksGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = KoloboksGame.SCR_WIDTH;
		config.height = KoloboksGame.SCR_HEIGHT;
		new LwjglApplication(new KoloboksGame(), config);
	}
}
