package com.jsatch.demogame.states;


import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class State {
    protected OrthographicCamera mCam;
    protected Vector3 mMouse;
    protected GameStateManager mGSM;

    protected State(GameStateManager gsm){
        mGSM = gsm;
        mCam = new OrthographicCamera();
        mMouse = new Vector3();
    }

    public abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sp);
    public abstract void dispose();
}
