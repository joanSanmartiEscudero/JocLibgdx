package com.esteven.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.esteven.game.PajaroFlapeador;
import com.esteven.game.sprites.Ocell;
import com.esteven.game.sprites.Tube;

public class PlayState extends State {
    private static final int TUBE_SPACING = 125, TUBE_COUNT = 4;
    private static final int GROUND_Y_OFFSET = -45;

    private Ocell ocell;
    private Texture background, ground;
    private Vector2 posGround1, posGround2;

    private Array<Tube> tubes;

    public PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);
        ocell = new Ocell(50, 200);
        camera.setToOrtho(false, PajaroFlapeador.WIDTH/2, PajaroFlapeador.HEIGHT/2);
        background = new Texture("bg.png");
        ground = new Texture("ground.png");
        posGround1 = new Vector2(camera.position.x - camera.viewportWidth/2, GROUND_Y_OFFSET);
        posGround2 = new Vector2((camera.position.x - camera.viewportWidth/2) + ground.getWidth(), GROUND_Y_OFFSET);
        tubes = new Array<Tube>();

        for (int i = 1; i <= TUBE_COUNT; i++){
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
    }

    @Override
    protected void HandleInput() {
        if (Gdx.input.justTouched()){
            ocell.Jump();
        }
    }

    @Override
    protected void Update(float deltaTime) {
        HandleInput();
        UpdateGround();
        ocell.Update(deltaTime);
        camera.position.x = ocell.getPosition().x + 80;

        for (Tube tube : tubes){
            if (camera.position.x - (camera.viewportWidth/2)
                    > tube.getPosTopTube().x + tube.getTopTubeTexture().getWidth()){
                tube.Reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT ));
            }

            if (tube.Collides(ocell.getBoundsOcell())){
                gameStateManager.Set(new PlayState(gameStateManager));
                break;
            }

        }

        if (ocell.getPosition().y <= ground.getHeight() + GROUND_Y_OFFSET){
            gameStateManager.Set(new PlayState(gameStateManager));
        }

        camera.update();

    }

    @Override
    public void Render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        spriteBatch.draw(background, camera.position.x - (camera.viewportWidth/2), 0);
        spriteBatch.draw(ocell.getTextureRegion(), ocell.getPosition().x, ocell.getPosition().y);

        for (Tube tube : tubes){
            spriteBatch.draw(tube.getTopTubeTexture(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            spriteBatch.draw(tube.getBottomTubeTexture(), tube.getPosBottomTube().x, tube.getPosBottomTube().y);
        }

        spriteBatch.draw(ground, posGround1.x, posGround1.y);
        spriteBatch.draw(ground, posGround2.x, posGround2.y);

        spriteBatch.end();
    }

    @Override
    public void Dispose() {
        background.dispose();
        ocell.Dispose();

        for (Tube tube : tubes){
            tube.Dispose();
        }
    }

    private void UpdateGround(){
        if (camera.position.x - (camera.viewportWidth/2) > posGround1.x + ground.getWidth()){
            posGround1.add(ground.getWidth()*2, 0);
        }

        if (camera.position.x - (camera.viewportWidth/2) > posGround2.x + ground.getWidth()){
            posGround2.add(ground.getWidth()*2, 0);
        }
    }
}
