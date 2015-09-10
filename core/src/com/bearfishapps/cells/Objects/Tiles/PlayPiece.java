package com.bearfishapps.cells.Objects.Tiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.bearfishapps.cells.Constants.Constants;

public class PlayPiece extends Object{
    public PlayPiece(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.rect(x + Constants.sprites.border, y + Constants.sprites.border, Constants.sprites.size, Constants.sprites.size);

    }

    public Vector2[] getMoveablePositions() {
        int x = this.x/Constants.sprites.unit;
        int y = this.y/Constants.sprites.unit;

        Vector2[] positions = new Vector2[8];
        positions[0] = new Vector2(x-1, y-1);
        positions[1] = new Vector2(x-1, y);
        positions[2] = new Vector2(x-1, y+1);
        positions[3] = new Vector2(x, y-1);
        positions[4] = new Vector2(x, y+1);
        positions[5] = new Vector2(x+1, y-1);
        positions[6] = new Vector2(x+1, y);
        positions[7] = new Vector2(x+1, y+1);

        return positions;
    }

}
