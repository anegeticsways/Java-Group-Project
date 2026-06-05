/*
Contributed by Andrean (103325)
Role: Member 1 - Data and Storage Lead

Description: 
1. Defines the interface for accessing user data in the eWaste education application
*/

import java.util.ArrayList;

public interface UserAccess {
    ArrayList<User> loadUsers(); // Method to load all users from storage, returning an ArrayList of User objects
    void saveAllUsers(ArrayList<User> users); // Method to save all users to storage, taking an ArrayList of User objects as input
    void updateUserScore(String name, int score); // Method to update a specific user's score, taking the user's name and new score as input
}
