package com.jsatch.demogame.sprites;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

public class Tube {
    private static final int FLUCTUATION = 130;
    private static final int TUBE_GAP =
            80;
    private static final int LOWEST_OPENING = 80;

    private Texture texBottomTube, texTopTube;
    private Vector3 mPosTopTube, mPosBottomTube;
    private Random rand;
    private int mViewportHeight;

    public Tube(int posx, int height){
        texTopTube = new Texture("top_tube.png");
        texBottomTube = new Texture("bottom_tube.png");
        mViewportHeight = height;

        rand = new Random();

        mPosTopTube = new Vector3(posx, mViewportHeight - rand.nextInt(mViewportHeight) , 0);
        mPosBottomTube = new Vector3(posx, mPosTopTube.y - TUBE_GAP - LOWEST_OPENING -texBottomTube.getHeight(), 0);
//        System.out.println(String.format("heigh : %s  aleatorio : %s", height, aleatorio));
//        System.out.println(String.format("mPosY : %s  textBottomTube_Height : %s", mPosTopTube.y, texBottomTube.getHeight()));
    }
    public void reposition(int posx){
        mPosTopTube.set(posx, mViewportHeight - rand.nextInt(mViewportHeight) , 0);
        mPosBottomTube.set(posx, mPosTopTube.y - TUBE_GAP - LOWEST_OPENING -texBottomTube.getHeight(), 0);
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
