package com.frinkly.jumpcat;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;

import com.badlogic.gdx.scenes.scene2d.Stage;

import com.frinkly.jumpcat.Cat.State;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class WorldRenderer {
    private static final float CAMERA_WIDTH = 12f;
    private static final float CAMERA_HEIGHT = 20f;

    private long fadeTimeAlpha = 800l;
    private long fadeTime = 0l;

    private Stage stage;
    private OrthographicCamera cam;
    private SpriteBatch spriteBatch;
    private int width;
    private int height;
    private float ppuX;
    private float ppuY;

    private World world;
    private Cat cat;

    private ImageButton playButton;
    private ImageButton leaderButton;

    ShapeRenderer debugRenderer = new ShapeRenderer();

    public WorldRenderer(World newWorld) {
        this.world = newWorld;
        this.cat = world.getCat();
        this.cam = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
        this.cam.position.set(CAMERA_WIDTH / 2f, CAMERA_HEIGHT / 2f, 0);
        spriteBatch = new SpriteBatch();

        this.cam.update();
        spriteBatch.setProjectionMatrix(new Matrix4().setToOrtho2D(0, 0,
                Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),
                true);
    }

    public void setSize(int newWidth, int newHeight) {
        width = newWidth;
        height = newHeight;
        ppuX = (float) width / CAMERA_WIDTH;
        ppuY = (float) height / CAMERA_HEIGHT;
        stage.setViewport(480, 800, true);
        stage.getCamera().translate(-stage.getGutterWidth(),
                -stage.getGutterHeight(), 0);
    }

    public void render() {
        stage.act();
        spriteBatch.begin();
        drawBackground();
        drawPoints();
        if (cat.getState().equals(State.DEAD) && drawFlash()) {
            drawMenu();
        }
        spriteBatch.end();
        stage.draw();
        debugDraw(true);
    }

    public void dispose() {
        spriteBatch.dispose();
    }

    private boolean drawFlash() {
        if (System.currentTimeMillis() - fadeTime > 0l
                && System.currentTimeMillis() - fadeTime < fadeTimeAlpha) {
            spriteBatch
                    .setColor(
                            1.0f,
                            1.0f,
                            1.0f,
                            (float) (fadeTimeAlpha - (System
                                    .currentTimeMillis() - fadeTime))
                                    / fadeTimeAlpha);
            spriteBatch.draw(Assets.flashSprite, 0f, 0f, CAMERA_WIDTH * ppuX,
                    CAMERA_HEIGHT * ppuY);
            return false;
        }
        else if (fadeTime == 0) {
            fadeTime = System.currentTimeMillis();
            return false;
        }
        else {
            return true;
        }
    }

    private void drawBackground() {
        Assets.backgroundSprite.setSize(CAMERA_WIDTH * ppuX, CAMERA_HEIGHT
                * ppuY);
        Assets.backgroundSprite.draw(spriteBatch);
    }

    private void drawMenu() {
        if(!stage.getActors().contains(playButton, false)){
            playButton = new ImageButton(
                    Assets.skin.getDrawable("playbutton"),
                    Assets.skin.getDrawable("playbutton-down"));
            playButton.setPosition(2f * ppuX, 5f * ppuY);
            leaderButton = new ImageButton(
                    Assets.skin.getDrawable("leaderboardbutton"),
                    Assets.skin.getDrawable("leaderboardbutton-down"));
            leaderButton.setPosition(7f * ppuX, 5f * ppuY);
            stage.addActor(playButton);
            stage.addActor(leaderButton);
            Gdx.input.setInputProcessor(stage);
        }
    }

    private void drawPoints() {
        Assets.font.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        Assets.font.draw(
                spriteBatch,
                Integer.toString(cat.getPoints()),
                ((CAMERA_WIDTH / 2) - (Assets.font.getBounds(Integer
                        .toString(cat.getPoints())).width / (2 * this.ppuX)))
                        * ppuX, 17f * ppuY);
    }

    private void debugDraw(boolean draw) {
        if (draw) {
            debugRenderer.setProjectionMatrix(cam.combined);
            debugRenderer.begin(ShapeType.Filled);
            for (Block block : world.getBlocks()) {
                Rectangle rect1 = block.getBounds1();
                Rectangle rect2 = block.getBounds2();
                float x1 = block.getPosition().x;
                float y1 = block.getPosition().y;
                debugRenderer.setColor(new Color(1, 0, 0, 1));
                debugRenderer.rect(x1, y1, rect1.width, rect1.height);
                debugRenderer.rect(x1, y1 + rect1.height + block.getSPACE(),
                        rect2.width, rect2.height);
            }
            Rectangle rect = cat.getBounds();
            float x1 = cat.getPosition().x + rect.x;
            float y1 = cat.getPosition().y + rect.y;
            debugRenderer.setColor(new Color(0, 1, 0, 1));
            debugRenderer.rect(x1, y1, rect.width, rect.height);
            debugRenderer.end();
        }
    }

    private void drawBlocks() {

    }

    private void drawCat() {

    }
}
