package com.grupf.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.grupf.game.OcellFlapejador;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = OcellFlapejador.WIDTH;
		config.height = OcellFlapejador.HEIGHT;
		config.title = OcellFlapejador.TITLE;
		new LwjglApplication(new OcellFlapejador(), config);
	}
}
