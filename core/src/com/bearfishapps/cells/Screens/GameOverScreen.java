package com.bearfishapps.cells.Screens;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.bearfishapps.cells.Assets.AssetLoader;
import com.bearfishapps.cells.Cells;
import com.bearfishapps.cells.Constants.Pref;
import com.bearfishapps.cells.Tools.CustomClasses.CustomImageButton;
import com.bearfishapps.cells.Tools.CustomClasses.CustomLabel;
import com.bearfishapps.cells.Tools.Prefs;

public class GameOverScreen extends Screens{

    private int score;
    private boolean highScore;
    private Label title;
    private Label scoreLabel;
    private ImageButton retryButton;
    private ImageButton quitButton;

    public GameOverScreen(Cells game, int moves, int size) {
        super(game);
        super.setBackgroundColor(255, 255, 255, 100);

        Prefs.init();

        if(MathUtils.random(0, 3)<3)
            game.actionResolver.showAd();

        score = (int)((float)moves/(float)size*100f);
        if(score < Prefs.getScore())
            highScore = true;
        else
            highScore = false;

        CustomLabel.make(64, Color.BLACK);
        title = new Label("Game Over", CustomLabel.style);
        CustomLabel.make(48, Color.BLACK);

        if(score > 10000000 && !highScore)
            scoreLabel = new Label("Score: 10000000", CustomLabel.style);
        else
            scoreLabel = new Label("Score: "+score, CustomLabel.style);

        CustomImageButton.make(AssetLoader.restartButton, AssetLoader.restartButton2);
        retryButton = new ImageButton(CustomImageButton.style);
        CustomImageButton.make(AssetLoader.quitButton, AssetLoader.quitButton2);
        quitButton = new ImageButton(CustomImageButton.style);

        if(highScore) {
            title.setText("High Score");
            Prefs.setScore(score);
        }

        if (game.actionResolver.getSignedInGPGS()) {
            game.actionResolver.submitScoreGPGS(Prefs.getScore());
            game.actionResolver.unlockAchievementGPGS("CgkI6ZHYr9IEEAIQAA");
            if (Prefs.getScore() < 1000)
                game.actionResolver.unlockAchievementGPGS("CgkI6ZHYr9IEEAIQAQ");
            if (Prefs.getScore() < 500)
                game.actionResolver.unlockAchievementGPGS("CgkI6ZHYr9IEEAIQAg");
            if (Prefs.getScore() < 300)
                game.actionResolver.unlockAchievementGPGS("CgkI6ZHYr9IEEAIQAw");
            if (Prefs.getScore() < 200)
                game.actionResolver.unlockAchievementGPGS("CgkI6ZHYr9IEEAIQBA");
            if (Prefs.getScore() < 100)
                game.actionResolver.unlockAchievementGPGS("CgkI6ZHYr9IEEAIQBQ");
        }

    }

    @Override
    public void draw(float delta, float animationKeyFrame) {
    }

    @Override
    public void preShow(Table table, InputMultiplexer multiplexer) {
        retryButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game));
            }
        });

        quitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenuScreen(game));
            }
        });

        table.top();
        table.add(title).row();
        table.add(scoreLabel).padTop(10).row();
        table.add(retryButton).padTop(10).row();
        table.add(quitButton).padTop(10).row();
    }
}
