package com.grupf.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Tube {
    private static final int FLUCTUATION = 130, TUBE_GAP = 100, LOWEST_OPENING = 120;
    public static final int TUBE_WIDTH = 52;
    private Rectangle boundsTop, boundsBottom;
    private Texture topTubeTexture, bottomTubeTexture;
    private Vector2 posTopTube, posBottomTube;
    private Random random;

    public Tube(float x){
        topTubeTexture = new Texture("toptube.png");
        bottomTubeTexture = new Texture("bottomtube.png");
        random = new Random();
        posTopTube = new Vector2(x, random.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBottomTube = new Vector2(x, posTopTube.y - TUBE_GAP - bottomTubeTexture.getHeight());

        boundsTop = new Rectangle(posTopTube.x, posTopTube.y, topTubeTexture.getWidth(), topTubeTexture.getHeight());
        boundsBottom = new Rectangle(posBottomTube.x, posBottomTube.y, bottomTubeTexture.getWidth(), bottomTubeTexture.getHeight());

    }

    public Texture getTopTubeTexture() {
        return topTubeTexture;
    }

    public Texture getBottomTubeTexture() {
        return bottomTubeTexture;
    }

    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    public Vector2 getPosBottomTube() {
        return posBottomTube;
    }

    public void Reposition(float x){
        posTopTube.set(x, random.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBottomTube.set(x, posTopTube.y - TUBE_GAP - bottomTubeTexture.getHeight());

        boundsTop.setPosition(posTopTube.x, posTopTube.y);
        boundsBottom.setPosition(posBottomTube.x, posBottomTube.y);
    }

    public Boolean Collides(Rectangle playerRectangle){
        return playerRectangle.overlaps(boundsTop) || playerRectangle.overlaps(boundsBottom);
    }

    public void Dispose(){
        topTubeTexture.dispose();
        bottomTubeTexture.dispose();
    }
}
