/*
Contributed by Andrean (103325)
Role: Member 1 - Data and Storage Lead

Description: 
1. Manages user data by reading from and writing to a file (userdata.txt).
2. Implements the UserAccess interface to provide methods for retrieving all users, saving users, and updating user scores.
3. Ensures that user data is persistent across sessions by storing it in a text file.

Tested by: Ainin (102810)
*/

import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class UserFileAccess implements UserAccess {

    private static final String FILE_PATH = "userdata.txt"; // Path to the file where user data is stored

    // Method to load user data from the file, returning an ArrayList of User objects
    @Override
    public ArrayList<User> loadUsers() {
        ArrayList<User> users = new ArrayList<>(); // Initialize an empty ArrayList to hold the User objects

        // Try to read the user data from the file, creating User objects for each line of data and adding them to the users list
        try {
            File file = new File(FILE_PATH);

            // If the file does not exist, create a new file to store user data
            if (!file.exists()) {
                file.createNewFile();
            }

            // Use a BufferedReader to read the file line by line, parsing each line to create User objects and adding them to the users list
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            //  Read each line of the file
            while ((line = br.readLine()) != null) {
                line = line.trim(); // Remove leading and trailing whitespace from the line

                if (line.isEmpty()) {
                    continue; // Skip empty lines to avoid processing invalid data
                }

                String[] data = line.split(","); // Split the line into parts using a comma as the delimiter, expecting the format "name,score"

                // Check if the line contains exactly two parts 
                if (data.length == 2) {
                    String name = data[0]; // The first part is the user's name
                    int score = Integer.parseInt(data[1]); // The second part is the user's score, which is parsed from a string to an integer
                    users.add(new User(name, score)); // Create a new User object with the parsed name and score, and add it to the users list
                }
            }

            br.close(); // Close the BufferedReader to free up system resources

        } catch (IOException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error loading user data."); // Show an error message if there is an issue with reading the file or parsing the score
        }

        return users;
    }

    // Method to save user data to the file, taking an ArrayList of User objects as input and writing each user's name and score to the file in the format "name,score"
    @Override
    public void saveAllUsers(ArrayList<User> users) {
        try {
            FileWriter fw = new FileWriter(FILE_PATH); // Create a FileWriter to write to the specified file path, which will overwrite the existing file content

            // Write each user's name and score to the file, separating them with a comma and adding a newline after each user
            for (User u : users) {
                fw.write(u.getName() + "," + u.getScore() + "\n");
            }

            fw.close();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving user data."); // Show an error message if there is an issue with writing to the file
        }
    }

    // Method to update a specific user's score
    @Override
    public void updateUserScore(String name, int score) {
        ArrayList<User> users = loadUsers(); // Load the existing users from the file to get the current list of users

        // Iterate through the list of users to find the user with the specified name, and update their score if found
        for (User u : users) {
            if (u.getName().equalsIgnoreCase(name)) {
                u.setScore(score);
                break;
            }
        }

        saveAllUsers(users); // Save the updated list of users back to the file to ensure that the changes are persisted
    }
}
