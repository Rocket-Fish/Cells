package com.bearfishapps.cells.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.bearfishapps.cells.Constants.Constants;
import com.bearfishapps.cells.Objects.Tiles.*;
import com.bearfishapps.cells.Objects.Tiles.Block;
import com.bearfishapps.cells.Objects.Tiles.DeadPiece;
import com.bearfishapps.cells.Objects.Tiles.EnemyPiece;
import com.bearfishapps.cells.Objects.Tiles.Object;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Grid{
    private int size;
    private int turnNumber = 0;
    private boolean gameOn = true;
    private int percentOfCells = 0;

    private Cell[][] cell;
    private HashSet<Vector2> moveablePos = new HashSet<>();
    private HashSet<Vector2> compMoveablePos = new HashSet<>();
    public Grid(int size) {
        this.size = size;

        cell = new Cell[size][size];

        for(int i = 0; i<size; i++) {
            for(int j = 0; j<size; j++) {
                int rand = MathUtils.random(0, 100);
                if(rand < 80)
                    cell[i][j] = new Cell(i, j);
                else if(rand < 90)
                    cell[i][j] = new Cell(new Block(i, j));
                else
                    cell[i][j] = new Cell(new DeadPiece(i, j));
            }
        }
        spawnInitialTiles();
    }

    private void spawnInitialTiles() {
        cell[0][0] = new Cell(new PlayPiece(0, 0));
        cell[size-1][size-1] = new Cell(new EnemyPiece(size-1, size-1));

        while(true) {
            int x = MathUtils.random(0, size-1);
            int y = MathUtils.random(0, size-1);
            if(cell[x][y].returnObj() instanceof Blank) {
                cell[x][y] = new Cell(new PlayPiece(x, y));
                break;
            }
        }

        while(true) {
            int x = MathUtils.random(0, size-1);
            int y = MathUtils.random(0, size-1);
            if(cell[x][y].returnObj() instanceof Blank) {
                cell[x][y] = new Cell(new EnemyPiece(x, y));
                break;
            }
        }
    }

    public void clickTile(Vector3 clickedPos) {
        Iterator it = moveablePos.iterator();
        while (it.hasNext()) {
            Vector2 v = (Vector2)it.next();
            int x, y;
            x = (int)clickedPos.x/Constants.sprites.unit;
            y = (int)clickedPos.y/Constants.sprites.unit;

            if((v.x == x && v.y == y)) {
                addToCell(x, y, new PlayPiece(x, y));
                update();
                updateAI();
                update();
                checkSurroundings();
                break;
            }
        }
    }

    private void addToCell(int x, int y, Object object) {
        cell[x][y] = new Cell(object);
    }

    private void updateAI() {
        Iterator iter = compMoveablePos.iterator();
        Vector2 center = new Vector2(size/2, size/2);
        Vector2 averagePosition = new Vector2();
        int x = 0;int y = 0;int count = 0;
        if(compMoveablePos.size() != 0) {
            while (iter.hasNext()) {
                Vector2 vec = (Vector2) iter.next();
                x += vec.x;
                y += vec.y;
                count++;
            }
        }
        try {
            averagePosition.x = x / count;
            averagePosition.y = y / count;
        }catch (Exception e) {
            Gdx.app.log("ERROR", "Divided by Zero");
            gameOn = false;
            averagePosition = center;
        }

        if(center.dst(averagePosition) < 5 || MathUtils.random(0, 10)>8) {

            int rand = 0;
            if (compMoveablePos.size() > 0)
                rand = MathUtils.random(1, compMoveablePos.size());
            else
                return;
            Iterator it = compMoveablePos.iterator();
            Vector2 v = new Vector2();
            for (int i = 0; i < rand; i++) {
                v = (Vector2) it.next();
            }
            addToCell((int)v.x, (int)v.y, new EnemyPiece((int)v.x, (int)v.y));
            Gdx.app.log("New Enemy Piece", v.toString()+ ", rand: "+rand);
        }
        else {
            Iterator it = compMoveablePos.iterator();
            int dst = 10000;
            Vector2 v = new Vector2();
            while (it.hasNext()) {
                Vector2 vec = (Vector2) it.next();
                if(vec.dst(center) < dst) {
                    dst = (int)vec.dst(center);
                    v = vec;
                }
            }
            addToCell((int)v.x, (int)v.y, new EnemyPiece((int)v.x, (int)v.y));
            Gdx.app.log("New Enemy Piece", v.toString() + ", dst: "+ dst);
        }
    }

    public void checkSurroundings() {
        Gdx.app.log("", "-----------" + turnNumber + "-----------");
        for (int i = 0; i < cell.length; i++) {
            for (int j = 0; j < cell[i].length; j++) {
                Object[] obj = new Object[8];
                if(cell[i][j].returnObj() instanceof PlayPiece) {
                    boolean n;
                    if(j+1 < cell.length && j-1 >= 0)
                        n = false;
                    else
                        n = true;

                    if(i+1 < cell[0].length) {
                        obj[0] = n ? null : cell[i + 1][j + 1].returnObj();
                        obj[1] = cell[i + 1][j].returnObj();
                        obj[2] = n ? null : cell[i + 1][j - 1].returnObj();
                    }
                    else {
                        obj[0] = null;
                        obj[1] = null;
                        obj[2] = null;
                    }

                    obj[3] = n ? null : cell[i][j+1].returnObj();
                    obj[4] = n ? null : cell[i][j-1].returnObj();

                    if(i-1 >= 0) {
                        obj[5] = n ? null : cell[i - 1][j + 1].returnObj();
                        obj[6] = cell[i - 1][j].returnObj();
                        obj[7] = n ? null : cell[i - 1][j - 1].returnObj();
                    }
                    else {
                        obj[5] = null;
                        obj[6] = null;
                        obj[7] = null;
                    }

                    int count = 0;
                    if(cell[i][j].returnObj() instanceof DeadPiece) {
                        for (int q = 0; q < 8; q++) {
                            if (obj[q] != null)
                                if (obj[q] instanceof PlayPiece)
                                    count++;
                        }
                        if (count > 4)
                            cell[i][j] = new Cell(i, j);
                    }
                    else if (cell[i][j].returnObj() instanceof EnemyPiece) {
                        for (int q = 0; q < 8; q++) {
                            if (obj[q] != null)
                                if (obj[q] instanceof PlayPiece && !(obj[q] instanceof EnemyPiece))
                                    count++;
                        }
                        if (count > 4)
                            cell[i][j] = new Cell(new PlayPiece(i, j));
                    }
                    else if (cell[i][j].returnObj() instanceof PlayPiece) {
                        for (int q = 0; q < 8; q++) {
                            if (obj[q] != null)
                                if (obj[q] instanceof EnemyPiece)
                                    count++;
                        }
                        if (count > 4)
                            cell[i][j] = new Cell(new EnemyPiece(i, j));
                    }
                }
            }
        }
        turnNumber ++;
    }

    private void reset() {
        moveablePos.clear();
        compMoveablePos.clear();
        for (int i = 0; i < cell.length; i++) {
            for (int j = 0; j < cell[i].length; j++) {
                if(cell[i][j].returnObj() instanceof Blank)
                    ((Blank) cell[i][j].returnObj()).setColor(Color.WHITE);
            }
        }
    }
    public void update() {
        reset();

        for(int i = 0; i < cell.length; i++) {
            for(int j = 0; j < cell[i].length; j++) {
                if(cell[i][j].returnObj().getMoveablePositions() != null) {
                    int mode = 0;
                    if (cell[i][j].returnObj() instanceof EnemyPiece)
                        mode = 1;
                    else if(cell[i][j].returnObj() instanceof PlayPiece)
                        mode = 2;

                    for (int k = 0; k < cell[i][j].returnObj().getMoveablePositions().length; k++) {
                        Vector2 v = cell[i][j].returnObj().getMoveablePositions()[k];
                        if ((v.x >= 0 && v.x < size) && (v.y >= 0 && v.y < size)) {
                            if(cell[(int)v.x][(int)v.y].returnObj() instanceof Blank) {
                                if (mode == 1) {
                                    compMoveablePos.add(cell[i][j].returnObj().getMoveablePositions()[k]);
                                }
                                else if(mode == 2) {
                                    moveablePos.add(cell[i][j].returnObj().getMoveablePositions()[k]);
                                }
                                else {}
                            }
                        }
                    }
                }
            }
        }
        Gdx.app.log("Computer Moveable Pos", compMoveablePos.toString());
        Gdx.app.log("Human Moveable Pos", moveablePos.toString());

        Iterator it = moveablePos.iterator();
        while (it.hasNext()) {
            Vector2 v = (Vector2)it.next();
            int x = (int)v.x;
            int y = (int)v.y;

            if(cell[x][y].returnObj() instanceof Blank) {
                ((Blank) cell[x][y].returnObj()).setColor(Color.CYAN);
            }
        }

        int counta = 0;
        for(int i = 0; i < cell.length; i++) {
            for(int j = 0; j < cell[i].length; j++) {
                if(cell[i][j].returnObj() instanceof PlayPiece && !(cell[i][j].returnObj() instanceof EnemyPiece) && !(cell[i][j].returnObj() instanceof DeadPiece))
                    counta ++;
            }
        }
        Gdx.app.log("counta", String.valueOf(counta));
        percentOfCells = ((counta*100)/(size*size));
        Gdx.app.log("percent", String.valueOf(percentOfCells));
    }

    private void drawGrid(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.LIGHT_GRAY);
        for(int i = 1; i<size; i++) {
            shapeRenderer.ellipse(cell[i][0].returnObj().getX(), cell[i][0].returnObj().getY(), Constants.sprites.border, size*Constants.sprites.unit);
            shapeRenderer.ellipse(cell[0][i].returnObj().getX(), cell[0][i].returnObj().getY(), size*Constants.sprites.unit, Constants.sprites.border);
        }
    }

    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        drawGrid(shapeRenderer);
        for(int i = 0; i<size; i++) {
            for(int j = 0; j<size; j++) {
                cell[i][j].draw(shapeRenderer);
            }
        }
        shapeRenderer.end();
    }

    public boolean isGameOn() {
        return gameOn;
    }

    public int getTurnNumber() {
        return turnNumber;
    }

    public int getPercentOfCellsTaken() {
        return percentOfCells;
    }
}
