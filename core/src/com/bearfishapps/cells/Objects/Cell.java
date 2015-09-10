package com.bearfishapps.cells.Objects;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.bearfishapps.cells.Objects.Tiles.Object;

public class Cell {
    private com.bearfishapps.cells.Objects.Tiles.Object obj;

    public Cell(int x, int y) {
        obj = new com.bearfishapps.cells.Objects.Tiles.Blank(x, y);
    }

    public Cell(com.bearfishapps.cells.Objects.Tiles.Object type) {
        obj = type;
    }

    public void draw(ShapeRenderer shapeRenderer) {
        obj.draw(shapeRenderer);
    }

    public Object returnObj() {
        return obj;
    }

}
