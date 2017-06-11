package com.jsatch.demogame.sprites;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

public class Tube {
    private static final int TUBE_HEIGHT = 160;
    private static final int LOWEST_OPENING = 30;
    private static final int MIN_TUBE_GAP = 80;

    private Texture texBottomTube, texTopTube;
    private Vector3 mPosTopTube, mPosBottomTube;
    private Rectangle mRecTopTube, mRectBottomTube;
    private Random rand;

    public Tube(int posx){
        texTopTube = new Texture("top_tube.png");
        texBottomTube = new Texture("bottom_tube.png");

        rand = new Random();

        mPosBottomTube = new Vector3(posx, 0 - rand.nextInt(TUBE_HEIGHT - LOWEST_OPENING) ,0);
        mPosTopTube = new Vector3(posx, TUBE_HEIGHT + MIN_TUBE_GAP + rand.nextInt(TUBE_HEIGHT - LOWEST_OPENING),0);

        mRectBottomTube = new Rectangle(
                mPosBottomTube.x, mPosBottomTube.y,
                texBottomTube.getWidth(), texBottomTube.getHeight());
        mRecTopTube = new Rectangle(
                mPosTopTube.x, mPosTopTube.y,
                texTopTube.getWidth(), texTopTube.getHeight()
        );
    }
    public void reposition(float posx){
        mPosBottomTube.set(posx, 0 - rand.nextInt(TUBE_HEIGHT - LOWEST_OPENING) ,0);
        mPosTopTube.set(posx, TUBE_HEIGHT + MIN_TUBE_GAP + rand.nextInt(TUBE_HEIGHT - LOWEST_OPENING),0);

        mRectBottomTube.setPosition(mPosBottomTube.x, mPosBottomTube.y);
        mRecTopTube.setPosition(mPosTopTube.x, mPosTopTube.y);
    }

    public boolean collision(Rectangle player){
        return player.overlaps(mRectBottomTube) || player.overlaps(mRecTopTube);
    }

    public void dispose(){
        texBottomTube.dispose();
        texTopTube.dispose();
    }

    public Texture getTexBottomTube() {
        return texBottomTube;
    }

    public Texture getTexTopTube() {
        return texTopTube;
    }

    public Vector3 getPosTopTube() {
        return mPosTopTube;
    }

    public Vector3 getPosBottomTube() {
        return mPosBottomTube;
    }
}
