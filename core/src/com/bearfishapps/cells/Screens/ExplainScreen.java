package com.bearfishapps.cells.Screens;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.bearfishapps.cells.Assets.AssetLoader;
import com.bearfishapps.cells.Cells;
import com.bearfishapps.cells.Tools.CustomClasses.CustomImageButton;
import com.bearfishapps.cells.Tools.CustomClasses.CustomLabel;

public class ExplainScreen extends Screens{
    public Label content;
    public ImageButton nextButton;
    public ImageButton quitButton;
    public ImageButton prevButton;

    private int segment = 0;
    private String[] text = new String[6];

    public ExplainScreen(Cells game) {
        super(game);
        super.setBackgroundColor(255, 255, 255, 100);

        text[0] = "Frequently Asked Questions:\n\n" +
                "How Dow I play this game?\n" +
                "Your gamepieces are blue, \n" +
                "and the squares that you can\n" +
                "move to is marked in lightblue.\n" +
                "Click on the lightblue squares\n" +
                "to make your moves";
        text[1] = "\nWhat is the purpose of\n " +
                "this game?\n" +
                "To force the computer (RED)\n" +
                "to have no more possible moves\n" +
                "in as less moves as possible.\n" +
                "You want to corner all\n" +
                "computer pieces.";
        text[2] = "\nHow is my score calculated?\n" +
                "We use the formula: \n" +
                "Score \n" +
                "=(Number of turns you took\n" +
                "to defeat the computer) \n" +
                "divided by\n" +
                "(the size of the game feild) \n" +
                "times one hundred" +
                "";
        text[3] = "\nWhy are my pieces changing \n" +
                "color?\n" +
                "If you piece has 5 or more\n" +
                "enemies surrounding it, \n" +
                "it will become an enemy piece.\n" +
                "The same goes for the enemy.\n";
        text[4] = "\nIs it whats a better score?\n" +
                "The lower the score, the better!\n";
        text[5] = "Press the X button to return";

        CustomLabel.make(32, Color.BLACK);
        content = new Label("INFORMATION: ", CustomLabel.style);

        CustomImageButton.make(AssetLoader.nextButton, AssetLoader.nextButton2);
        nextButton = new ImageButton(CustomImageButton.style);

        CustomImageButton.make(AssetLoader.quitButton, AssetLoader.quitButton2);
        quitButton = new ImageButton(CustomImageButton.style);

        CustomImageButton.make(AssetLoader.prevButton, AssetLoader.prevButton2);
        prevButton = new ImageButton(CustomImageButton.style);

    }

    @Override
    public void draw(float delta, float animationKeyFrame) {
        content.setText(text[segment]);

    }

    @Override
    public void preShow(Table table, InputMultiplexer multiplexer) {
        prevButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(segment>0)
                    segment--;
            }
        });
        nextButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(segment<text.length-1)
                    segment++;
            }
        });

        quitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenuScreen(game));
            }
        });

        table.top();
        table.add(content).colspan(3).row();
        table.add(prevButton).padTop(25).padRight(10);
        table.add(quitButton).padTop(25).padRight(10);
        table.add(nextButton).padTop(25);

    }
}
