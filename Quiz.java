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
import java.util.ArrayList; //contributed by Ainin (102810)

public class Quiz {

    private ArrayList<Assessment> questions;
    private int totalScore;
    
    public Quiz() { // Constructor for Quiz class
        questions = new ArrayList<>();
        loadQuestions();
    }

    //Store all quiz question here
    public void loadQuestions() {

        questions.add(new Assessment(
            "What is e-waste?",
            new String[] {"Electronic waste", "Food waste", "Plastic waste", "Paper waste"},
            0
        ));

        questions.add(new Assessment(
            "Which item is an example of e-waste?",
            new String[]{"Old smartphone", "Banana peel", "Glass bottle", "Newspaper"},
            0
        ));

         questions.add(new Assessment(
            "Which SDG is related to responsible consumption and production?",
            new String[]{"SDG 3", "SDG 6", "SDG 12", "SDG 15"},
            2
        ));

        questions.add(new Assessment(
            "Which material can be found in e-waste?",
            new String[]{"Lead", "Sugar", "Cotton", "Wood"},
            0
        ));

         questions.add(new Assessment(
            "True or False: E-waste may contain toxic materials.",
            new String[]{"True", "False"},
            0
        ));

        questions.add(new Assessment(
            "Improper e-waste disposal can pollute:",
            new String[]{"Soil and water", "Only books", "Only clothes", "Nothing"},
            0
        ));

         questions.add(new Assessment(
            "Which is a responsible way to dispose of e-waste?",
            new String[]{"Use certified recycling centres", "Throw into river", "Burn it", "Mix with food waste"},
            0
        ));

        questions.add(new Assessment(
            "True or False: Recycling e-waste helps reduce pollution.",
            new String[]{"True", "False"},
            0
        ));

        questions.add(new Assessment(
            "Which device is considered e-waste when discarded?",
            new String[]{"Laptop", "Plastic spoon", "Paper bag", "Food wrapper"},
            0
        ));

        questions.add(new Assessment(
            "Why should we repair electronics?",
            new String[]{"To reduce e-waste", "To waste money", "To increase pollution", "To damage devices"},
            0
        ));

         questions.add(new Assessment(
            "True or False: Batteries can be part of e-waste.",
            new String[]{"True", "False"},
            0
        ));

         questions.add(new Assessment(
        "What is the main purpose of e-waste recycling centres?",
        new String[]{"Safely process discarded electronics", "Sell unhealthy food", "Burn electronics openly", "Store plastic bottles only"},
        0
        ));

        questions.add(new Assessment(
            "What can toxic chemicals from e-waste affect?",
            new String[]{"Human health", "Only furniture", "Only books", "Nothing"},
            0
        ));

        questions.add(new Assessment(
            "Which is better before buying a new device?",
            new String[]{"Check if old device can be repaired", "Throw old device immediately", "Burn old device", "Hide old device"},
            0
        ));

        questions.add(new Assessment(
            "True or False: Donating working electronics can reduce e-waste.",
            new String[]{"True", "False"},
            0
        ));

        questions.add(new Assessment(
            "Which of these is NOT e-waste?",
            new String[]{"Apple peel", "Printer", "Tablet", "Charger"},
            0
        ));

        questions.add(new Assessment(
        "Why is informal burning of e-waste dangerous?",
        new String[]{"It releases toxic gases", "It creates clean energy", "It improves air quality", "It reduces pollution"},
        0
        ));

       questions.add(new Assessment(
        "Which valuable material can be recovered from recycled electronic devices?",
        new String[]{"Gold", "Plastic straws", "Rubber bands", "Paper clips"},
        0
        ));

        questions.add(new Assessment(
        "True or False: Throwing electronics into regular rubbish bins is recommended.",
        new String[]{"True", "False"},
        1
        ));

        questions.add(new Assessment(
        "True or False: E-waste recycling is unnecessary because electronics naturally disappear over time.",
        new String[]{"True", "False"},
        1
        ));
        
    }

    public void displayQuiz(String name, int score) {
        int correctCount = 0;

        //Warning message
        JOptionPane.showMessageDialog(
            null,
            "Warning: Your score will reset if you retake the quiz.",
            JOptionPane.WARNING_MESSAGE
        );

        //Loop through questions
        for (int i = 0; i < questions.size(); i++) {
            Assessment q = questions.get(i);

            int answer = JOptionPane.showOptionDialog(
                null,
                q.getQuestion(),
                "Quiz Question" + (i + 1),
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                q.getOptions(),
                q.getOptions()[0]
            );

            //Check answer
            if (answer == q.getCorrectAnswer()) {

                correctCount++;

                JOptionPane.showMessageDialog(
                    null,
                    "Correct! You earned points."
                );

            } else {

                JOptionPane.showMessageDialog(
                    null,
                    "Incorrect. \nThe correct answer is:" + q.getOptions()[q.getCorrectAnswer()]
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
        }

        //Score calculation
        totalScore = (correctCount * 100) / questions.size();

               int pointsEarned = totalScore;

        String stars;
        if (totalScore >= 80) {
            stars = "⭐⭐⭐";
        } else if (totalScore >= 50) {
            stars = "⭐⭐";
        } else {
            stars = "⭐";
        }
        
        //Contributed by Afiq
        GamificationEngine engine = new GamificationEngine();

        String badge = engine.determineBadge(totalScore);
        String message = engine.getMotivationalMessage(totalScore);
        int pointsToNextBadge = engine.calculatePointsToNextBadge(totalScore);

        String resultMessage =
            "Quiz Completed!\n\n" +
            "Name: " + name + "\n" +
            "Correct Answers: " + correctCount + "/" + questions.size() + "\n" +
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
        
