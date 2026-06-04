/*
Created by: Andrean (103325)
Role: Member 1 - Data and Storage Lead

Tested by: Ainin (102810)

Description:
1. Displays all users' scores.
2. Sorts users from highest score to lowest score.
3. Shows badge/title earned using GamificationEngine.
*/

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Achievement extends ModuleBase {

    private UserFileAccess fileAccess = new UserFileAccess();

    public Achievement(String name, int score) {
        super("Achievements", name, score);
        openModule();
    }

    @Override
    public void openModule() {
        JPanel panel = new JPanel();
        panel.setBackground(AppConfig.BG);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 20, 20, 20));

        JLabel title = new JLabel("Achievements");
        title.setFont(AppConfig.TITLE_FONT);
        title.setForeground(AppConfig.PRIMARY);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextArea achievementArea = new JTextArea();
        achievementArea.setFont(AppConfig.NORMAL_FONT);
        achievementArea.setEditable(false);
        achievementArea.setLineWrap(true);
        achievementArea.setWrapStyleWord(true);
        achievementArea.setText(getAchievementText());

        JScrollPane scrollPane = new JScrollPane(achievementArea);
        scrollPane.setPreferredSize(new Dimension(320, 450));

        JButton backBtn = new JButton("Back");
        backBtn.setFont(AppConfig.BUTTON_FONT);
        backBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        backBtn.addActionListener(e -> {
            dispose();
            new UserOptions(name, score);
        });

        panel.add(title);
        panel.add(Box.createVerticalStrut(20));
        panel.add(scrollPane);
        panel.add(Box.createVerticalStrut(20));
        panel.add(backBtn);

        add(panel);
        setVisible(true);
    }

    private String getAchievementText() {
        ArrayList<User> users = fileAccess.loadUsers();
        GamificationEngine engine = new GamificationEngine();

        StringBuilder sb = new StringBuilder();

        for (User u : users) {
            sb.append("User: ").append(u.getName()).append("\n");
            sb.append("Total Score: ").append(u.getScore()).append("/100\n");
            sb.append("Performance: ").append(engine.getRubricMessage(u.getScore())).append("\n");
            sb.append("Badge: ").append(engine.determineBadge(u.getScore())).append("\n");
            sb.append(engine.getMotivationalMessage(u.getScore())).append("\n");

            int next = engine.calculatePointsToNextBadge(u.getScore());

            if (next > 0) {
                sb.append("Need ").append(next).append(" more points for next badge.\n");
            } else {
                sb.append("Highest badge achieved!\n");
            }

            sb.append("\n-------------------------\n\n");
        }

        return sb.toString();
    }
}
