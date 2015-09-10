package com.bearfishapps.cells.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.TimeUtils;
import com.bearfishapps.cells.Assets.AssetLoader;
import com.bearfishapps.cells.Cells;
import com.bearfishapps.cells.Objects.Grid;
import com.bearfishapps.cells.Tools.CustomClasses.CustomImageButton;
import com.bearfishapps.cells.Tools.CustomClasses.CustomLabel;

public class GameScreen extends Screens{

    private Grid grid;
    private InputProcessor inputProcessor;

    private int x, y;
    private long timeHelper;

    private Label stats;

    private ImageButton restartButton;
    private ImageButton quitButton;
    private ImageButton enlargeButton;
    private ImageButton shrinkButton;
    private ImageButton showButton;

    private int size = -1;
    private boolean show = false;
    public GameScreen(Cells game) {
        super(game);

        size = MathUtils.random(15, 35);
        grid = new Grid(size);

        CustomLabel.make(32, Color.BLACK);
        stats = new Label("Moves: "+ grid.getTurnNumber(), CustomLabel.style);

        CustomImageButton.make(AssetLoader.restartButton, AssetLoader.restartButton2);
        restartButton = new ImageButton(CustomImageButton.style);
        CustomImageButton.make(AssetLoader.quitButton, AssetLoader.quitButton2);
        quitButton = new ImageButton(CustomImageButton.style);
        CustomImageButton.make(AssetLoader.enlargeButton, AssetLoader.enlargeButton2);
        enlargeButton = new ImageButton(CustomImageButton.style);
        CustomImageButton.make(AssetLoader.shrinkButton, AssetLoader.shrinkButton2);
        shrinkButton = new ImageButton(CustomImageButton.style);
        CustomImageButton.make(AssetLoader.prevButton, AssetLoader.prevButton2);
        showButton = new ImageButton(CustomImageButton.style);

        restartButton.setVisible(false);
        quitButton.setVisible(false);
        restartButton.setVisible(false);
        restartButton.setVisible(false);

        inputProcessor = new InputProcessor() {
            @Override
            public boolean keyDown(int keycode) {
                return false;
            }

            @Override
            public boolean keyUp(int keycode) {
                return false;
            }

            @Override
            public boolean keyTyped(char character) {
                return false;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                timeHelper = TimeUtils.millis();
                x = screenX;
                y = screenY;
                return false;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                if(TimeUtils.timeSinceMillis(timeHelper)<130L) {
                    Vector3 asdf = new Vector3(screenX, screenY, 0);
                    camera.unproject(asdf);

                    grid.clickTile(asdf);
                }

                return false;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                camera.translate((x - screenX) * camera.zoom * 2, (screenY - y) * camera.zoom * 2);
                camera.update();
                x = screenX; y = screenY;
                return false;
            }

            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                return false;
            }

            @Override
            public boolean scrolled(int amount) {
                if(camera.zoom>=0)
                    camera.zoom+= amount*0.25f;
                if(camera.zoom<0)
                    camera.zoom = 0;
                camera.update();
                return false;
            }
        };
        super.setBackgroundColor(255, 255, 255, 100);
        grid.update();
    }

    @Override
    public void draw(float delta, float animationKeyFrame) {
        shapeRenderer.setProjectionMatrix(camera.combined);
        grid.draw(shapeRenderer);
        if(!grid.isGameOn()) {
            Gdx.app.log("Game", "GameOver");
            game.setScreen(new GameOverScreen(game, grid.getTurnNumber(), size));
        }

        stats.setText("Moves: "+ grid.getTurnNumber());
    }

    @Override
    public void preShow(Table table, InputMultiplexer multiplexer) {
        restartButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game));
            }
        });

        quitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameOverScreen(game, 999999999, 1));
            }
        });

        showButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(!show) {
                    show = true;
                    quitButton.setVisible(true);
                    restartButton.setVisible(true);

                    quitButton.addAction(Actions.sequence(Actions.fadeOut(0f), Actions.fadeIn(0.5f)));
                    restartButton.addAction(Actions.sequence(Actions.fadeIn(0.5f)));

                    CustomImageButton.make(AssetLoader.nextButton, AssetLoader.nextButton2);
                    showButton.setStyle(CustomImageButton.style);
                }
                else if(show) {
                    show = false;

                    quitButton.addAction(Actions.sequence(Actions.fadeOut(0.5f)));
                    restartButton.addAction(Actions.sequence(Actions.fadeOut(0.5f), Actions.run(new Runnable() {
                        @Override
                        public void run() {
                            quitButton.setVisible(false);
                            restartButton.setVisible(false);
                        }
                    })));
                    CustomImageButton.make(AssetLoader.prevButton, AssetLoader.prevButton2);
                    showButton.setStyle(CustomImageButton.style);

                }
            }
        });

        enlargeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (camera.zoom >= 0)
                    camera.zoom -= 0.25f;
                if (camera.zoom < 0)
                    camera.zoom = 0;
                camera.update();
            }
        });
        shrinkButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (camera.zoom >= 0)
                    camera.zoom += 0.25f;
                if (camera.zoom < 0)
                    camera.zoom = 0;
                camera.update();
            }
        });

        table.top().right();
        table.add(stats).expandX();
        table.add(showButton).right().row();

        table.add(enlargeButton).padTop(10).left();
        table.add(restartButton).padTop(10).right().row();

        table.add(shrinkButton).padTop(10).left();
        table.add(quitButton).padTop(10).right().row();
        multiplexer.addProcessor(inputProcessor);
    }
}
