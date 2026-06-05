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

    // Define the file path for data persistence
    private static final String FILE_PATH = "userdata.txt";

    @Override
    public ArrayList<User> loadUsers() {
        ArrayList<User> users = new ArrayList<>();

        try {
            File file = new File(FILE_PATH);

            // Create the file if it does not exist on the first run
            if (!file.exists()) {
                file.createNewFile();
            }

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            // Read the file line by line
            while ((line = br.readLine()) != null) {
                line = line.trim();

                // Skip any blank lines in the text file
                if (line.isEmpty()) {
                    continue;
                }

                // Split the line into name and score using the comma delimiter
                String[] data = line.split(",");

                if (data.length == 2) {
                    String name = data[0];
                    int score = Integer.parseInt(data[1]);
                    users.add(new User(name, score)); // Add parsed data as a new User object
                }
            }

            br.close();

        } catch (IOException | NumberFormatException e) {
            // Display an error to the user if the file cannot be read or parsed
            JOptionPane.showMessageDialog(null, "Error loading user data.");
        }

        return users; // Return the list of loaded users
    }

    @Override
    public void saveAllUsers(ArrayList<User> users) {
        try {
            // Open FileWriter (this overwrites the existing file)
            FileWriter fw = new FileWriter(FILE_PATH);

            // Loop through all users and write them to the file in "name,score" format
            for (User u : users) {
                fw.write(u.getName() + "," + u.getScore() + "\n");
            }

            fw.close();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving user data.");
        }
    }

    @Override
    public void updateUserScore(String name, int score) {
        // Load the current list of users
        ArrayList<User> users = loadUsers();

        // Search for the specific user by name and update their score
        for (User u : users) {
            if (u.getName().equalsIgnoreCase(name)) {
                u.setScore(score);
                break; // Stop searching once the user is found
            }
        }

        // Save the updated list back to the text file
        saveAllUsers(users);
    }
}
