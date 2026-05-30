/*
Contributed by Ainin (102810)
Role: Member 4 - Assessment Lead

Description:
1. Quiz.java manages the quiz assessment part of the program.
2. Displays 20+ e-waste quiz questions to users.
3. Calculates the final score based on correct answers.
4. Gives score-based motivational messages.
5. Works together with Assessment.java to store question structure.
*/

import javax.swing.JOptionPane;
import java.util.ArrayList;

public class Quiz {

    private ArrayList<Assessment> questions;
    private int totalScore;

    public Quiz() {
        questions = new ArrayList<>();
        loadQuestions();
    }

    private void loadQuestions() {

        questions.add(new Assessment(
            "What is e-waste?",
            new String[]{"Food waste", "Electronic waste", "Plastic waste", "Paper waste"},
            1,
            "MCQ"
        ));

        questions.add(new Assessment(
            "Which item is an example of e-waste?",
            new String[]{"Banana peel", "Glass bottle", "Newspaper", "Old smartphone"},
            3,
            "MCQ"
        ));

        questions.add(new Assessment(
            "Which SDG is related to responsible consumption and production?",
            new String[]{"SDG 3", "SDG 6", "SDG 12", "SDG 15"},
            2,
            "MCQ"
        ));

        questions.add(new Assessment(
            "Which material can be found in e-waste?",
            new String[]{"Sugar", "Cotton", "Lead", "Wood"},
            2,
            "MCQ"
        ));

        questions.add(new Assessment(
            "True or False: E-waste may contain toxic materials.",
            new String[]{"False", "True"},
            1,
            "True/False"
        ));

        questions.add(new Assessment(
            "Improper e-waste disposal can pollute:",
            new String[]{"Only books", "Only clothes", "Soil and water", "Nothing"},
            2,
            "MCQ"
        ));

        questions.add(new Assessment(
            "Which is a responsible way to dispose of e-waste?",
            new String[]{"Throw into river", "Burn it", "Use certified recycling centres", "Mix with food waste"},
            2,
            "MCQ"
        ));

        questions.add(new Assessment(
            "True or False: Recycling e-waste helps reduce pollution.",
            new String[]{"True", "False"},
            0,
            "True/False"
        ));

        questions.add(new Assessment(
            "Which device is considered e-waste when discarded?",
            new String[]{"Plastic spoon", "Paper bag", "Food wrapper", "Laptop"},
            3,
            "MCQ"
        ));

        questions.add(new Assessment(
            "Why should we repair electronics?",
            new String[]{"To waste money", "To reduce e-waste", "To increase pollution", "To damage devices"},
            1,
            "MCQ"
        ));

        questions.add(new Assessment(
            "True or False: Batteries can be part of e-waste.",
            new String[]{"True", "False"},
            0,
            "True/False"
        ));

        questions.add(new Assessment(
            "What is the main purpose of e-waste recycling centres?",
            new String[]{"Sell unhealthy food", "Burn electronics openly", "Safely process discarded electronics", "Store plastic bottles only"},
            2,
            "MCQ"
        ));

        questions.add(new Assessment(
            "What can toxic chemicals from e-waste affect?",
            new String[]{"Only furniture", "Only books", "Human health", "Nothing"},
            2,
            "MCQ"
        ));

        questions.add(new Assessment(
            "Which is better before buying a new device?",
            new String[]{"Throw old device immediately", "Check if old device can be repaired", "Burn old device", "Hide old device"},
            1,
            "MCQ"
        ));

        questions.add(new Assessment(
            "True or False: Donating working electronics can reduce e-waste.",
            new String[]{"False", "True"},
            1,
            "True/False"
        ));

        questions.add(new Assessment(
            "Which of these is NOT e-waste?",
            new String[]{"Printer", "Tablet", "Apple peel", "Charger"},
            2,
            "MCQ"
        ));

        questions.add(new Assessment(
            "Why is informal burning of e-waste dangerous?",
            new String[]{"It creates clean energy", "It improves air quality", "It releases toxic gases", "It reduces pollution"},
            2,
            "MCQ"
        ));

        questions.add(new Assessment(
            "Which valuable material can be recovered from recycled electronic devices?",
            new String[]{"Plastic straws", "Gold", "Rubber bands", "Paper clips"},
            1,
            "MCQ"
        ));

        questions.add(new Assessment(
            "True or False: Throwing electronics into regular rubbish bins is recommended.",
            new String[]{"True", "False"},
            1,
            "True/False"
        ));

        questions.add(new Assessment(
            "True or False: E-waste recycling is unnecessary because electronics naturally disappear over time.",
            new String[]{"True", "False"},
            1,
            "True/False"
        ));
    }

    public void displayQuiz(String name, int score) {
        int correctCount = 0;

        JOptionPane.showMessageDialog(
            null,
            "Warning: Your score will reset if you retake the quiz.\nPlease answer all questions carefully.",
            "Quiz Warning",
            JOptionPane.WARNING_MESSAGE
        );

        for (int i = 0; i < questions.size(); i++) {
            Assessment q = questions.get(i);

            int answer = JOptionPane.showOptionDialog(
                null,
                "[" + q.getQuestionType() + "]\n" + q.getQuestion(),
                "Quiz Question " + (i + 1),
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                q.getOptions(),
                null
            );

            if (answer == JOptionPane.CLOSED_OPTION) {
                JOptionPane.showMessageDialog(
                    null,
                    "Quiz cancelled. Your score will not be updated.",
                    "Quiz Cancelled",
                    JOptionPane.INFORMATION_MESSAGE
                );
                totalScore = score;
                return;
            }

            if (answer == q.getCorrectAnswer()) {
                correctCount++;
                JOptionPane.showMessageDialog(
                    null,
                    "Correct! You earned points.",
                    "Feedback",
                    JOptionPane.INFORMATION_MESSAGE
                );
            } else {
                JOptionPane.showMessageDialog(
                    null,
                    "Incorrect.\nThe correct answer is: " + q.getOptions()[q.getCorrectAnswer()],
                    "Feedback",
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
        }

        totalScore = (correctCount * 100) / questions.size();
        int pointsEarned = totalScore;
        double percentage = ((double) correctCount / questions.size()) * 100;

        String stars;
        if (totalScore >= 80) {
            stars = "⭐⭐⭐";
        } else if (totalScore >= 50) {
            stars = "⭐⭐";
        } else {
            stars = "⭐";
        }

        GamificationEngine engine = new GamificationEngine();

        String badge = engine.determineBadge(totalScore);
        String message = engine.getMotivationalMessage(totalScore);
        int pointsToNextBadge = engine.calculatePointsToNextBadge(totalScore);

        String resultMessage =
            "Quiz Completed!\n\n" +
            "Name: " + name + "\n" +
            "Correct Answers: " + correctCount + "/" + questions.size() + "\n" +
            "Percentage: " + String.format("%.2f", percentage) + "%\n" +
            "Final Score: " + totalScore + "/100\n" +
            "Points Earned: " + pointsEarned + "\n" +
            "Stars Earned: " + stars + "\n" +
            "Badge: " + badge + "\n\n" +
            message + "\n";

        if (pointsToNextBadge > 0) {
            resultMessage += "You need " + pointsToNextBadge + " more points to reach the next badge.";
        } else {
            resultMessage += "You have reached the highest badge!";
        }

        JOptionPane.showMessageDialog(
            null,
            resultMessage,
            "Quiz Result",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    public int calculateScore() {
        return totalScore;
    }
}
