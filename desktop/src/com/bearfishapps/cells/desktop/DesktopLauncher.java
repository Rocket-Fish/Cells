package com.bearfishapps.cells.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.bearfishapps.cells.ActionResolver;
import com.bearfishapps.cells.Cells;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 360;
		config.height = 600;
		new LwjglApplication(new Cells(new ActionResolver() {
			@Override
			public boolean getSignedInGPGS() {
				return false;
			}

			@Override
			public void loginGPGS() {

			}

			@Override
			public void submitScoreGPGS(int score) {

			}

			@Override
			public void getLeaderboardGPGS() {

			}

			@Override
			public void unlockAchievementGPGS(String achievementId) {

			}

			@Override
			public void getAchievementsGPGS() {

			}

			@Override
			public void showAd() {

			}
		}), config);
	}
}
