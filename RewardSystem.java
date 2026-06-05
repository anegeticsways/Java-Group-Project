/* 
Contributed by Abang Afiq Aiman.
Role: Member 3 - Engagement Engineer

Description: 
Defines the criteria and thresholds for earning different rewards.

Tested by: Ainin (102810)
*/

public interface RewardSystem {

    int BRONZE_THRESHOLD = 20; // Threshold for earning a Bronze badge
    int SILVER_THRESHOLD = 50; // Threshold for earning a Silver badge
    int GOLD_THRESHOLD = 80; // Threshold for earning a Gold badge

    String determineBadge(int score); // Method to determine the badge earned based on the user's score
    String getMotivationalMessage(int score); // Method to get a motivational message based on the user's score
    int calculatePointsToNextBadge(int score); // Method to calculate the points needed to reach the next badge level
}
