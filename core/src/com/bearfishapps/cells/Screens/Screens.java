package com.bearfishapps.cells.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.bearfishapps.cells.Cells;
import com.bearfishapps.cells.Constants.Constants;

public abstract class Screens implements com.badlogic.gdx.Screen {
    protected Cells game;
    private int color[] = new int[4];
    private float keyFrame = 0;

    protected OrthographicCamera camera;
    protected Stage stage;
    protected Table table;

    protected SpriteBatch batch;
    protected ShapeRenderer shapeRenderer;

    public Screens(Cells game) {
        this.game = game;

        color[0] = 0; color[1] = 0; color[2] = 0; color[3] = 0;

        camera = new OrthographicCamera(Constants.screen.width, Constants.screen.height);
        camera.position.set(camera.viewportWidth/2, camera.viewportHeight / 2, 0);
        camera.update();

        stage = new Stage(new ExtendViewport(camera.viewportWidth, camera.viewportHeight));
        table = new Table();

        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
    }

    public Screens(Cells game, int sizeX, int sizeY) {
        this.game = game;

        color[0] = 0; color[1] = 0; color[2] = 0; color[3] = 0;

        camera = new OrthographicCamera(sizeX, sizeY);
        camera.position.set(0, -camera.viewportHeight / 2, 0);
        camera.update();

        stage = new Stage(new ExtendViewport(camera.viewportWidth, camera.viewportHeight));
        table = new Table();
    }

    protected void setBackgroundColor(int r, int g, int b, int a) {
        color[0] = r; color[1] = g; color[2] = b; color[3] = a;
    }

    @Override
    public void show() {
        InputMultiplexer multiplexer = new InputMultiplexer();

        preShow(table, multiplexer);
        table.setFillParent(true);

        stage.addActor(table);
        stage.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(1f)));
        multiplexer.addProcessor(stage);
        Gdx.input.setInputProcessor(multiplexer);
    }

    @Override
    public void render(float delta) {
        keyFrame+=delta;
        Gdx.gl.glClearColor(color[0] / 255.0f, color[1] / 255.0f, color[2] / 255.0f, color[3]/100f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        draw(delta, keyFrame);

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    public abstract void draw(float delta, float animationKeyFrame);
    public abstract void preShow(Table table, InputMultiplexer multiplexer);
}
