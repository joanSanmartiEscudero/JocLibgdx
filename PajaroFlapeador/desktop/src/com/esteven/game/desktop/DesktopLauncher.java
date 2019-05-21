package com.esteven.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.esteven.game.PajaroFlapeador;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = PajaroFlapeador.WIDTH;
		config.height = PajaroFlapeador.HEIGHT;
		config.title = PajaroFlapeador.TITLE;
		new LwjglApplication(new PajaroFlapeador(), config);
	}
}
