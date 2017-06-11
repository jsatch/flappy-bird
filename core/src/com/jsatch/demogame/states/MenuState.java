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
        sp.begin();
        sp.draw(texBackground, 0, 0, DemoGdxGame.WIDTH, DemoGdxGame.HEIGHT);
        sp.draw(texPlayButton,
                (DemoGdxGame.WIDTH / 2) - (texPlayButton.getWidth() / 2),
                (DemoGdxGame.HEIGHT / 2) + (texPlayButton.getHeight() / 2)
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
