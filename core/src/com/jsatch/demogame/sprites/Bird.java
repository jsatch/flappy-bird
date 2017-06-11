package com.jsatch.demogame.sprites;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Bird {
    private static final int GRAVITY = -15;
    private static final int MOVEMENT = 50;

    private Vector3 mPosicion;
    private Vector3 mVelocidad;
    private Rectangle mBounds;

    private Texture mBird;

    public Bird(int x, int y) {
        mPosicion = new Vector3(x, y, 0);
        mVelocidad = new Vector3(0, 0, 0);
        mBird = new Texture("bird_rojo_1.png");
        mBounds = new Rectangle(mPosicion.x, mPosicion.y, mBird.getWidth(), mBird.getHeight());
    }

    public void update(float dt){
        if (mPosicion.y > 0) {
            mVelocidad.add(0, GRAVITY, 0);
        }
        mVelocidad.scl(dt);
        //mPosicion.add(mVelocidad);
        mPosicion.add(MOVEMENT * dt, mVelocidad.y, 0);
        mBounds.setPosition(mPosicion.x, mPosicion.y);
        if (mPosicion.y < 0){
            mPosicion.y = 0;
        }
        mVelocidad.scl(1/dt);
    }

    public void jump(){
        mVelocidad.y = 250;
    }

    public Vector3 getPosicion() {
        return mPosicion;
    }

    public Texture getTexture() {
        return mBird;
    }

    public Rectangle getBounds() {
        return mBounds;
    }

    public void dispose(){
        mBird.dispose();
    }
}
