/*
Contributed by Andrean (103325)
Role: Member 1 - Data and Storage Lead

Description: 
1. Main.java is the entry point of the program. 
2. It handles user login, displays the main menu, and manages user interactions
*/

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Main extends JFrame {

    private JTextField nameField; // UI component for user to enter their name
    private UserFileAccess fileAccess = new UserFileAccess(); // Handles loading and saving user data

    public Main() {
        // Set up the main window properties
        setTitle("EcoLearn");
        setSize(AppConfig.PHONE_WIDTH, AppConfig.PHONE_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Show the welcome screen with app information
        JPanel panel = new JPanel();
        panel.setBackground(AppConfig.BG);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(80, 25, 25, 25));

        // Create and style the title and subtitle labels
        JLabel title = new JLabel("EcoLearn");
        title.setFont(new Font("Segoe UI", Font.BOLD, 30));
        title.setForeground(AppConfig.PRIMARY);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Subtitle with app description
        JLabel subtitle = new JLabel("<html><div style='text-align:center;'>E-Waste Education<br>and Awareness App</div></html>");
subtitle.setFont(AppConfig.NORMAL_FONT);
subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
subtitle.setHorizontalAlignment(SwingConstants.CENTER);

        // Create and style the username input field
        nameField = new JTextField();
        nameField.setMaximumSize(new Dimension(300, 40));
        nameField.setFont(AppConfig.NORMAL_FONT);
        nameField.setBorder(BorderFactory.createTitledBorder("Enter username"));

        // Create and style the start button
        JButton startBtn = new JButton("Start");
        startBtn.setFont(AppConfig.BUTTON_FONT);
        startBtn.setMaximumSize(new Dimension(300, 45));
        startBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        startBtn.addActionListener(e -> login());

        // Add components to the panel with spacing
        panel.add(title);
        panel.add(Box.createVerticalStrut(20));
        panel.add(subtitle);
        panel.add(Box.createVerticalStrut(50));
        panel.add(nameField);
        panel.add(Box.createVerticalStrut(25));
        panel.add(startBtn);

        add(panel);
        setVisible(true); // Show the main window
    }

    // Handle user login
    private void login() {
        String name = nameField.getText().trim();

        // Validate that the username is not empty
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your username.");
            return;
        }

        // Load existing users and check if the entered username already exists
        ArrayList<User> users = fileAccess.loadUsers();
        User existingUser = null;

        // Search for the user in the loaded list
        for (User u : users) {
            if (u.getName().equalsIgnoreCase(name)) {
                existingUser = u;
                break;
            }
        }

        int score;

        // If the user exists, retrieve their score; otherwise, create a new user with a score of 0
        if (existingUser != null) {
            score = existingUser.getScore();
            JOptionPane.showMessageDialog(this,
                    "Welcome back, " + name + "!\nCurrent score: " + score);
        } else { // New user - create and save to file
            score = 0;
            users.add(new User(name, score));
            fileAccess.saveAllUsers(users);
            JOptionPane.showMessageDialog(this,
                    "Welcome, " + name + "!\nLet's begin your e-waste journey.");
        }

        dispose(); // Close the login window
        new UserOptions(name, score); // Open the user options menu with the user's name and score
    }
    public static void main(String[] args) {
        new Main();
    }
}
