package com.grupf.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateManager {
    private Stack<State> states;

    public GameStateManager(){
        states = new Stack<State>();
    }

    public void Push(State state){
        states.push(state);
    }

    public void Pop(){
        states.pop().Dispose();
    }

    public void Set(State state){
        states.pop().Dispose();
        states.push(state);
    }

    public void Update(float deltaTime){
        states.peek().Update(deltaTime);
    }

    public void Render(SpriteBatch spriteBatch){
        states.peek().Render(spriteBatch);
    }
}
