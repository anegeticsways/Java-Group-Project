/*
Contributed by Andrean (103325)
Role: Member 1 - Data and Storage Lead

Description:
1. UserOptions.java manages the user options menu after enter to system
2. It handles user interactions for viewing learning content, taking quizzes, viewing score board, and exiting the program.
3. It also updates the user's score after taking quizzes and saves the updated data to file
4. Serves as bridge between Main.java and other classes
5. If user still wanted to stay logged in the system, they can return to options menu after each activity instead of exiting the program
*/

import javax.swing.JOptionPane;

public class UserOptions {

    private UserAccess userAccess;

    public UserOptions() {
        this.userAccess = new UserFileAccess();
    }

    public UserOptions(String name, int score) {
        this.userAccess = new UserFileAccess();
        choice(name, score);
    }

    public void choice(String name, int score) {

        String optionInput = JOptionPane.showInputDialog(
                "Please select an option:\n"
                + "1. View Learning Content\n"
                + "2. Take Quiz\n"
                + "3. View Score Board\n"
                + "4. Exit");

        int option = Integer.parseInt(optionInput);

        if (option == 1) {

            LearningModule learningModule = new LearningModule();
            learningModule.startLearning();

            this.choice(name, score);

        } else if (option == 2) {

            new Quiz(name, score);

        } else if (option == 3) {

            Achievement achievement = new Achievement();
            achievement.displayAchievements();

            this.choice(name, score);

        } else if (option == 4) {

            JOptionPane.showMessageDialog(
                    null,
                    "Thank you for using the eWaste Education and Awareness Site! Goodbye!");

            System.exit(0);

        } else {

            JOptionPane.showMessageDialog(
                    null,
                    "Invalid option. Please try again.");

            this.choice(name, score);
        }
    }
}
