package com.bearfishapps.cells;

public interface ActionResolver {
    public boolean getSignedInGPGS();
    public void loginGPGS();
    public void submitScoreGPGS(int score);
    public void getLeaderboardGPGS();
    public void unlockAchievementGPGS(String achievementId);
    public void getAchievementsGPGS();
    public void showAd();
}
