package com.frinkly.jumpcat;

import com.badlogic.gdx.Application.ApplicationType;

import com.badlogic.gdx.Input.Keys;

import com.badlogic.gdx.InputProcessor;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.GL10;

import com.badlogic.gdx.Screen;

public class GameScreen implements Screen, InputProcessor{
    private World world;
    private WorldRenderer renderer;
    private CatController catController;
    private BlockController blockController;

    private int screenWidth;
    private int screenHeight;

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        blockController.update(delta);
        catController.update(delta);
        renderer.render();
    }

    @Override
    public void resize(int width, int height) {
        screenWidth = width;
        screenHeight = height;
        renderer.setSize(width, height);
    }

    @Override
    public void show() {
        world = new World();
        renderer = new WorldRenderer(world);
        catController = new CatController(world);
        blockController = new BlockController(world);
        blockController.start();
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void pause() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void resume() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void dispose() {
        Assets.dispose();
        renderer.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Keys.SPACE) {
            catController.jumpPressed();
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(!Gdx.app.getType().equals(ApplicationType.Android)) {
            return false;
        }
        catController.jumpPressed();
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(!Gdx.app.getType().equals(ApplicationType.Android)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        // TODO Auto-generated method stub
        return false;
    }

}
