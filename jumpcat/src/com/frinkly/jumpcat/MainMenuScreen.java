package com.frinkly.jumpcat;

import com.badlogic.gdx.graphics.OrthographicCamera;

import com.badlogic.gdx.scenes.scene2d.InputEvent;

import com.badlogic.gdx.scenes.scene2d.InputListener;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.graphics.GL10;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.scenes.scene2d.Stage;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import com.badlogic.gdx.Screen;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class MainMenuScreen implements Screen{
    ImageButton playButton;
    Stage stage;
    SpriteBatch batch;
    private JumpCatGame game;

    public MainMenuScreen(JumpCatGame newGame) {
        game = newGame;
    }

    public void createButtons() {
        playButton = new ImageButton(Assets.skin.getDrawable("playbutton"), Assets.skin.getDrawable("playbutton-down"));
        playButton.setPosition(40f, 400f);
        playButton.setTransform(true);
        stage.addActor(playButton);
        Gdx.input.setInputProcessor(stage);

        playButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y,
                    int pointer, int button) {
                game.setScreen(new GameScreen());
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                    int pointer, int button) {
                return true;
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        stage.act();
        batch.begin();
        stage.draw();
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        stage.setViewport(480, 800, true);
        stage.getCamera().translate(-stage.getGutterWidth(), -stage.getGutterHeight(), 0);
    }

    @Override
    public void show() {
        Assets.loadAssets();
        stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
        batch = new SpriteBatch();
        this.createButtons();
    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }


}
