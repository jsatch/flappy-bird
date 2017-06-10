package com.jsatch.demogame.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.jsatch.demogame.DemoGdxGame;
import com.jsatch.demogame.sprites.Bird;
import com.jsatch.demogame.sprites.Tube;


public class PlayState extends State {
    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;

    private Bird mBird;
    private Texture mBackground;
    private Tube mTube;
//    private Array<Tube> mTubes;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        mBird = new Bird(50, 400);
        mCam.setToOrtho(false, DemoGdxGame.WIDTH / 2, DemoGdxGame.HEIGHT / 2);

        mBackground = new Texture("bg.png");


        mTube = new Tube(100, (int)mCam.viewportHeight);
//        mTubes = new Array<Tube>();
//        for (int i=1; i<= TUBE_COUNT; i++){
//            mTubes.add(new Tube(i * (TUBE_SPACING + mTubes.peek().getTexBottomTube().getWidth()),
//                    (int)mCam.viewportHeight));
//        }


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
        mBird.update(dt);
    }

    @Override
    public void render(SpriteBatch sp) {
        sp.setProjectionMatrix(mCam.combined);
        sp.begin();
        sp.draw(mBackground,
                mCam.position.x - (mCam.viewportWidth / 2), 0,
                DemoGdxGame.WIDTH / 2, DemoGdxGame.HEIGHT / 2);
        sp.draw(mBird.getTexture(), mBird.getPosicion().x, mBird.getPosicion().y);
        sp.draw(mTube.getTexTopTube(), mTube.getPosTopTube().x, mTube.getPosTopTube().y);
        sp.draw(mTube.getTexBottomTube(), mTube.getPosBottomTube().x, mTube.getPosBottomTube().y);
        sp.end();
    }

    @Override
    public void dispose() {

    }
}
