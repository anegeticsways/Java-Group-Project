/*
Contributed by Abang Afiq Aiman (103041)
Role: Member 3 - Engagement Engineer

Description: 
Manages logic for badges, points, and motivational elements.
*/

import javax.swing.JOptionPane;

public class GamificationEngine implements RewardSystem {

    @Override
    public String determineBadge(int score) {
        if (score >= GOLD_THRESHOLD) {
            return "🌟 Gold Eco-Warrior";
        } else if (score >= SILVER_THRESHOLD) {
            return "🥈 Silver Recycler";
        } else if (score >= BRONZE_THRESHOLD) {
            return "🥉 Bronze Learner";
        } else {
            return "🌱 Novice Sprout";
        }
    }

    @Override
    public String getMotivationalMessage(int score) {
        if (score >= GOLD_THRESHOLD) {
            return "Outstanding! You are a true champion for the environment!";
        } else if (score >= SILVER_THRESHOLD) {
            return "Great job! Keep learning to become a top Eco-Warrior!";
        } else if (score >= BRONZE_THRESHOLD) {
            return "Good start! There is still more to learn about saving our planet.";
        } else {
            return "Every journey begins with a single step. Keep going!";
        }
    }

    @Override
    public int calculatePointsToNextBadge(int score) {
        if (score < BRONZE_THRESHOLD) {
            return BRONZE_THRESHOLD - score;
        } else if (score < SILVER_THRESHOLD) {
            return SILVER_THRESHOLD - score;
        } else if (score < GOLD_THRESHOLD) {
            return GOLD_THRESHOLD - score;
        } else {
            return 0; // Max badge achieved
        }
    }

    // Method to display a single user's gamified stats in a pop-up
    public void displayPlayerStats(String name, int score) {
        String badge = determineBadge(score);
        String message = getMotivationalMessage(score);
        int nextBadgePoints = calculatePointsToNextBadge(score);

        String statsMessage = "User: " + name + "\n"
                            + "Total Score: " + score + "\n"
                            + "Current Badge: " + badge + "\n\n"
                            + message + "\n";

        if (nextBadgePoints > 0) {
            statsMessage += "(Need " + nextBadgePoints + " more points for the next badge!)";
        } else {
            statsMessage += "(You have reached the highest rank!)";
        }

        JOptionPane.showMessageDialog(null, statsMessage, "Your Achievements", JOptionPane.INFORMATION_MESSAGE);
    }
}
