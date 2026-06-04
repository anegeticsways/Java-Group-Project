/*
Contributed by Andrean (103325)
Role: Member 1 - Data and Storage Lead

Description: 
1. Defines the interface for accessing user data in the eWaste education application
*/

import java.util.ArrayList;

public interface UserAccess {
    ArrayList<User> loadUsers();
    void saveAllUsers(ArrayList<User> users);
    void updateUserScore(String name, int score);
}
