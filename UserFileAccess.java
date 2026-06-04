/*
Contributed by Andrean (103325)
Role: Member 1 - Data and Storage Lead

Description: 
1. Manages user data by reading from and writing to a file (userdata.txt).
2. Implements the UserAccess interface to provide methods for retrieving all users, saving users, and updating user scores.
3. Ensures that user data is persistent across sessions by storing it in a text file.
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class UserFileAccess implements UserAccess {
    private static final String FILE_PATH = "userdata.txt";

    @Override // Method to retrieve all users from userdata.txt
    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue; // Skip empty lines
                
                String[] data = line.split(",");
                if (data.length == 2) {
                    users.add(new User(data[0], Integer.parseInt(data[1])));
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading users: " + e.getMessage());
        }
        return users;
    }

    @Override // Method to save all users to userdata.txt
    public void saveAllUsers(ArrayList<User> users) {
        try (FileWriter fw = new FileWriter(FILE_PATH)) {
            for (User u : users) {
                fw.write(u.getName() + "," + u.getScore() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error saving users: " + e.getMessage());
        }
    }

    @Override // Method to update a specific user's score in userdata.txt
    public void updateUserScore(String name, int newScore) {
        // Automatically reads, updates the correct user, and saves!
        ArrayList<User> users = getAllUsers();
        for (User u : users) {
            if (u.getName().equalsIgnoreCase(name)) {
                u.setScore(newScore);
                break;
            }
        }
        saveAllUsers(users); // Save the updated user data to file
    }
}