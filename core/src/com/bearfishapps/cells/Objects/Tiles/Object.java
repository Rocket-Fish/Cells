package com.bearfishapps.cells.Objects.Tiles;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.bearfishapps.cells.Constants.Constants;

public abstract class Object {
    protected int x, y;

    public Object(int x, int y) {
        this.x = x * Constants.sprites.unit;
        this.y = y * Constants.sprites.unit;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Vector2[] getMoveablePositions() {
        return null;
    }

    public abstract void draw(ShapeRenderer shapeRenderer);


}
