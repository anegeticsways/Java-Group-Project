/*
Contributed by Abang Afiq Aiman (103041)
Role: Member 3 - Engagement Engineer

Tested by Andrean (103325)

Description: 
Manages logic for badges, points, and motivational elements.
*/

public class GamificationEngine implements RewardSystem {

    // Define thresholds for badge levels
    @Override
    public String determineBadge(int score) {
        if (score >= GOLD_THRESHOLD) { // 80 and above for Gold badge
            return "🌟 Gold Eco-Warrior"; 
        } else if (score >= SILVER_THRESHOLD) { // 60 to 79 for Silver badge
            return "🥈 Silver Recycler";
        } else if (score >= BRONZE_THRESHOLD) { // 40 to 59 for Bronze badge
            return "🥉 Bronze Learner";
        } else {
            return "🌱 Novice Sprout"; // Below 40 for Novice badge
        }
    }

    // Method to display motivational messages based on score ranges
    @Override
    public String getMotivationalMessage(int score) {
        if (score >= GOLD_THRESHOLD) {
            return "Excellent work! You understand e-waste very well."; // 80 and above
        } else if (score >= SILVER_THRESHOLD) {
            return "Good progress! Keep learning and improving."; // 60 to 79
        } else if (score >= BRONZE_THRESHOLD) {
            return "Nice effort! Review the learning content and try again."; // 40 to 59
        } else {
            return "Do not worry. Keep learning and you can improve your score."; // Below 40
        }
    }

    // Method to provide rubric message based on score ranges
    public String getRubricMessage(int score) {
        if (score >= 80) {
            return "Outstanding!"; // 80 and above
        } else if (score >= 60) {
            return "That's good!"; // 60 to 79
        } else if (score >= 40) {
            return "Good try!";
        } else if (score >= 20) {
            return "You can do better!"; // 20 to 39
        } else {
            return "Don't give up!"; // Below 20
        }
    }

    // Method to calculate how many more points the user needs to reach the next badge level
    @Override
    public int calculatePointsToNextBadge(int score) {
        if (score < BRONZE_THRESHOLD) {
            return BRONZE_THRESHOLD - score;
        } else if (score < SILVER_THRESHOLD) {
            return SILVER_THRESHOLD - score;
        } else if (score < GOLD_THRESHOLD) {
            return GOLD_THRESHOLD - score;
        } else {
            return 0;
        }
    }

    // Overloaded method to include the user's name in the motivational message
    public String getMotivationalMessage(int score, String name) {
        return name + ", " + getMotivationalMessage(score);
    }
}
