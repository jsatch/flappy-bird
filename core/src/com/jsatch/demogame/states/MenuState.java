package com.jsatch.demogame.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jsatch.demogame.DemoGdxGame;

public class MenuState extends State{
    private Texture texBackground;
    private Texture texPlayButton;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        mCam.setToOrtho(false, DemoGdxGame.WIDTH / 2, DemoGdxGame.HEIGHT / 2);
        texBackground = new Texture("bg.png");
        texPlayButton = new Texture("button_play.png");
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()){
            mGSM.set(new PlayState(mGSM));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sp) {
        sp.setProjectionMatrix(mCam.combined);
        sp.begin();
        sp.draw(texBackground, 0, 0, mCam.viewportWidth, mCam.viewportHeight);
        sp.draw(texPlayButton,
                mCam.position.x - (texPlayButton.getWidth()/2) ,
                mCam.position.y
        );
        sp.end();
    }

    @Override
    public void dispose() {
        texBackground.dispose();
        texPlayButton.dispose();
        System.out.println("MenuState dispose");
    }
}
