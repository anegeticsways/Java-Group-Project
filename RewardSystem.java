/* 
Contributed by Abang Afiq Aiman.
Role: Member 3 - Engagement Engineer

Description: 
Defines the criteria and thresholds for earning different rewards.
*/

public interface RewardSystem {

    int BRONZE_THRESHOLD = 20;
    int SILVER_THRESHOLD = 50;
    int GOLD_THRESHOLD = 80;

    String determineBadge(int score);
    String getMotivationalMessage(int score);
    int calculatePointsToNextBadge(int score);
}
