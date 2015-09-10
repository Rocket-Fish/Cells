package com.bearfishapps.cells.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.bearfishapps.cells.Assets.AssetLoader;
import com.bearfishapps.cells.Cells;
import com.bearfishapps.cells.Tools.Prefs;

public class SplashScreen extends Screens{
    private Texture texture = new Texture("Bearfish.png");
    private Image splashImage = new Image(texture);

    public SplashScreen(Cells c)
    {
        super(c, 60, 100);
        game = c;
        super.setBackgroundColor(255, 255, 255, 100);
    }

    @Override
    public void show() {
        splashImage.setX(0);
        splashImage.setY(0);
//        splashImage.setX((Gdx.graphics.getWidth()-16)/2);
//        splashImage.setY((Gdx.graphics.getHeight() - 16) / 2);
        stage.addActor(splashImage);

        splashImage.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(2f), Actions.delay(2), Actions.fadeOut(1f), Actions.run(new Runnable() {
            @Override
            public void run() {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenuScreen(game));
            }
        })));
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        texture.dispose();
        super.dispose();
    }

    @Override
    public void draw(float delta, float animationKeyFrame) {

    }

    @Override
    public void preShow(Table table, InputMultiplexer multiplexer) {

    }
}
