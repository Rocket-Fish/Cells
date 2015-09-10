package com.bearfishapps.cells.Objects.Tiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.bearfishapps.cells.Constants.Constants;

public class Block extends Object{
    public Block(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.DARK_GRAY);
        shapeRenderer.rect(x + Constants.sprites.border, y + Constants.sprites.border, Constants.sprites.size, Constants.sprites.size);
    }
}
