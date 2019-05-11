package com.grupf.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.grupf.game.OcellFlapejador;

public class MenuState extends State {
    private Texture background, playButton;
    public MenuState(GameStateManager gameStateManager) {
        super(gameStateManager);
        camera.setToOrtho(false, OcellFlapejador.WIDTH/2, OcellFlapejador.HEIGHT/2);
        background = new Texture("bg.png");
        playButton = new Texture("playbtn.png");
    }

    @Override
    public void HandleInput() {
        if (Gdx.input.justTouched()){
            gameStateManager.Set(new PlayState(gameStateManager));
        }
    }

    @Override
    protected void Update(float deltaTime) {
        HandleInput();
    }

    @Override
    public void Render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0);
        spriteBatch.draw(playButton, camera.position.x - playButton.getWidth()/2, camera.position.y);
        spriteBatch.end();
    }

    @Override
    public void Dispose() {
        background.dispose();
        playButton.dispose();
    }
}
