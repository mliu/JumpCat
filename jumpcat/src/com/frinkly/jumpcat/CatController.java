package com.frinkly.jumpcat;

import com.badlogic.gdx.utils.Pool;

import com.badlogic.gdx.math.Rectangle;

import com.badlogic.gdx.math.Intersector;

import com.frinkly.jumpcat.Cat.State;

import com.badlogic.gdx.Input.Keys;

public class CatController {
    private World world;
    private Cat cat;
    private boolean jumpPressed = false;

    private boolean debugCollision = false;

    private static final float HEIGHT = 20f;
    private static final float WIDTH = 12f;

    private static final float ACCELERATION = -40f;
    private static final float MAX_JUMP_SPEED = 12f;

    private Pool<Rectangle> rectPool = new Pool<Rectangle>() {
        @Override
        protected Rectangle newObject() {
            return new Rectangle();
        }
    };

    public void jumpPressed() {
        jumpPressed = true;
    }

    public CatController(World newWorld) {
        world = newWorld;
        this.cat = world.getCat();
    }

    public void update(float delta) {
        processInput();

        cat.getAcceleration().y = ACCELERATION;
        cat.getAcceleration().scl(delta);
        cat.getVelocity().add(0, cat.getAcceleration().y);
        cat.update(delta);
        if (checkCollisions()) {
            cat.setState(State.DEAD);
        }

        if (cat.getPosition().y + cat.getBounds().height > HEIGHT) {
            cat.getPosition().y = HEIGHT - cat.getBounds().height;
        }

        if (cat.getVelocity().y > 0 && !cat.getState().equals(State.DEAD)) {
            cat.setState(State.JUMPING);
        }

        else if (cat.getVelocity().y < 0 && !cat.getState().equals(State.DEAD)) {
            cat.setState(State.FALLING);
        }

        if (cat.getPosition().y < world.FLOOR) {
            if (!debugCollision)
                cat.setState(State.DEAD);
            cat.getPosition().y = world.FLOOR;
        }
    }

    public void processInput() {
        if (jumpPressed && !cat.getState().equals(State.DEAD)) {
            jumpPressed = false;
            cat.setState(State.JUMPING);
            cat.getVelocity().y = MAX_JUMP_SPEED;
        }
    }

    public boolean checkCollisions() {
        if (!debugCollision) {
            for (Block block : world.getBlocks()) {
                Rectangle blockRect = rectPool.obtain();
                Rectangle catRect = rectPool.obtain();
                blockRect.set(block.getPosition().x, block.getPosition().y,
                        block.getBounds1().width, block.getBounds1().height);
                catRect.set(cat.getPosition().x, cat.getPosition().y,
                        cat.getBounds().width, cat.getBounds().height);
                if (catRect.overlaps(blockRect)) {
                    return true;
                }
                blockRect.set(block.getPosition().x, block.getPosition().y
                        + block.getBounds1().height + block.getSPACE(),
                        block.getBounds2().width, block.getBounds2().height);
                if (catRect.overlaps(blockRect)) {
                    return true;
                }
            }
        }
        return false;
    }
}
