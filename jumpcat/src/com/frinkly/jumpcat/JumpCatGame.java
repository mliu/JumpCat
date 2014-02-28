package com.frinkly.jumpcat;

import com.badlogic.gdx.Game;


public class JumpCatGame extends Game{

	@Override
	public void create() {
	    setScreen(new MainMenuScreen(this));
	}
}
