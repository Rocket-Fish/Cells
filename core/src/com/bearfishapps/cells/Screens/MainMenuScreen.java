package com.bearfishapps.cells.Screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.bearfishapps.cells.Assets.AssetLoader;
import com.bearfishapps.cells.Cells;
import com.bearfishapps.cells.Tools.CustomClasses.CustomButton;
import com.bearfishapps.cells.Tools.CustomClasses.CustomImageButton;
import com.bearfishapps.cells.Tools.CustomClasses.CustomLabel;
import com.bearfishapps.cells.Tools.Prefs;

public class MainMenuScreen extends Screens{

    private Label title;
    private ImageButton playButton;
    private ImageButton questionButton;
    private ImageButton highScoreButton;
    private ImageButton achievementsButton;
    private ImageButton quitButton;

    public MainMenuScreen(Cells game) {
        super(game);
        super.setBackgroundColor(255, 255, 255, 100);

        AssetLoader.load();

        CustomLabel.make(128, Color.BLACK);
        title = new Label("CELLS!", CustomLabel.style);

        CustomImageButton.make(AssetLoader.playButton, AssetLoader.playButton2);
        playButton = new ImageButton(CustomImageButton.style);
        CustomImageButton.make(AssetLoader.questionButton, AssetLoader.questionButton2);
        questionButton = new ImageButton(CustomImageButton.style);
        CustomImageButton.make(AssetLoader.leaderBoardButton, AssetLoader.leaderBoardButton2);
        highScoreButton = new ImageButton(CustomImageButton.style);
        CustomImageButton.make(AssetLoader.achievementButton, AssetLoader.achievementButton2);
        achievementsButton = new ImageButton(CustomImageButton.style);
        CustomImageButton.make(AssetLoader.quitButtonL, AssetLoader.quitButtonL2);
        quitButton = new ImageButton(CustomImageButton.style);

    }

    @Override
    public void draw(float delta, float animationKeyFrame) {

    }

    @Override
    public void preShow(Table table, InputMultiplexer multiplexer) {
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game));
            }
        });

        questionButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new ExplainScreen(game));
            }
        });

        highScoreButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (game.actionResolver.getSignedInGPGS()) {
                    game.actionResolver.getLeaderboardGPGS();
                } else game.actionResolver.loginGPGS();
            }
        });

        achievementsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (game.actionResolver.getSignedInGPGS()) {
                    game.actionResolver.getAchievementsGPGS();
                } else game.actionResolver.loginGPGS();
            }
        });

        quitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        table.bottom();
        table.add(title).colspan(2).row();
        table.add(playButton).padTop(10).colspan(2).row();
        table.add(questionButton).padTop(10).colspan(2).row();
        table.add(highScoreButton).padTop(10).padLeft(10);
        table.add(achievementsButton).padTop(10).row();
        table.add(quitButton).padTop(10).colspan(2).row();
    }

    @Override
    public void dispose() {
        super.dispose();
        AssetLoader.dispose();
        CustomButton.dispose();
    }

}
