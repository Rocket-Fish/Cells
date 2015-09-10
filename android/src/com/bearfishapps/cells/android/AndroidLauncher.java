package com.bearfishapps.cells.android;

import android.content.Intent;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.bearfishapps.cells.ActionResolver;
import com.bearfishapps.cells.Cells;
import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.GameHelper;
import com.purplebrain.adbuddiz.sdk.AdBuddiz;

public class AndroidLauncher extends AndroidApplication  implements GameHelper.GameHelperListener{

	private GameHelper gameHelper;
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		AdBuddiz.setPublisherKey("8a4847ed-db85-45c6-9b19-52cd8a212833");
		AdBuddiz.cacheAds(this);

		if (gameHelper == null) {
			gameHelper = new GameHelper(this, GameHelper.CLIENT_GAMES);
		}
		gameHelper.setup(this);

		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new Cells(new ActionResolver() {
			@Override
			public boolean getSignedInGPGS() {
				return gameHelper.isSignedIn();
			}

			@Override
			public void loginGPGS() {
				try {
					runOnUiThread(new Runnable() {
						public void run() {
							gameHelper.beginUserInitiatedSignIn();
						}
					});
				} catch (final Exception ex) {

				}
			}

			@Override
			public void submitScoreGPGS(int score) {				//     CgkItvuerZIHEAIQBQ
				Games.Leaderboards.submitScore(gameHelper.getApiClient(), "CgkI6ZHYr9IEEAIQCA", score);
			}

			@Override
			public void getLeaderboardGPGS() {
				if (gameHelper.isSignedIn()) {
					startActivityForResult(Games.Leaderboards.getLeaderboardIntent(gameHelper.getApiClient(), "CgkI6ZHYr9IEEAIQCA"), 100);
				}
				else if (!gameHelper.isConnecting()) {
					loginGPGS();
				}

			}

			@Override
			public void unlockAchievementGPGS(String achievementId) {
				Games.Achievements.unlock(gameHelper.getApiClient(), achievementId);
			}

			@Override
			public void getAchievementsGPGS() {
				if (gameHelper.isSignedIn()) {
					startActivityForResult(Games.Achievements.getAchievementsIntent(gameHelper.getApiClient()), 101);
				}
				else if (!gameHelper.isConnecting()) {
					loginGPGS();
				}

			}

			public void showAd() {
				show();
			}

		}), config);
	}
	private void show() {
		AdBuddiz.showAd(this);
	}

	@Override
	public void onStart(){
		super.onStart();
		gameHelper.onStart(this);
	}
	@Override
	public void onStop(){
		super.onStop();
		gameHelper.onStop();
	}
	@Override
	public void onActivityResult(int request, int response, Intent data) {
		super.onActivityResult(request, response, data);
		gameHelper.onActivityResult(request, response, data);
	}

	@Override
	public void onSignInFailed() {

	}

	@Override
	public void onSignInSucceeded() {

	}
}
