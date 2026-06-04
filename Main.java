/*
Contributed by Andrean (103325)
Role: Member 1 - Data and Storage Lead

Description: 
1. Main.java is the entry point of the program. 
2. It handles user login, displays the main menu, and manages user interactions
*/

// import java.util.Scanner;
// import java.util.logging.FileHandler;
import javax.swing.JOptionPane;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        //Variables to store user data
        String name;
        int score = 0; //Default score for new users

        // Display welcome message for users
        JOptionPane.showMessageDialog(null, "Welcome to eWaste Education and Awareness Site!");

        //User input name to begin the program
        name = JOptionPane.showInputDialog("Please enter your username to begin the program:");

       //Call all users method to load user data
        UserAccess userAccess = new UserFileAccess(); // Instance of UserFileAccess to access user data
        ArrayList<User> users = userAccess.getAllUsers(); // Load all users from file

        // Trackers to see if user exists or not
            boolean userExists = false; 
            User existingUser = null; 

        //To check if user exists
        for (User u : users) {
            if (u.getName().equalsIgnoreCase(name) == true) { // If user exists, welcome back and show current score
                userExists = true;
                existingUser = u;
                break; // Exit the loop if user is found
            }
        }

        if (userExists) { //If user exists
            JOptionPane.showMessageDialog(null, "Welcome back, " + name + "! Your current score is: " + existingUser.getScore());
            UserOptions userOptions = new UserOptions();
            userOptions.choice(name, existingUser.getScore()); // Call user options menu
        } 
        else { //If user does not exist, create new user
            JOptionPane.showMessageDialog(null, "Welcome, " + name + "! Let's begin your eWaste education journey!");
            User newUser = new User(name, score);
            users.add(newUser); // Add new user to the 'users' list
            userAccess.saveAllUsers(users);
            UserOptions userOptions = new UserOptions();
            userOptions.choice(name, score); // Call user options menu
        }
    }
}
