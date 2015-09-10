package com.bearfishapps.cells.Objects.Tiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.bearfishapps.cells.Constants.Constants;

public class Blank extends Object{
    private Color color = Color.WHITE;
    public Blank(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(color);
        shapeRenderer.rect(x + Constants.sprites.border, y + Constants.sprites.border, Constants.sprites.size, Constants.sprites.size);
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
