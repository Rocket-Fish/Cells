package com.bearfishapps.cells.Objects.Tiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.bearfishapps.cells.Constants.Constants;

public class DeadPiece extends PlayPiece {
    public DeadPiece(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.ORANGE);
        shapeRenderer.rect(x + Constants.sprites.border, y + Constants.sprites.border, Constants.sprites.size, Constants.sprites.size);
    }

    public Vector2[] getMoveablePositions() {
        return null;
    }
}
