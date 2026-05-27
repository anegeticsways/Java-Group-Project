/*
Contributed by Andrean (103325)
Description:
1. Quiz.java is responsible for managing the quiz functionality of the program
2. It displays quiz questions to users and calculates scores based on user answers
3. Involves data storage for quiz questions and answers, and calculation logic
4. [NOT YET] Include elements like timers, marks/points earned and stars
*/

/*
Thoughts & Comments:
1. Since we needed to accommodate 20+ quiz questions, better if we can store correct answers selection in an array or list to make it easier to manage and calculate scores instead of hardcoding each question's correct answer in the code
2. We can also consider adding more features such as providing feedback for each question or showing correct
3. Can include warning message for user that score will reset everytime retakes the quiz to ensure score is updated correctly (within 0-100 marks)
*/

/*
Proper marks calculation logic:

n = number of questions
fullmark = 100
marks per question = fullmark / n

Example: 
n = 25 questions
fullmark = 100
marks per question = 100 / 25 = 4 marks per question

*/

import javax.swing.JOptionPane;

public class Quiz {

    int totalScore;
    int tempScore;
    
    public Quiz() {
        // Constructor for Quiz class
    }

    //Quiz questions will be displayed here
    public void displayQuiz(String name, int score) {
        // Example quiz question
        String question1 = "What is eWaste?";
        String[] options1 = {"A. Electronic waste", "B. A type of recycling", "C. A new technology", "D. None of the above"};
        int answer1 = JOptionPane.showOptionDialog(null, question1, "Quiz Question 1", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options1, options1[0]);
        // Check if answer is correct and update score
        if (answer1 == 0) { // Assuming option A is correct
            tempScore += 10; // Add points for correct answer
            //calculateScore(); // Update total score
        }

        String question2 = "Why is it important to recycle eWaste?";
        String[] options2 = {"A. To reduce environmental pollution", "B. To save resources", "C. To prevent health hazards", "D. All of the above"};
        int answer2 = JOptionPane.showOptionDialog(null, question2, "Quiz Question 2", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options2, options2[0]);
        // Check if answer is correct and update score
        if (answer2 == 3) { // Assuming option D is correct
            tempScore += 10; // Add points for correct answer
            //calculateScore(); // Update total score
        }

        JOptionPane.showMessageDialog(null, "Your current score is: " + calculateScore(), "Quiz Result", JOptionPane.INFORMATION_MESSAGE);

        // ... Additional quiz questions can be added here following the same format
    }

    public int calculateScore() {
        totalScore = tempScore; // Update total score based on quiz answers
        return totalScore; // Return the total score to be updated in UserOptions.java
    }
}
