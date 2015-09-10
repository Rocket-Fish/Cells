package com.bearfishapps.cells;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bearfishapps.cells.Screens.SplashScreen;

public class Cells extends Game {
	public ActionResolver actionResolver;

	public Cells(ActionResolver resolver) {
		actionResolver = resolver;
	}

	@Override
	public void create () {
		this.setScreen(new SplashScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
}
