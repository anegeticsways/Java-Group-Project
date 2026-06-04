/*
Contributed by Abang Afiq Aiman (103041)
Role: Member 3 - Engagement Engineer

Description: 
Manages logic for badges, points, and motivational elements.

Tested by: Ainin (102810)
*/

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
            return "Excellent work! You understand e-waste very well.";
        } else if (score >= SILVER_THRESHOLD) {
            return "Good progress! Keep learning and improving.";
        } else if (score >= BRONZE_THRESHOLD) {
            return "Nice effort! Review the learning content and try again.";
        } else {
            return "Do not worry. Keep learning and you can improve your score.";
        }
    }

    public String getRubricMessage(int score) {
        if (score >= 80) {
            return "Outstanding!";
        } else if (score >= 60) {
            return "That's good!";
        } else if (score >= 40) {
            return "Good try!";
        } else if (score >= 20) {
            return "You can do better!";
        } else {
            return "Don't give up!";
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
            return 0;
        }
    }

    public String getMotivationalMessage(int score, String name) {
        return name + ", " + getMotivationalMessage(score);
    }
}
