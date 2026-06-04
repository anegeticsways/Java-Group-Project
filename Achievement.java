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

public class Achievement extends JFrame implements ModuleAction {

    private UserFileAccess fileAccess = new UserFileAccess();
    private String userName;
    private int userScore;

    public Achievement(String name, int score) {
        super("Achievements");
        this.userName = name;
        this.userScore = score;

        setSize(AppConfig.PHONE_WIDTH, AppConfig.PHONE_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        openModule();
    }

    @Override
    public void openModule() {
        JPanel panel = new JPanel();
        panel.setBackground(AppConfig.BG);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(35, 20, 20, 20));

        JLabel title = new JLabel("Achievements");
        title.setFont(AppConfig.TITLE_FONT);
        title.setForeground(AppConfig.PRIMARY);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextPane achievementPane = new JTextPane();
        achievementPane.setContentType("text/html");
        achievementPane.setEditable(false);
        achievementPane.setBackground(Color.WHITE);
        achievementPane.setText(getAchievementText());

        JScrollPane scrollPane = new JScrollPane(achievementPane);
        scrollPane.setPreferredSize(new Dimension(330, 470));
        scrollPane.setMaximumSize(new Dimension(330, 470));

        JButton backBtn = new JButton("Back");
        backBtn.setFont(AppConfig.BUTTON_FONT);
        backBtn.setFocusPainted(false);
        backBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        backBtn.addActionListener(e -> {
            dispose();
            new UserOptions(userName, userScore);
        });

        panel.add(title);
        panel.add(Box.createVerticalStrut(25));
        panel.add(scrollPane);
        panel.add(Box.createVerticalStrut(20));
        panel.add(backBtn);

        add(panel);
        setVisible(true);
    }

    private String getAchievementText() {
        ArrayList<User> users = fileAccess.loadUsers();
        users.sort((u1, u2) -> u2.getScore() - u1.getScore());

        GamificationEngine engine = new GamificationEngine();

        StringBuilder sb = new StringBuilder();
        sb.append("<html><body style='font-family:Segoe UI; font-size:12px;'>");

        for (User u : users) {
            sb.append("<div style='padding:10px; margin-bottom:10px; border:1px solid #b4dcc0;'>");
            sb.append("<b>User:</b> ").append(u.getName()).append("<br>");
            sb.append("<b>Total Score:</b> ").append(u.getScore()).append("/100<br>");
            sb.append("<b>Performance:</b> ").append(engine.getRubricMessage(u.getScore())).append("<br>");
            sb.append("<b>Badge:</b> ").append(engine.determineBadge(u.getScore())).append("<br><br>");
            sb.append(engine.getMotivationalMessage(u.getScore())).append("<br>");

            int next = engine.calculatePointsToNextBadge(u.getScore());

            if (next > 0) {
                sb.append("<i>Need ").append(next).append(" more points for next badge.</i>");
            } else {
                sb.append("<i>Highest badge achieved!</i>");
            }

            sb.append("</div>");
        }

        sb.append("</body></html>");
        return sb.toString();
    }
}
