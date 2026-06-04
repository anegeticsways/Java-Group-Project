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

    public UserOptions(String name, int score) {
        super("EcoLearn Menu", name, score);
        openModule();
    }

    @Override
    public void openModule() {
        JPanel panel = new JPanel();
        panel.setBackground(AppConfig.BG);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 25, 25, 25));

        JLabel title = new JLabel("Main Menu");
        title.setFont(AppConfig.TITLE_FONT);
        title.setForeground(AppConfig.PRIMARY);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel userInfo = new JLabel("<html><center>User: " + name + "<br>Score: " + score + "</center></html>");
        userInfo.setFont(AppConfig.NORMAL_FONT);
        userInfo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton learningBtn = createButton("View Learning Content");
        JButton quizBtn = createButton("Take Quiz");
        JButton achievementBtn = createButton("View Achievements");
        JButton exitBtn = createButton("Exit");

        learningBtn.addActionListener(e -> {
            dispose();
            new LearningModule(name, score);
        });

        quizBtn.addActionListener(e -> {
            dispose();
            new Quiz(name, score);
        });

        achievementBtn.addActionListener(e -> {
            dispose();
            new Achievement(name, score);
        });

        exitBtn.addActionListener(e -> System.exit(0));

        panel.add(title);
        panel.add(Box.createVerticalStrut(20));
        panel.add(userInfo);
        panel.add(Box.createVerticalStrut(50));
        panel.add(learningBtn);
        panel.add(Box.createVerticalStrut(15));
        panel.add(quizBtn);
        panel.add(Box.createVerticalStrut(15));
        panel.add(achievementBtn);
        panel.add(Box.createVerticalStrut(15));
        panel.add(exitBtn);

        add(panel);
        setVisible(true);
    }

    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(AppConfig.BUTTON_FONT);
        btn.setMaximumSize(new Dimension(300, 45));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        return btn;
    }
}
