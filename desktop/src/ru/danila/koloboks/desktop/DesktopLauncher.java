package ru.danila.koloboks.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import ru.danila.koloboks.KoloboksGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setWindowedMode(KoloboksGame.SCR_WIDTH, KoloboksGame.SCR_HEIGHT);
		new Lwjgl3Application(new KoloboksGame(), config);
	}
}
