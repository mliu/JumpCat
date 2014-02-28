package com.frinkly.jumpcat;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class World {
    private ArrayList<Block> blocks;
    private Cat cat;

    private static final int MAXCAP = 7;

    public static final float FLOOR = 2f;

    public World() {
        blocks = new ArrayList<Block>(MAXCAP);
        cat = new Cat(new Vector2(2, 10));
    }

    public int getMAXCAP() {
        return MAXCAP;
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public Cat getCat() {
        return cat;
    }
}
