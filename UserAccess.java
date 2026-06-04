/*
Contributed by Andrean (103325)
Role: Member 1 - Data and Storage Lead

Description: 
1. Defines the interface for accessing user data in the eWaste education application
*/


import java.util.ArrayList;

public interface UserAccess {
    ArrayList<User> getAllUsers(); // Method to retrieve all users from userdata.txt in UserFileAccess.java
    void saveAllUsers(ArrayList<User> users); // Method to save all users to userdata.txt in UserFileAccess.java
    void updateUserScore(String name, int newScore); // Method to update a specific user's score in userdata.txt in UserFileAccess.java
}