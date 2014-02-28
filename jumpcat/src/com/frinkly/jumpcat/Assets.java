package com.frinkly.jumpcat;

import com.badlogic.gdx.graphics.Color;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Assets {
    public static TextureAtlas buttonAtlas;
    public static Skin skin;

    public static Sprite backgroundSprite;
    public static Sprite flashSprite;

    public static BitmapFont font;

    public static void loadAssets() {
        skin = new Skin();
        buttonAtlas = new TextureAtlas(Gdx.files.internal("textures/menu.pack"));
        skin.addRegions(Assets.buttonAtlas);
        font = new BitmapFont(Gdx.files.internal("font/font.fnt"), false);
        font.setColor(Color.BLACK);
        backgroundSprite = new Sprite(buttonAtlas.createSprite("bg/bg"));
        flashSprite = new Sprite(buttonAtlas.createSprite("bg/flash"));
    }

    public static void dispose() {
        buttonAtlas.dispose();
        skin.dispose();
        font.dispose();
    }
}
