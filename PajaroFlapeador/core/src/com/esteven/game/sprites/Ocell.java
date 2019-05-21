package com.esteven.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Ocell {
    private static final int GRAVITY = -15, MOVEMENT = 100;

    private Vector3 position, velocity;
    private Texture textureAnimation;
    private Rectangle boundsOcell;
    private Animation ocellAnimation;
    private Sound flapSound;

    public Ocell(int x, int y){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0,0,0);
        textureAnimation = new Texture("birdanimation.png");
        ocellAnimation = new Animation(new TextureRegion(textureAnimation), 3, 0.5f);
        boundsOcell = new Rectangle(x, y, textureAnimation.getWidth()/3, textureAnimation.getHeight());
        flapSound = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));
    }

    public void Update(float deltaTime){
        ocellAnimation.Update(deltaTime);

        if (position.y > 0) {
            velocity.add(0, GRAVITY, 0);
        }

        velocity.scl(deltaTime);
        position.add(MOVEMENT * deltaTime, velocity.y, 0);

        if (position.y < 0){
            position.y = 0;
        }

        velocity.scl(1/deltaTime);

        boundsOcell.setPosition(position.x, position.y);
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getTextureRegion() {
        return ocellAnimation.getFrame();
    }

    public void Jump(){
        velocity.y = 250;
        flapSound.play(0.5f);
    }

    public Rectangle getBoundsOcell(){
        return boundsOcell;
    }

    public void Dispose(){
        textureAnimation.dispose();
        flapSound.dispose();
    }
}
