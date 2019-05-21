package com.esteven.game.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Animation {
    private Array<TextureRegion> frames;
    private float maxFrameLifeTime, currentFrameLifeTime;
    private int frameCount, frame;

    public Animation(TextureRegion region, int frameCount, float cycleTime){
        frames = new Array<TextureRegion>();
        int frameWidth = region.getRegionWidth()/frameCount;
        for (int i = 0; i < frameCount; i++){
            frames.add(new TextureRegion(region, i*frameWidth, 0, frameWidth, region.getRegionHeight()));
        }
        this.frameCount = frameCount;
        maxFrameLifeTime = cycleTime / frameCount;
        frame = 0;
    }

    public void Update(float deltaTime){
        currentFrameLifeTime += deltaTime;
        if (currentFrameLifeTime > maxFrameLifeTime){
            frame++;
            currentFrameLifeTime = 0;
        }

        if (frame >= frameCount){
            frame = 0;
        }
    }

    public TextureRegion getFrame(){
        return frames.get(frame);
    }
}
