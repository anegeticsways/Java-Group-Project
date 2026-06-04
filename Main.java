/*
Contributed by Andrean (103325)
Role: Member 1 - Data and Storage Lead

Description: 
1. Main.java is the entry point of the program. 
2. It handles user login, displays the main menu, and manages user interactions
*/

// import java.util.Scanner;
// import java.util.logging.FileHandler;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Main extends JFrame {

    private JTextField nameField;
    private UserFileAccess fileAccess = new UserFileAccess();

    public Main() {
        setTitle("EcoLearn");
        setSize(AppConfig.PHONE_WIDTH, AppConfig.PHONE_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setBackground(AppConfig.BG);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(80, 25, 25, 25));

        JLabel title = new JLabel("EcoLearn");
        title.setFont(new Font("Segoe UI", Font.BOLD, 30));
        title.setForeground(AppConfig.PRIMARY);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel("<html><center>E-Waste Education<br>and Awareness App</center></html>");
        subtitle.setFont(AppConfig.NORMAL_FONT);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        nameField = new JTextField();
        nameField.setMaximumSize(new Dimension(300, 40));
        nameField.setFont(AppConfig.NORMAL_FONT);
        nameField.setBorder(BorderFactory.createTitledBorder("Enter username"));

        JButton startBtn = new JButton("Start");
        startBtn.setFont(AppConfig.BUTTON_FONT);
        startBtn.setMaximumSize(new Dimension(300, 45));
        startBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        startBtn.addActionListener(e -> login());

        panel.add(title);
        panel.add(Box.createVerticalStrut(20));
        panel.add(subtitle);
        panel.add(Box.createVerticalStrut(50));
        panel.add(nameField);
        panel.add(Box.createVerticalStrut(25));
        panel.add(startBtn);

        add(panel);
        setVisible(true);
    }

    private void login() {
        String name = nameField.getText().trim();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your username.");
            return;
        }

        ArrayList<User> users = fileAccess.loadUsers();
        User existingUser = null;

        for (User u : users) {
            if (u.getName().equalsIgnoreCase(name)) {
                existingUser = u;
                break;
            }
        }

        int score;

        if (existingUser != null) {
            score = existingUser.getScore();
            JOptionPane.showMessageDialog(this,
                    "Welcome back, " + name + "!\nCurrent score: " + score);
        } else {
            score = 0;
            users.add(new User(name, score));
            fileAccess.saveAllUsers(users);
            JOptionPane.showMessageDialog(this,
                    "Welcome, " + name + "!\nLet's begin your e-waste journey.");
        }

        dispose();
        new UserOptions(name, score);
    }

    public static void main(String[] args) {
        new Main();
    }
}
