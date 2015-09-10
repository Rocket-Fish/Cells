package com.bearfishapps.cells.Tools.CustomClasses;


import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.bearfishapps.cells.Tools.FontGenerator;

public class CustomButton {

    private static BitmapFont font;

    public static TextButton.TextButtonStyle style;

    public static void make(int size, TextureRegion region1, TextureRegion region2) {

        FontGenerator.generate(size);
        font = FontGenerator.returnFont();

        style = new TextButton.TextButtonStyle();
        style.up = new TextureRegionDrawable(region1);
        style.down = new TextureRegionDrawable(region2);
        style.font = font;
    }

    public static void dispose() {
        font.dispose();
        FontGenerator.dispose();
    }
}
