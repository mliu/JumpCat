package com.frinkly.jumpcat;

import com.badlogic.gdx.math.Vector2;

import com.badlogic.gdx.math.Rectangle;

public class Cat {

    public enum State {
        WALKING, JUMPING, FALLING, DEAD
    }

    private float stateTime = 0;

    private int points = 0;

    private static final float SIZE = 1f;

    private Vector2 position = new Vector2();
    private Vector2 velocity = new Vector2();
    private Vector2 acceleration = new Vector2();
    private Rectangle bounds = new Rectangle();
    private State state = State.WALKING;

    public Cat(Vector2 initPosition) {
        this.position = initPosition;
        bounds.width = SIZE;
        bounds.height = SIZE;
    }

    public int getPoints() {
        return points;
    }

    public void addPoint() {
        points++;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 newPosition) {
        position = newPosition;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public Vector2 getAcceleration() {
        return acceleration;
    }

    public State getState() {
        return state;
    }

    public void setState(State newState) {
        state = newState;
    }

    public float getStateTime() {
        return stateTime;
    }

    public void update(float delta) {
        stateTime += delta;
        position.add(velocity.cpy().scl(delta));
    }
}
