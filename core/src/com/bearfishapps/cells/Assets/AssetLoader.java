package com.bearfishapps.cells.Assets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.bearfishapps.cells.Constants.Constants;

public class AssetLoader {
    private static Texture texture;

    public static TextureRegion achievementButton ;
    public static TextureRegion achievementButton2;
    public static TextureRegion bothButton        ;
    public static TextureRegion bothButton2       ;
    public static TextureRegion button            ;
    public static TextureRegion button2           ;
    public static TextureRegion enlargeButton     ;
    public static TextureRegion enlargeButton2    ;
    public static TextureRegion leaderBoardButton ;
    public static TextureRegion leaderBoardButton2;
    public static TextureRegion nextButton        ;
    public static TextureRegion nextButton2       ;
    public static TextureRegion pauseButton       ;
    public static TextureRegion pauseButton2      ;
    public static TextureRegion playButton        ;
    public static TextureRegion playButton2       ;
    public static TextureRegion prevButton        ;
    public static TextureRegion prevButton2       ;
    public static TextureRegion questionButton    ;
    public static TextureRegion questionButton2   ;
    public static TextureRegion quitButton        ;
    public static TextureRegion quitButton2       ;
    public static TextureRegion quitButtonL       ;
    public static TextureRegion quitButtonL2      ;
    public static TextureRegion restartButton     ;
    public static TextureRegion restartButton2    ;
    public static TextureRegion shrinkButton      ;
    public static TextureRegion shrinkButton2     ;

    public static void load() {
        texture = new Texture(Constants.ui.bottons);
        texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        achievementButton  = new TextureRegion(texture, 0, 0, 128, 128);
        achievementButton2 = new TextureRegion(texture, 128, 0, 128, 128);
        bothButton         = new TextureRegion(texture, 0, 128, 128, 128);
        bothButton2        = new TextureRegion(texture, 128, 128, 128, 128);
        button             = new TextureRegion(texture, 256, 0, 128, 128);
        button2            = new TextureRegion(texture, 256, 128, 128, 128);
        enlargeButton      = new TextureRegion(texture, 0, 384, 64, 64);
        enlargeButton2     = new TextureRegion(texture, 64, 384, 64, 64);
        leaderBoardButton  = new TextureRegion(texture, 384, 0, 128, 128);
        leaderBoardButton2 = new TextureRegion(texture, 384, 128, 128, 128);
        nextButton         = new TextureRegion(texture, 128, 384, 64, 64);
        nextButton2        = new TextureRegion(texture, 192, 384, 64, 64);
        pauseButton        = new TextureRegion(texture, 256, 384, 64, 64);
        pauseButton2       = new TextureRegion(texture, 512, 128, 64, 64);
        playButton         = new TextureRegion(texture, 0, 256, 128, 128);
        playButton2        = new TextureRegion(texture, 128, 256, 128, 128);
        prevButton         = new TextureRegion(texture, 512, 192, 64, 64);
        prevButton2        = new TextureRegion(texture, 576, 128, 64, 64);
        questionButton     = new TextureRegion(texture, 256, 256, 128, 128);
        questionButton2    = new TextureRegion(texture, 384, 256, 128, 128);
        quitButton         = new TextureRegion(texture, 320, 384, 64, 64);
        quitButton2        = new TextureRegion(texture, 640, 128, 64, 64);
        quitButtonL        = new TextureRegion(texture, 512, 0, 128, 128);
        quitButtonL2       = new TextureRegion(texture, 640, 0, 128, 128);
        restartButton      = new TextureRegion(texture, 576, 192, 64, 64);
        restartButton2     = new TextureRegion(texture, 384, 384, 64, 64);
        shrinkButton       = new TextureRegion(texture, 768, 0, 64, 64);
        shrinkButton2      = new TextureRegion(texture, 512, 256, 64, 64);

    }

    public static void dispose() {
        texture.dispose();
    }
}
