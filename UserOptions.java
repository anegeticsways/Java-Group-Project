/*
Contributed by Andrean (103325)
Role: Member 1 - Data and Storage Lead
Tester: Andrean (103325)

Description:
1. UserOptions.java manages the user options menu after enter to system
2. It handles user interactions for viewing learning content, taking quizzes, viewing score board, and exiting the program.
3. It also updates the user's score after taking quizzes and saves the updated data to file
4. Serves as bridge between Main.java and other classes
5. If user still wanted to stay logged in the system, they can return to options menu after each activity instead of exiting the program
*/

import javax.swing.JOptionPane;
import javax.swing.UIManager;

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

        UIManager.put("OptionPane.background", AppConfig.BG);
        UIManager.put("Panel.background", AppConfig.BG);

        String[] options = {
            "📚 Learning Content",
            "📝 Take Quiz",
            "🏆 Score Board",
            "🚪 Exit"
        };

        int option = JOptionPane.showOptionDialog(
                null,
                "Welcome, " + name + "!\n\nChoose an option:",
                "EcoLearn Menu",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]);

        if (option == 0) {

            LearningModule learningModule = new LearningModule();
            learningModule.startLearning();

            this.choice(name, score);

        } else if (option == 1) {

            new Quiz(name, score);

        } else if (option == 2) {

            Achievement achievement = new Achievement();
            achievement.displayAchievements();

            this.choice(name, score);

        } else if (option == 3 || option == JOptionPane.CLOSED_OPTION) {

            JOptionPane.showMessageDialog(
                    null,
                    "Thank you for using EcoLearn!\n\nKeep supporting responsible e-waste management.");

            System.exit(0);

        } else {

            JOptionPane.showMessageDialog(
                    null,
                    "Invalid option. Please try again.");

            this.choice(name, score);
        }
    }
}
