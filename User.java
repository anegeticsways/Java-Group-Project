/*
Contributed by Andrean (103325)
Role: Member 1 - Data and Storage Lead

Description: 
1. Represents a user in the eWaste education application
*/

// Class to represent a user, containing properties for the user's name and score, along with getter and setter methods to manage these properties.
public class User {
    private final String name; // User's name, set at creation and cannot be changed
    private int score;

    // Constructor to initialize the user's name and score
    public User(String name, int score) {
        this.name = name;
        this.score = score;
    }

    // Getter method to retrieve the user's name
    public String getName() {
        return name;
    }

    // Getter and setter methods to manage the user's score
    public int getScore() {
        return score;
    }

    // Setter method to update the user's score
    public void setScore(int score) {
        this.score = score;
    }
}
