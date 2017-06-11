package com.jsatch.demogame.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.jsatch.demogame.DemoGdxGame;
import com.jsatch.demogame.sprites.Bird;
import com.jsatch.demogame.sprites.Tube;


public class PlayState extends State {
    private static final int TUBE_SPACING = 50;
    private static final int TUBE_COUNT = 4;
    public static final int TUBE_WIDTH = 26;
    private static final int GROUND_OFFSET = -40;

    private Bird mBird;
    private Texture mBackground;
    private Texture mGround;
    private Array<Tube> mTubes;

    private Vector3 mPosGround1, mPosGround2;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        //mCam.setToOrtho(false, DemoGdxGame.WIDTH / 2, DemoGdxGame.HEIGHT / 2);

        mBird = new Bird((int)(mCam.position.x - (mCam.viewportWidth / 2)), 400);

        mBackground = new Texture("bg.png");
        mGround = new Texture("ground.png");

        mPosGround1 = new Vector3(mCam.position.x - (mCam.viewportWidth / 2), GROUND_OFFSET ,0);
        mPosGround2 = new Vector3(mCam.position.x - (mCam.viewportWidth / 2) + mGround.getWidth(),
                GROUND_OFFSET ,0);
        System.out.println(mPosGround1.x);
        System.out.println(mPosGround2.x);

        mTubes = new Array<Tube>();
        for (int i=1; i<= TUBE_COUNT; i++){
            mTubes.add(new Tube(i * (TUBE_SPACING + TUBE_WIDTH)));
        }


    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()){
            mBird.jump();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        updateGround();
        mBird.update(dt);

        mCam.position.x = mBird.getPosicion().x + 40;

        for (int i=0; i<mTubes.size; i++){
            Tube tube = mTubes.get(i);
            if (mCam.position.x - (mCam.viewportWidth / 2) > tube.getPosTopTube().x + TUBE_WIDTH){
                tube.reposition(tube.getPosBottomTube().x + (TUBE_COUNT * (TUBE_SPACING + TUBE_WIDTH)));
            }
            if (tube.collision(mBird.getBounds())){
                mGSM.set(new PlayState(mGSM));
            }
        }

        // Verificamos colision del pajarito
        if (mBird.getPosicion().y < mGround.getHeight() + GROUND_OFFSET){
            mGSM.set(new PlayState(mGSM));
        }

        mCam.update();
    }

    @Override
    public void render(SpriteBatch sp) {
        sp.setProjectionMatrix(mCam.combined);
        sp.begin();
        sp.draw(mBackground,
                mCam.position.x - (mCam.viewportWidth / 2), 0,
                DemoGdxGame.WIDTH / 2, DemoGdxGame.HEIGHT / 2);
        sp.draw(mBird.getTexture(), mBird.getPosicion().x, mBird.getPosicion().y);
        for (Tube tube : mTubes){
            sp.draw(tube.getTexTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            sp.draw(tube.getTexBottomTube(), tube.getPosBottomTube().x, tube.getPosBottomTube().y);
        }
        sp.draw(mGround, mPosGround1.x, mPosGround1.y);
        sp.draw(mGround, mPosGround2.x, mPosGround2.y);
        sp.end();
    }

    @Override
    public void dispose() {
        mBackground.dispose();
        mBird.dispose();
        for (Tube tube : mTubes){
            tube.dispose();
        }
        mGround.dispose();
        System.out.println("PlayState dispose");
    }

    private void updateGround(){
        if (mCam.position.x - (mCam.viewportWidth / 2) > mPosGround1.x + mGround.getWidth()){
            mPosGround1.add(mGround.getWidth() * 2 , 0, 0);
        }
        if (mCam.position.x - (mCam.viewportWidth / 2) > mPosGround2.x + mGround.getWidth()){
            mPosGround2.add(mGround.getWidth() * 2, 0, 0);
        }
    }
}
