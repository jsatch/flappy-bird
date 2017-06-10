package com.jsatch.demogame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.jsatch.demogame.DemoGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = DemoGdxGame.WIDTH;
		config.height = DemoGdxGame.HEIGHT;
		config.title = DemoGdxGame.TITLE;
		new LwjglApplication(new DemoGdxGame(), config);
	}
}
