/*
Contributed by Andrean (103325)
Description:
1. UserOptions.java manages the user options menu after enter to system
2. It handles user interactions for viewing learning content, taking quizzes, viewing score board, and exiting the program.
3. It also updates the user's score after taking quizzes and saves the updated data to file
4. Serves as bridge between Main.java and other classes
5. If user still wanted to stay logged in the system, they can return to options menu after each activity instead of exiting the program
*/

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class UserOptions {

    private ArrayList<User> users; // List to hold user data for updating scores

    public UserOptions() {
        this.users = User.loadUsers(); // Load users to access and update user data in this class
    }

    public void choice(String name, int score) {
        //User Options [PARTIALLY COMPLETED - NEED TO LINK TO OTHER CLASSES]
        String optionInput = JOptionPane.showInputDialog("Please select an option:\n1. View Learning Content\n2. Take Quiz\n3. View Score Board\n4. Exit");
        int option = Integer.parseInt(optionInput);

        if (option == 1) { // User views learning content - LearningContent.java
            // Display learning content - LearningContent.java
           LearningModule learningModule = new LearningModule();
            learningModule.startLearning(); // This method should be implemented in LearningContent class to show the content
        // Return to options menu after learning content
        this.choice(name, score);
        } 
        else if (option == 2) { // User takes quiz - Quiz.java
            Quiz quiz = new Quiz();
            quiz.displayQuiz(name, score); //Display quiz questions
            int latestScore = quiz.calculateScore(); // Calculate score from quiz
            // Assuming 'users' is accessible in this scope
            for (User u : users) {
                if (u.getName().equalsIgnoreCase(name)) {
                    u.setScore(latestScore); // update score
                    User.saveAllUsers(users); // save the updated user data to file
                    break;
                }
            }

            // Return to options menu after answering quiz
            this.choice(name, latestScore);
        } 
        else if (option == 3) { //User views score board - Achievement.java
            // View score - Achievements.java
            Achievement achievement = new Achievement();
            achievement.displayAchievements(); // Display all users score
        // Return to options menu after viewing scores
        this.choice(name, score);
        } 
        else if (option == 4) { // User exits the program
            // Exit program
            JOptionPane.showMessageDialog(null, "Thank you for using the eWaste Education and Awareness Site! Goodbye!");
            System.exit(0);
        } 
        else { // Invalid option handling
            JOptionPane.showMessageDialog(null, "Invalid option. Please try again.");
            this.choice(name, score); // Restart the program to try again
        }
    }
}
