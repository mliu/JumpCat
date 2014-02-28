package com.frinkly.jumpcat;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "jumpcat";
		cfg.useGL20 = true;
		cfg.width = 480;
		cfg.height = 800;

		new LwjglApplication(new JumpCatGame(), cfg);
	}
}
