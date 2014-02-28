package com.frinkly.jumpcat;

import com.badlogic.gdx.math.Vector2;

import com.badlogic.gdx.math.Rectangle;

public class Block {
    private static final float SPEED = -5f;
    private static final float STARTPOS = 14f;
    private static final float FLOOR = 2f;
    private static final float WIDTH = 2f;
    private static final float SPACE = 4f;

    private boolean isPassed = false;
    private float height;
    private Vector2 position;
    private Vector2 velocity;
    private Rectangle bounds1;
    private Rectangle bounds2;

    private float stateTime = 0;

    public Block() {
        this.position = new Vector2(STARTPOS, FLOOR);
        velocity = new Vector2(SPEED, 0);
        height = (float) Math.ceil(Math.random() * 10 + 2);
        this.bounds1 = new Rectangle(this.position.x, this.position.y, WIDTH, height);
        this.bounds2 = new Rectangle(this.position.x, this.position.y + height + SPACE, WIDTH, 20f);
    }

    public boolean isPassed() {
        return isPassed;
    }

    public void passed() {
        isPassed = true;
    }

    public float getSPACE() {
        return SPACE;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public Rectangle getBounds1() {
        return bounds1;
    }

    public Rectangle getBounds2() {
        return bounds2;
    }

    public void update(float delta) {
        stateTime += delta;
        position.add(velocity.cpy().scl(delta));
    }
}
