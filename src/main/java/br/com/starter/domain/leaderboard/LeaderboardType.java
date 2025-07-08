package br.com.starter.domain.leaderboard;

public enum LeaderboardType {
    DAILY_MUNDI("Daily Mundi"),
    WEEKLY_MUNDI("Weekly Mundi"),
    MONTHLY_MUNDI("Monthly Mundi"),
    ALL_TIME_MUNDI("All Time Mundi"),
    DAILY_COUNTRY("Daily Country"),
    WEEKLY_COUNTRY("Weekly Country"),
    MONTHLY_COUNTRY("Monthly Country"),
    ALL_TIME_COUNTRY("All Time Country"),
    DAILY_STATE("Daily State"),
    WEEKLY_STATE("Weekly State"),
    MONTHLY_STATE("Monthly State"),
    ALL_TIME_STATE("All Time State"),
    DAILY_CITY("Daily City"),
    WEEKLY_CITY("Weekly City"),
    MONTHLY_CITY("Monthly City"),
    ALL_TIME_CITY("All Time City");

    private final String description;

    LeaderboardType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
