package com.bearfishapps.cells.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.bearfishapps.cells.Constants.Constants;

public class Prefs {
    private static Preferences prefs;
    public static void init() {
        prefs = Gdx.app.getPreferences(Constants.pref.name);
        if(!prefs.contains(Constants.pref.score)) {
            prefs.putInteger(Constants.pref.score, 10000000);
            prefs.flush();
        }
    }

    public static void setScore(int score) {
        prefs.putInteger(Constants.pref.score, score);
        prefs.flush();
    }

    public static int getScore() {
        return prefs.getInteger(Constants.pref.score);
    }
}
