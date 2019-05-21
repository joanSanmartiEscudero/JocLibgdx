package com.esteven.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.esteven.game.states.GameStateManager;
import com.esteven.game.states.MenuState;

public class PajaroFlapeador extends ApplicationAdapter {
	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;
	public static final String TITLE = "Ocell Flapejador";

	private GameStateManager gameStateManager;
	private SpriteBatch batch;

	private Music mainTheme;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		gameStateManager = new GameStateManager();
		mainTheme = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		mainTheme.setLooping(true);
		mainTheme.setVolume(0.2f);
		mainTheme.play();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		gameStateManager.Push(new MenuState(gameStateManager));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gameStateManager.Update(Gdx.graphics.getDeltaTime());
		gameStateManager.Render(batch);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		mainTheme.dispose();
	}
}
