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

import javax.swing.*;
import java.awt.*;

public class UserOptions extends ModuleBase {

    // Constructor to initialize the user options menu with the user's name and score
    public UserOptions(String name, int score) {
        super(name, score);
        openModule();
    }

    // Method to display the user options menu and handle user interactions
    @Override
    public void openModule() { // Display the user options menu with buttons for different activities
        JPanel panel = new JPanel();
        panel.setBackground(AppConfig.BG);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(320, 450));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 25, 30, 25));

        JLabel title = new JLabel("Main Menu");
        title.setFont(AppConfig.TITLE_FONT);
        title.setForeground(AppConfig.PRIMARY);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

       JLabel userInfo = new JLabel("<html><div style='text-align:center;'>User: " + name + "<br>Score: " + score + "/100</div></html>");
userInfo.setFont(AppConfig.NORMAL_FONT);
userInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
userInfo.setHorizontalAlignment(SwingConstants.CENTER);
        
        JButton learningBtn = createButton("View Learning Content"); // Button to view learning content
        JButton quizBtn = createButton("Take Quiz"); // Button to take the quiz
        JButton achievementBtn = createButton("View Achievements"); // Button to view achievements and score board
        JButton exitBtn = createButton("Exit"); // Button to exit the program

        // Action listeners for each button to handle user interactions and navigate to the respective modules
        learningBtn.addActionListener(e -> { // Navigate to the learning module when the "View Learning Content" button is clicked
            Window w = SwingUtilities.getWindowAncestor(panel);
            if (w != null) w.dispose();

            LearningModule learningModule = new LearningModule();
            learningModule.openModule();

            new UserOptions(name, score);
        });

        quizBtn.addActionListener(e -> { // Navigate to the quiz module when the "Take Quiz" button is clicked
            Window w = SwingUtilities.getWindowAncestor(panel);
            if (w != null) w.dispose();

            new Quiz(name, score);
        });

        achievementBtn.addActionListener(e -> { // Navigate to the achievement module when the "View Achievements" button is clicked 
            Window w = SwingUtilities.getWindowAncestor(panel);
            if (w != null) w.dispose();

            new Achievement(name, score);
        });

        exitBtn.addActionListener(e -> System.exit(0)); // Exit the program when the "Exit" button is clicked

        // Add components to the panel and display the user options menu
        panel.add(title);
        panel.add(Box.createVerticalStrut(20));
        panel.add(userInfo);
        panel.add(Box.createVerticalStrut(35));
        panel.add(learningBtn);
        panel.add(Box.createVerticalStrut(12));
        panel.add(quizBtn);
        panel.add(Box.createVerticalStrut(12));
        panel.add(achievementBtn);
        panel.add(Box.createVerticalStrut(12));
        panel.add(exitBtn);

        // Display the user options menu in a dialog
        JOptionPane.showOptionDialog(null, panel, "EcoLearn Menu", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{}, null);
    }

    // Helper method to create buttons with consistent styling
    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(AppConfig.BUTTON_FONT);
        btn.setMaximumSize(new Dimension(260, 40));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setFocusPainted(false);
        return btn;
    }
}
