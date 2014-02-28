package com.frinkly.jumpcat;

import com.frinkly.jumpcat.Cat.State;

import java.util.ArrayList;

public class BlockController {
    private World world;
    private Cat cat;
    private ArrayList<Block> blocks;

    private static int MAXCAP;

    public BlockController(World newWorld) {
        world = newWorld;
        cat = world.getCat();
        blocks = world.getBlocks();
        MAXCAP = world.getMAXCAP();
    }

    public void update(float delta) {
        int count = blocks.size();
        for (int x = 0; x < count; x++) {
            if (!cat.getState().equals(State.DEAD)) {
                blocks.get(x).update(delta);
            }
            if (blocks.get(x).getPosition().x < cat.getPosition().x
                    && !blocks.get(x).isPassed()) {
                cat.addPoint();
                blocks.get(x).passed();
            }
        }
        if (blocks.get(0).getPosition().x + blocks.get(0).getBounds1().width < 0f) {
            blocks.remove(0);
        }
        if (blocks.get(blocks.size() - 1).getPosition().x < 7f) {
            blocks.add(new Block());
        }
    }

    public void start() {
        blocks.add(new Block());
    }
}
