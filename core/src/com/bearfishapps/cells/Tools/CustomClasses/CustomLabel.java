package com.bearfishapps.cells.Tools.CustomClasses;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.bearfishapps.cells.Tools.FontGenerator;

public class CustomLabel {
    public static Label.LabelStyle style;

    public static void make(int size) {
        FontGenerator.generate(size);
        style = new Label.LabelStyle(FontGenerator.returnFont(), Color.WHITE);
    }

    public static void make(int size, Color color) {
        FontGenerator.generate(size);
        style = new Label.LabelStyle(FontGenerator.returnFont(), color);
    }

    public static void dispose() {
        FontGenerator.dispose();
    }
}
